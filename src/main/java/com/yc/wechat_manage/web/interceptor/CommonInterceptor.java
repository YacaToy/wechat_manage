package com.yc.wechat_manage.web.interceptor;

import com.yc.wechat_manage.common.base.BaseInterceptor;
import com.yc.wechat_manage.log4j.Log4jRandomThreadLocal;
import com.yc.wechat_manage.threadLocal.ActionTimeThreadLocal;
import com.yc.wechat_manage.util.RequestHandlerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class CommonInterceptor extends BaseInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Log4jRandomThreadLocal.set((int) (Math.random() * 20));
        logger.info(RequestHandlerUtil.getProjectRequestPath(request)+" request start :");
        ActionTimeThreadLocal.setTime(System.currentTimeMillis());
        Map<String, String[]> param = request.getParameterMap();
        StringBuffer sb = new StringBuffer("request param :");
        Set<String> keySet=param.keySet();
        for (String temp : keySet) {
            sb.append(temp+"=");
            sb.append(Arrays.toString(param.get(temp))+";");
        }
        logger.info(sb.toString());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        logger.info(RequestHandlerUtil.getProjectRequestPath(request)+" request end.");
        long costTime=System.currentTimeMillis() - ActionTimeThreadLocal.getTime();
        logger.info("this request cost time :" +costTime+ "ms");
        ActionTimeThreadLocal.removeInfo();
        Log4jRandomThreadLocal.remove();
        Log4jRandomThreadLocal.set((int) (Math.random() * 20));
        super.afterCompletion(request, response, handler, ex);
    }
}
