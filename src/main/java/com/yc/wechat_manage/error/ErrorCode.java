package com.yc.wechat_manage.error;

/**
 * 错误码
 *
 */
public enum ErrorCode {
	sessionerror("100003","登陆态失效或未登录"),
	success("000000","操作成功"),
	paramerror("900001","参数错误"),
	systemerror("999999","系统错误");


	private String code;
	
	private String msg;
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private ErrorCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	
	
}
