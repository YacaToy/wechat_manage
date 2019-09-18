package com.yc.wechat_manage.util;

import com.yc.wechat_manage.common.properties.ConfigKeyConstance;
import com.yc.wechat_manage.common.properties.PropertiesConfig;

public class WeCharConfiguration {

    public final static String APP_ID = PropertiesConfig.getValues(ConfigKeyConstance._WX_APP_ID, ConfigKeyConstance._WX_APP_ID);

    public final static String SECRET = PropertiesConfig.getValues(ConfigKeyConstance._WX_SECRET, ConfigKeyConstance._WX_SECRET);

    /**
     * 获取微信公众号的token
     */
    public final static String getAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APP_ID+
            "&secret="+SECRET;

    /**
     * 获取用户的token
     */
    public static String getCodeForAccessToken(String code){
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APP_ID+
                "&secret="+SECRET+"&code="+code+"&grant_type=authorization_code";
        return url;
    }

    /**
     * 获取刷新用户的token
     * @param refresh_token
     * @return
     */
    public static String getCodeForAccessTokenFlush(String refresh_token){
        String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+APP_ID+"&grant_type=refresh_token&refresh_token="+refresh_token;
        return url;
    }

    /**
     * 拉取用户信息
     * @param access_token
     * @param openid
     * @return
     */
    public static String getTokenForUser(String access_token,String openid){
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
        return url;
    }

    /**
     * 检查用户的 access_token 是否有效
     * @param access_token
     * @param openid
     * @return
     */
    public static String isAccessTokenOk(String access_token,String openid){
        String url = "https://api.weixin.qq.com/sns/auth?access_token="+access_token+"&openid="+openid;
        return url;
    }

    /**
     * 获取用户基本信息（包括UnionID机制）
     * @param access_token
     * @param openId
     * @return
     */
    public static String getTokenForUser2(String access_token , String openId){
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+openId+"&lang=zh_CN";
        return url;
    }

}
