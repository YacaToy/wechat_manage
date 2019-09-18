package com.yc.wechat_manage.common.web;

import com.yc.wechat_manage.common.application.YCApplication;
import com.yc.wechat_manage.common.base.BaseLog;
import com.yc.wechat_manage.util.StringUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.Properties;

public class YDispatcherServlet extends DispatcherServlet {

    static BaseLog logger = BaseLog.newInstance(YDispatcherServlet.class);

    static ContextLoaderListener context;

    public static final String SPRING_PATH = "spring.xml";

    public static final String CLASSPATH = "classpath:";

    private static final long serialVersionUID = 1L;

    public static String jarPath;

    static {
        try {
            jarPath = URLDecoder
                    .decode(YCApplication.class.getProtectionDomain().getCodeSource().getLocation().getFile(), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static ContextLoaderListener getContext() {
        return context;
    }

    {
        Properties pro = System.getProperties();
        Resource resource = new ClassPathResource("configuration.properties");
        try {
            InputStream in = resource.getInputStream();
            if (in == null) {
                throw new IOException("not found configuration.properties!");
            }
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] path = pro.getProperty("spring.path").split(",");
        System.out.println(pro.getProperty("project.path"));
        StringBuffer springPath=new StringBuffer();
        if(!StringUtils.isEmpty(path)) {
            for (int i = 0; i < path.length; i++) {
                springPath.append(","+CLASSPATH+path[i]);
            }
        }
        setContextConfigLocation(jarPath + "/spring-mvc.xml"+(StringUtils.isEmpty(springPath.toString())?"":springPath.toString()));
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sr = config.getServletContext();
        sr.setInitParameter("contextConfigLocation", getWebPath());
        context = new ContextLoaderListener();
        context.initWebApplicationContext(sr);
        super.init(config);
    }

    public String getWebPath(){
        StringBuffer webPath = new StringBuffer("");
        webPath.append("classpath:spring-mvc.xml"+",");
        webPath.append("classpath:spring-mybatis.xml"+ ",");
        webPath.append("classpath:spring.xml"+",");
        webPath.append("classpath:spring-redis.xml");
        logger.info("webPath loading : " + webPath);
        return webPath.toString();
    }

    @Override
    public void destroy() {
        try {
            com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        context.closeWebApplicationContext(this.getServletContext());
        cleanupAttributes(this.getServletContext());
        try{
            DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
        }catch(Exception e){
        }
        logger.debug("[DriverMangerListner]:-------DriverManager deregisterDriver end...");
        super.destroy();
    }

    static void cleanupAttributes(ServletContext sc) {
        Enumeration<String> attrNames = sc.getAttributeNames();
        while (attrNames.hasMoreElements()) {
            String attrName = attrNames.nextElement();
            if (attrName.startsWith("org.springframework.")) {
                Object attrValue = sc.getAttribute(attrName);
                if (attrValue instanceof DisposableBean) {
                    try {
                        ((DisposableBean) attrValue).destroy();
                    } catch (Throwable ex) {
                        logger.error("Couldn't invoke destroy method of attribute with name '" + attrName + "'", ex);
                    }
                }
            }
        }
    }

}
