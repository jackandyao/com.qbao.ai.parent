package com.qbao.ai.util.http;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author linhanye 2016年11月16日
 *
 */
public class HttpClientUtils {
	public static final String ENCODING_UTF_8 = "UTF-8";
	public static final int ONE_THOUSAND = 1000;
	public static final int ZERO = 0;
	public static final int FIFTEEN_THOUSAND = 15000;
	public static final int SIXTY_THOUSAND = 60000;
	public static final int TWO = 2;
	public static final int MAX_PERROUTE = 256;
	public static final int THIRTY = 30;

	private static volatile HttpClient httpclient;

	/**
	 * get 方法
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String doGet(String url) throws IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);

		String response = null;
		try {
			response = executeHttpRequest(httpGet, httpClient);
		} finally {
			httpClient.getConnectionManager().shutdown(); // shutdown pool
		}
		return response;
	}

	/**
	 * 这个接口适合需要频繁调用get的场景
	 *
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String doGetFast(String url) throws IOException {
		HttpClient client = getSharedHttpClient();
		HttpGet httpGet = new HttpGet(url);

		return executeHttpRequest(httpGet, client);
	}

	private static String executeHttpRequest(HttpRequestBase requestBase, HttpClient httpClient) throws IOException {
		try {
			HttpResponse httpResponse = httpClient.execute(requestBase);
			StatusLine stausLine = httpResponse.getStatusLine();
			if (HttpStatus.SC_OK != stausLine.getStatusCode()) {
				// 非200 全部当做IO Exception
				throw new IOException("StatusCode=" + stausLine.getStatusCode());
			}
			return EntityUtils.toString(httpResponse.getEntity(), ENCODING_UTF_8);
		} finally {
			requestBase.releaseConnection();
		}
	}

	/**
	 * post方法
	 * 
	 * @param parameters
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String doPost(Map<String, String> parameters, String url) throws IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = createHttpPost(parameters, url);

		String response = null;
		try {
			response = executeHttpRequest(httpPost, httpClient);
		} finally {
			// shut down pool
			httpClient.getConnectionManager().shutdown();
		}
		return response;
	}

	/**
	 * post方法
	 * 
	 * @param json
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String doPost(String json, String url) throws IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = createHttpPost(json, url);

		String result = null;
		try {
			HttpResponse response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity respEntity = response.getEntity();
				result = EntityUtils.toString(respEntity);
				if (respEntity != null) {
					respEntity.consumeContent();
				}
			}
		} finally {
			// shut down pool
			httpClient.getConnectionManager().shutdown();
		}
		return result;
	}

	public static String doPostFast(Map<String, String> parameters, String url) throws IOException {
		HttpClient httpClient = getSharedHttpClient();
		HttpPost post = createHttpPost(parameters, url);

		return executeHttpRequest(post, httpClient);
	}

	/**
	 * 创建httppost
	 * 
	 * @param parameters
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static HttpPost createHttpPost(Map<String, String> parameters, String url)
			throws UnsupportedEncodingException {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		HttpPost httpPost = new HttpPost(url);
		if (nameValuePairs.size() > ZERO) {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, ENCODING_UTF_8));
		}
		return httpPost;
	}

	/**
	 * 创建httppost
	 * 
	 * @param json
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static HttpPost createHttpPost(String json, String url) throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);
		StringEntity entity = new StringEntity(json, ENCODING_UTF_8);// 解决中文乱码问题
		// entity.setContentEncoding(ENCODING_UTF_8);
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		httpPost.setHeader("Content-Type", "application/json");
		httpPost.setHeader("Accept", "application/json");
		return httpPost;
	}

	/**
	 * 签名
	 * 
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String sign(Map map) {
		List<String> keys = new ArrayList<String>(map.keySet());
		Collections.sort(keys);
		String prestr = "";
		String secretKey = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = map.get(key).toString();
			if ("sign".equals(key)) {
				continue;
			} else if (map.get(key) instanceof HashMap) {
				value = "[object Object]";
			} else if ("secretKey".equals(key)) {
				secretKey = value;
			}
			if (i == keys.size() - 1) {
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
			System.out.println(i + " " + key + ":" + value);
		}
		prestr = prestr + "&" + secretKey;
		return MD5Util.getMessageDigest(prestr);
	}

	/**
	 * 获取httpclient
	 * 
	 * @return
	 */
	private static HttpClient getSharedHttpClient() {
		if (httpclient == null) {
			synchronized (HttpClientUtils.class) {
				if (httpclient == null) {
					HttpParams httpParams = new BasicHttpParams();
					HttpConnectionParams.setConnectionTimeout(httpParams, FIFTEEN_THOUSAND);
					HttpConnectionParams.setSoTimeout(httpParams, SIXTY_THOUSAND);
					HttpConnectionParams.setTcpNoDelay(httpParams, true);

					final PoolingClientConnectionManager connectionManager = new PoolingClientConnectionManager();
					// 创建socket的上线是256*2
					connectionManager.setMaxTotal(MAX_PERROUTE * TWO);
					// 对每个指定连接的服务器（指定的IP）最多可以创建256 socket进行访问
					connectionManager.setDefaultMaxPerRoute(MAX_PERROUTE);

					DefaultHttpClient httpclient = new DefaultHttpClient(connectionManager);
					httpclient.setParams(httpParams);
					httpclient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);

					// 设置为不重试
					DefaultHttpRequestRetryHandler NO_RETRY = new DefaultHttpRequestRetryHandler(ZERO, false);
					httpclient.setHttpRequestRetryHandler(NO_RETRY);

					// 设置keep alive策略
					httpclient.setKeepAliveStrategy(new ConnectionKeepAliveStrategy() {
						@Override
						public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
							HeaderElementIterator it = new BasicHeaderElementIterator(
									response.headerIterator(HTTP.CONN_KEEP_ALIVE));
							while (it.hasNext()) {
								HeaderElement he = it.nextElement();
								String param = he.getName();
								String value = he.getValue();
								if (value != null && param.equalsIgnoreCase("timeout")) {
									return Long.parseLong(value) * ONE_THOUSAND;
								}
							}
							// 找不到keep-alive header
							return THIRTY * ONE_THOUSAND;
						}
					});

					// start check thread
					Thread checkThread = new Thread() {
						public void run() {
							while (true) {
								try {
									Thread.sleep(ONE_THOUSAND);
								} catch (InterruptedException e) {
								}
								connectionManager.closeExpiredConnections();
								connectionManager.closeIdleConnections(THIRTY, TimeUnit.SECONDS);
							}
						}
					};
					checkThread.setDaemon(true); // 必须set daemon
					checkThread.start();

					// 将构造完成的局部对象 --> shared object
					HttpClientUtils.httpclient = httpclient;
				}
			}
		}
		return HttpClientUtils.httpclient;
	}

}
