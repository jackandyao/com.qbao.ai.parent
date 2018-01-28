package com.qbao.controller.user;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.qbao.controller.base.BaseController;
import com.qbao.result.ClientAjaxResult;
import com.qbao.security.CasUser;

/**
 * description
 *
 * @author by zhangchanghong on 15/12/7.
 */
@Controller
public class AccountController extends BaseController {
	/*
	 * @Resource private SysConfigService sysConfigService;
	 */

	private final static Logger LOG = LoggerFactory.getLogger(AccountController.class);
	// @Secured("{ROLE_USER}")
	@RequestMapping("/account/isUserSign.html")
	public ModelAndView isUserSign() {
		// String redirectUrl =
		// sysConfigService.getKey(PropertiesConst.USERCENTER_WAP_LOGIN_PAGE.key);
		return new ModelAndView();
	}

	@Secured({ "ROLE_USER" })
	@RequestMapping(value = "/index.do")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView();
		System.out.println("adsf");
		System.out.println(getCurrentUsername());
		view.setViewName("/index.jsp");
		return view;
	}

	@RequestMapping(value = "/api/account4Client/login")
	@ResponseBody
	public ClientAjaxResult login(HttpServletRequest request, String st) {
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					"_cas_stateful_", st);
			LOG.info("param st: "+st +" and begin...");
			WebAuthenticationDetails details = new WebAuthenticationDetails(
					request);
			authenticationToken.setDetails(details);
			ApplicationContext ctx = WebApplicationContextUtils
					.getWebApplicationContext(request.getSession()
							.getServletContext());
			CasAuthenticationProvider casAuthenticationProvider = ctx
					.getBean(CasAuthenticationProvider.class);
			Authentication authentication = casAuthenticationProvider
					.authenticate(authenticationToken);
			SecurityContext context = SecurityContextHolder.getContext();
			context.setAuthentication(authentication);

			if (authentication != null) {
				Object principal = authentication.getPrincipal();
				if (principal instanceof UserDetails) {
					CasUser cu = (CasUser) principal;
					int siz = cu.getAuthorities().size();
					Iterator it=cu.getAuthorities().iterator();
					while (it.hasNext()){
						LOG.info("cu.getAuthorities().size()==>>"+((GrantedAuthority)it.next()).getAuthority());
					}

				}
			}
			LOG.info("param st: "+ st +" and get successfully...");

			return ClientAjaxResult.success();
		} catch (UsernameNotFoundException e) {
			LOG.info("param st: "+st+" your account is freezon: ",e);
			return ClientAjaxResult.failed("您的账号已经被冻结");
		} catch (Exception e) {
			LOG.info("param st: "+st+" network error :",e);
			return ClientAjaxResult.failed("网络错误");
		}
	}

	/**
	 * 黑名单用户访问提醒页面
	 */
	@RequestMapping(value = "/black/accessdefine.html")
	public ModelAndView message(Integer j) {
		// String errmsg =
		// sysConfigService.getKey(PropertiesConst.BY_BLACK_MSG_STR.key);
		ModelAndView view = new ModelAndView("/error/error");
		// result.addObject("errmsg", errmsg);
		if (j != null && j == 1) {
			view.setViewName("/error/ajaxerror");
		}
		return view;
	}

	/**
	 * 用户商家后台登录时自动请求此方法实现小登录
	 * @param jsonpCallback
	 * @param response
     */
	@Secured({"ROLE_USER"})
	@RequestMapping(value = "/login")
	@ResponseBody
	public void login(String jsonpCallback, HttpServletResponse response) {
//        try {
//            exchangeJson(jsonpCallback, response, AjaxResult.success());
//        } catch (Exception e) {
//            exchangeJson(jsonpCallback, response, AjaxResult.failed(e.getMessage()));
//        }
		//return new ModelAndView("forward:/release/h5Web/business/default/page.html?" + System.currentTimeMillis());
	}
}
