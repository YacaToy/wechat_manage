package com.yc.wechat_manage.common.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @email 17673178733@163.com
 * @author:YanCan
 * @date: 2019/3/29
 * @time: 10:12
 */
public class PropertiesConfig {

    private static Properties prop ;

    static {
        prop = new Properties();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration.properties");
        try {
            prop.load(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Integer getValues(String key,Integer defalut){
        Integer temp = null ;
        try {
            temp = Integer.parseInt(prop.getProperty(key));
        }catch (Exception e){
            temp = null;
        }
        if (temp == null){
            temp = defalut;
        }
        return temp ;
    }

    public static String getValues(String key,String defalut){
        String temp = prop.getProperty(key);
        if (temp == null){
            temp = defalut;
        }
        return temp;
    }

    public static List<String> getList(String key) {
        String temp=prop.getProperty(key);
        List<String>  list=Arrays.asList(temp.split(","));
        if(list==null) {
            list=new ArrayList<String>();
        }
        return list;
    }
}
