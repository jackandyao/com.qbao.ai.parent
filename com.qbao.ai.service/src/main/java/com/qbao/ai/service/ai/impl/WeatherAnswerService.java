package com.qbao.ai.service.ai.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qbao.ai.common.constant.Constant;
import com.qbao.ai.model.dto.WeatherDto;
import com.qbao.ai.model.dto.WeatherInfo;
import com.qbao.ai.resposity.mybatis.model.QuestionLink;
import com.qbao.ai.resposity.redis.cache.annotation.CacheType;
import com.qbao.ai.resposity.redis.cache.annotation.RedisCache;
import com.qbao.ai.service.ai.IBaseAnswerService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

/**
 * @author liaijun
 * @createTime 17/4/13 下午3:32
 * $$LastChangedDate: 2017-06-01 11:28:22 +0800 (Thu, 01 Jun 2017) $$
 * $$LastChangedRevision: 238 $$
 * $$LastChangedBy: zhangjun $$
 */
@Service
public class WeatherAnswerService implements IBaseAnswerService{

    @Autowired
    CustomService customService;
    Logger logger= Logger.getLogger(WeatherAnswerService.class);
    @Override
    public Map<String,Object> answerInfo(String q, int device, String lat, String lon,Long userId,int page,int size,Set<String> set) {
        logger.info("answerInfo   q="+q+"&device="+device+"&lat="+lat+"&lon="+lon);
        if(CollectionUtils.isNotEmpty(set)){
            return answerInfoByCity( userId, set) ;
        }
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
        response.put("questionType","weather");
        response.put("success", true);
        response.put("message", "Ok");
        response.put("userId",userId);
        String cityUrl= Constant.AI_CITY+lat+","+lon;
        String cityInfo=InfoByUrl(cityUrl);
        String cityList=getAiCity(cityInfo);
        String[] city=cityList.split(",");
        if(city.length>1){
            String weatherUrl=Constant.AI_WEATHER+city[1];
            String weatherInfo=getWeatherInfo(weatherUrl);
            WeatherDto dto=getWeather(weatherInfo);

            if(dto==null) {
                weatherUrl = Constant.AI_WEATHER + city[0];
                weatherInfo = getWeatherInfo(weatherUrl);
                dto=getWeather(weatherInfo);
                response.put("data",dto);
                response.put("title","小智查了"+dto.getCity()+"的天气,你看看~");
                return response;
            }
            response.put("data",dto);
            response.put("title","小智查了"+dto.getCity()+"的天气,你看看~");
            return response;
        }

        Map<String,Object> map=customService.answerInfo("",0,"","",null,0,0,null);
        map.put("userId",userId);
        return map;
    }

    public Map<String,Object> answerInfoByCity(Long userId,Set<String> citys) {
        logger.info("answerInfoByCity  city="+citys);
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("responseCode", Constant.RESPONSE_CODE_SCUESS);
        response.put("questionType","weather");
        response.put("success", true);
        response.put("message", "Ok");
        response.put("userId",userId);
        for (String city:citys){
            logger.info("answerInfoByCity >>>>>>>>>>>>>>>city="+city);
            String weatherUrl=Constant.AI_WEATHER+city;
            String weatherInfo=getWeatherInfo(weatherUrl);
            WeatherDto dto=getWeather(weatherInfo);
            response.put("data",dto);
            response.put("title","小智查了"+dto.getCity()+"的天气,你看看~");
            return response;
        }

        Map<String,Object> map=customService.answerInfo("",0,"","",null,0,0,null);
        map.put("userId",userId);
        return map;
    }
    public  String InfoByUrl(String urlString){
        String res = "";
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line+"\n";
            }
            in.close();
        } catch (Exception e) {
           logger.error(urlString+"接口异常:"+e.getMessage(),e);
        }
        logger.info(urlString+">>>>>>>>>>>>>>>调用接口返回信息 res="+res);
        return res;
    }



    public String getAiCity(String cityInfo){
        JSONObject jsonObject = JSON.parseObject(cityInfo);
        JSONArray addrList = jsonObject.getJSONArray("addrList");
        if(addrList!=null){
            JSONObject addr = addrList.getJSONObject(0);
            if(addr!=null) {
                String status = addr.getString("status");
                logger.info("根据经纬度获取城市状态(0:失败,1:成功) status=" + status);
                return addr.getString("admName");
            }
        }

        return "";
    }

    /**
     * 获取天气预报信息
     * @throws
     */
    @RedisCache(expire = 60 * 30, clazz = String.class, cacheType = CacheType.OBJECT)
    public  String getWeatherInfo(String url) {

        URL realUrl = null;
        ByteArrayOutputStream out = null;

        try {
            //真实地址
            realUrl = new URL(url);
            //打开连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            //设置连接属性
            connection.setRequestProperty("accept", "application/xhtml+xml,application/json,application/xml;charset=utf-8, text/javascript, */*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("contentType", "utf-8");
            connection.setRequestMethod("GET");
            connection.setRequestProperty("user-agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko");


            //这里获取的数据时压缩格式的数据所以用gzip进行解压缩
            GZIPInputStream gip = new GZIPInputStream(connection.getInputStream());
            out = new ByteArrayOutputStream();
            //缓冲
            byte []buffer = new byte[1024];
            int len ;
            while((len = gip.read(buffer))!=-1){
                out.write(buffer, 0, len);
            }

            //把字节数据转化为字符串返回回去
            return (new String(out.toByteArray(), "utf-8"));
        } catch (Exception e) {
            logger.error("getWeatherInfo url="+url+e.getMessage(),e);

        }finally{
            //关闭流
            try {
                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }

        }
        return null;
    }
    public WeatherDto getWeather(String weatherInfo){
        logger.info("getWeather  start weatherInfo="+weatherInfo);
        JSONObject jsonObject = JSON.parseObject(weatherInfo);
        String status=jsonObject.getString("status");
        JSONObject data=jsonObject.getJSONObject("data");
        if(status!=null&&Constant.WEATHER_STATUS.equals(status)){
            if(data!=null){
                WeatherDto weatherDto=new WeatherDto();
                weatherDto.setCity(data.getString("city"));
                String wendu=data.getString("wendu");
                String ganmao=data.getString("ganmao");

                JSONArray forecast =data.getJSONArray("forecast");
                List<WeatherInfo> infoList=new ArrayList<WeatherInfo>();
                if(forecast!=null){
                    for(int i=0;i<forecast.size();i++){
                        WeatherInfo info=new WeatherInfo();
                        JSONObject json=forecast.getJSONObject(i);
                        info.setAirQuality(json.getString("fengxiang"));
                        info.setDate(json.getString("date"));
//                      String fengxiang=json.getString("fengxiang");
//                      String fengli=json.getString("fengli");
                        String high=json.getString("high").replace("高温 ","");
                        String type=json.getString("type");
                        String low=json.getString("low").replace("低温 ","");
//                      String date=json.getString("date");

                        info.setTemperature(low+"~"+high);
                        info.setWeather(type);

                        infoList.add(info);

                    }
                }
                weatherDto.setWeatherInfo(infoList);
                return weatherDto;
            }
        }



        return null;
    }

//    public static void main(String[] args) throws  Exception{
//        WeatherAnswerService w=new WeatherAnswerService();
//        //String cityUrl= Constant.AI_CITY+"39.97646,116.3039";
//        WeatherDto dto=w.answerInfo("",1,"31.21333","121.349731");
//        System.out.println(dto);
//    }

}
