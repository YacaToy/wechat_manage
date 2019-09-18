package com.yc.wechat_manage.common.application;

import com.yc.wechat_manage.common.base.BaseLog;
import com.yc.wechat_manage.util.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

public class YCApplication {

	public static final String SPRING_PATH = "spring.xml";

	public BaseLog logger = BaseLog.newInstance(getClass());

	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	{
		String[] path = null;
		Properties pro = System.getProperties();
		try {
			Resource resource = new ClassPathResource("zccommon.properties");
			InputStream in = resource.getInputStream();
			if (in == null) {
				throw new IOException("not found zccommon.properties!");
			}
			pro.load(in);
			path = pro.getProperty("spring.path").split(",");
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("not found zccommon.properties!");
		}
		if (StringUtils.isEmpty(path)) {
			path = new String[] {};
		}
		String[] xmlPath = new String[path.length + 3];
		for (int i = 0; i < path.length; i++) {
			xmlPath[i + 3] = path[i];
		}
		try {
			String jarPath = YCApplication.class.getProtectionDomain().getCodeSource().getLocation().getFile();
			jarPath = URLDecoder.decode(jarPath, "UTF-8");
			xmlPath[0] = "jar:file:" + jarPath + "!/spring/spring.xml";
			xmlPath[1] = "jar:file:" + jarPath + "!/spring/spring-mybatis.xml";
			xmlPath[2] = "jar:file:" + jarPath + "!/spring/spring-redis.xml";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		setApplicationContext(new ClassPathXmlApplicationContext(xmlPath));
	}

}
