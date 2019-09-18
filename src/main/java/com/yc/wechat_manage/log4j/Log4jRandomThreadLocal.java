package com.yc.wechat_manage.log4j;

/**
 * log4j随机参数local类
 *
 */
public class Log4jRandomThreadLocal {

	private static final ThreadLocal<Integer> randomNum = new ThreadLocal<Integer>();

	public static void set(Integer x) {
		randomNum.set(x);
	}

	public static Integer get() {
		return randomNum.get();
	}
	
	public static void remove() {
		randomNum.remove();
	}

}
