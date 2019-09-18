package com.yc.wechat_manage.util;

import javax.servlet.http.HttpServletRequest;

/**
 * request请求工具类
 *
 */
public class RequestHandlerUtil {
	
	/**
	 * 获取请求全路径
	 * @param request
	 * @return
	 */
	public static String getRequestAllPath(HttpServletRequest request) {
		return request.getRequestURL().toString();
	}
	
	/**
	 * 获取请求路径
	 * @param request
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request) {
		return request.getRequestURI();
	}
	
	/**
	 * 获取Controller请求路径
	 * @param request
	 * @return
	 */
	public static String getProjectRequestPath(HttpServletRequest request) {
		return request.getRequestURI().replaceAll(request.getContextPath(), "");
	}
	
}
