package com.yc.wechat_manage.common.threadLocal;

import com.yc.wechat_manage.common.base.LogDTO;

public class LogThreadLocal {
	
	private static final ThreadLocal<LogDTO> logDTOs=new ThreadLocal<LogDTO>();
	
	public static void setLogDTO(LogDTO logDTO) {
		logDTOs.set(logDTO);
	}
	
	public static LogDTO getLogDTO() {
		return logDTOs.get();
	}
	
	public static void remove() {
		logDTOs.remove();
	}
	
	
}
