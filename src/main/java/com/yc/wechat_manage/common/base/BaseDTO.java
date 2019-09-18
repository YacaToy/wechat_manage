package com.yc.wechat_manage.common.base;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public abstract class BaseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
	
	
}
