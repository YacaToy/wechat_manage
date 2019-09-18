package com.yc.wechat_manage.common;

import com.alibaba.fastjson.JSON;
import com.yc.wechat_manage.error.ErrorCode;

import java.util.UUID;

/**
 * 总体返回类
 *
 */
public class ResponseInfo {
	
	private String returnCode;
	
	private String returnMsg;
	
	private Object returnData;
	
	private String serialNum;
	
	public ResponseInfo() {
		super();
		this.returnCode = ErrorCode.success.getCode();
		this.returnMsg = ErrorCode.success.getMsg();
		this.serialNum = UUID.randomUUID().toString();
	}
	
	public ResponseInfo(Object returnData) {
		super();
		this.returnCode = ErrorCode.success.getCode();
		this.returnMsg = ErrorCode.success.getMsg();
		this.returnData = returnData;
		this.serialNum = UUID.randomUUID().toString();
	}
	
	public ResponseInfo(ErrorCode responseCode) {
		super();
		this.returnCode = responseCode.getCode();
		this.returnMsg = responseCode.getMsg();
		this.serialNum = UUID.randomUUID().toString();
	}
	
	
	public ResponseInfo(ErrorCode responseCode, Object returnData) {
		super();
		this.returnCode = responseCode.getCode();
		this.returnMsg = responseCode.getMsg();
		this.returnData = returnData;
		this.serialNum = UUID.randomUUID().toString();
	}

	public String getReturnCode() {
		return returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public Object getReturnData() {
		return returnData;
	}

	public void setReturnData(Object returnData) {
		this.returnData = returnData;
	}

	public String getSerialNum() {
		return serialNum;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
