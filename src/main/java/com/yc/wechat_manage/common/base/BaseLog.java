package com.yc.wechat_manage.common.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseLog {
	
	private Log logger;
	
	private BaseLog(Log logger) {
		super();
		this.logger = logger;
	}



	public static BaseLog newInstance(Class<?> c) {
		return new BaseLog(LogFactory.getLog(c));
	}
	
	public void info(Object message) {
		info(message, null);
	}
	
	public void debug(Object message) {
		debug(message, null);
	}
	
	public void warn(Object message) {
		warn(message, null);
	}
	
	public void error(Object message) {
		error(message, null);
	}

	public Log getLogger() {
		return logger;
	}

	public void setLogger(Log logger) {
		this.logger = logger;
	}

	public void error(Object message, Throwable ex) {
		if(logger.isErrorEnabled()) {
			logger.error(message,ex);
		}
	}
	
	public void info(Object message, Throwable ex) {
		if(logger.isInfoEnabled()) {
			logger.info(message,ex);
		}
	}
	
	public void debug(Object message, Throwable ex) {
		if(logger.isDebugEnabled()) {
			logger.debug(message,ex);
		}
	}
	
	public void warn(Object message, Throwable ex) {
		if(logger.isWarnEnabled()) {
			logger.warn(message,ex);
		}
	}
	
}
