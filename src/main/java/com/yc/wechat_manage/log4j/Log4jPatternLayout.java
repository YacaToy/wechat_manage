package com.yc.wechat_manage.log4j;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternParser;

/**
 * log4j转换介入
 *
 */
public class Log4jPatternLayout extends PatternLayout {

	public Log4jPatternLayout(String pattern) {
		super(pattern);
	}

	public Log4jPatternLayout() {
		super();
	}

	/**
	 * 重写createPatternParser方法，返回PatternParser的子类
	 */
	@Override
	protected PatternParser createPatternParser(String pattern) {
		return new Log4jPatternParser(pattern);
	}

}
