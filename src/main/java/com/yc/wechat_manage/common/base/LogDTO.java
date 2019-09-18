package com.yc.wechat_manage.common.base;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class LogDTO extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 日志id
	 */
	private String logId;
	
	/**
	 * 接口名
	 */
	private String logInterface;
	
	/**
	 * 请求参数
	 */
	private String logParam;
	
	/**
	 * 请求时间
	 */
	private Long logTime;
	
	/**
	 * 返回数据
	 */
	private String logReturn;
	
	/**
	 * 花费时间
	 */
	private long logCostTime;

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getLogInterface() {
		return logInterface;
	}

	public void setLogInterface(String logInterface) {
		this.logInterface = logInterface;
	}

	public String getLogParam() {
		return logParam;
	}

	public void setLogParam(String logParam) {
		this.logParam = logParam;
	}

	public Long getLogTime() {
		return logTime;
	}

	public void setLogTime(Long logTime) {
		this.logTime = logTime;
	}

	public String getLogReturn() {
		return logReturn;
	}

	public void setLogReturn(String logReturn) {
		this.logReturn = logReturn;
	}

	public long getLogCostTime() {
		return logCostTime;
	}

	public void setLogCostTime(long logCostTime) {
		this.logCostTime = logCostTime;
	}
	
	public Map<String,String> toMap() throws IllegalArgumentException, IllegalAccessException{
		Map<String,String> temp=new HashMap<String,String>();
		Field[] f=this.getClass().getDeclaredFields();
		for (int i = 0; i < f.length; i++) {
			if(!Modifier.isStatic(f[i].getModifiers())) {
				f[i].setAccessible(true);
				Object value=f[i].get(this);
				temp.put(f[i].getName(), value==null?null:value.toString());
			}
		}
		return temp;
	}
	
}
