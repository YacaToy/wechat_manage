package com.yc.wechat_manage.common.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BaseInterceptor extends HandlerInterceptorAdapter {

    public Log logger = LogFactory.getLog(getClass());

}
