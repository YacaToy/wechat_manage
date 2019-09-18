package com.yc.wechat_manage.threadLocal;

/**
 * 请求时间存储类
 *
 */
public class ActionTimeThreadLocal {
	
	private static final ThreadLocal<Long> actionTime=new ThreadLocal<Long>();
	
	public static void setTime(Long time) {
		actionTime.set(time);
	}
	
	public static Long getTime() {
		return actionTime.get();
	}
	
	public static void removeInfo() {
		actionTime.remove();
	}
}
