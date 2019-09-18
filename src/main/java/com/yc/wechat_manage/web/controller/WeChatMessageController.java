package com.yc.wechat_manage.web.controller;

import com.yc.wechat_manage.common.base.BaseController;
import com.yc.wechat_manage.service.WeChatService;
import com.yc.wechat_manage.util.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
public class WeChatMessageController extends BaseController {

    @Autowired
    WeChatService weChatService ;

    @ResponseBody
    @RequestMapping(value="/getMassage",method={RequestMethod.POST, RequestMethod.GET} ,produces = "text/html;charset=UTF-8")
    public void getMassage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("getMessage");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = response.getWriter();
        if (SignUtil.checkSignature(signature,timestamp,nonce)){
            out.println(echostr);
            weChatService.acceptMessage(request,response);
        }
    }

}
