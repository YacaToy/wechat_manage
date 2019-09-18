package com.yc.wechat_manage.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface WeChatService {

    /**
     * 接收微信的消息 并做对应处理
     * @param request
     * @param response
     */
    void acceptMessage(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
