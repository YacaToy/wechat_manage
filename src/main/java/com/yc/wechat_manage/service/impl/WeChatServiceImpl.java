package com.yc.wechat_manage.service.impl;

import com.thoughtworks.xstream.XStream;
import com.yc.wechat_manage.common.base.BaseService;
import com.yc.wechat_manage.service.WeChatService;
import com.yc.wechat_manage.util.*;
import org.springframework.stereotype.Service;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@Service
public class WeChatServiceImpl extends BaseService implements WeChatService {

    public void acceptMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 处理接收消息
        ServletInputStream in = request.getInputStream();
        // 将POST流转换为XStream对象
        XStream xs = SerializeXmlUtil.createXstream();
        xs.processAnnotations(InputMessage.class);
        xs.processAnnotations(OutputMessage.class);
        // 将指定节点下的xml节点数据映射为对象
        xs.alias("xml", InputMessage.class);
        // 将流转换为字符串
        StringBuilder xmlMsg = new StringBuilder();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            xmlMsg.append(new String(b, 0, n, "UTF-8"));
        }
        if (StringUtils.isEmpty(xmlMsg.toString())){
            return;
        }
        // 将xml内容转换为InputMessage对象
        InputMessage inputMsg = null;
        logger.info("xmlMsg.toString() :"+xmlMsg.toString());
        inputMsg = (InputMessage) xs.fromXML(xmlMsg.toString());
        InputMessage finalInputMsg = inputMsg;

        String servername = inputMsg.getToUserName();// 服务端
        String custermname = inputMsg.getFromUserName();// 客户端
        long createTime = inputMsg.getCreateTime();// 接收时间
        Long returnTime = Calendar.getInstance().getTimeInMillis() / 1000;// 返回时间
        // 取得消息类型
        String msgType = inputMsg.getMsgType();
        // 根据消息类型获取对应的消息内容
        if (msgType.equals(MsgType.Text.toString())) {
            // 文本消息
            System.out.println("开发者微信号：" + inputMsg.getToUserName());
            System.out.println("发送方帐号：" + inputMsg.getFromUserName());
            System.out.println("消息创建时间：" + inputMsg.getCreateTime() + new Date(createTime * 1000l));
            System.out.println("消息内容：" + inputMsg.getContent());
            System.out.println("消息Id：" + inputMsg.getMsgId());
            noReply(response);
        }
        // 获取并返回多图片消息
        else if (msgType.equals(MsgType.Image.toString())) {
            System.out.println("获取多媒体信息");
            System.out.println("多媒体文件id：" + inputMsg.getMediaId());
            System.out.println("图片链接：" + inputMsg.getPicUrl());
            System.out.println("消息id，64位整型：" + inputMsg.getMsgId());
            OutputMessage outputMsg = new OutputMessage();
            outputMsg.setFromUserName(servername);
            outputMsg.setToUserName(custermname);
            outputMsg.setCreateTime(returnTime);
            outputMsg.setMsgType(msgType);
            ImageMessage images = new ImageMessage();
            images.setMediaId(inputMsg.getMediaId());
            outputMsg.setImage(images);
            System.out.println("xml转换：/n" + xs.toXML(outputMsg));
            response.getWriter().write(xs.toXML(outputMsg));
            noReply(response);
        }else if (msgType.equals(MsgType.Event.toString())){
            System.out.println("开发者微信号：" + inputMsg.getToUserName());
            System.out.println("发送方帐号：" + inputMsg.getFromUserName());
            System.out.println("消息创建时间：" + inputMsg.getCreateTime() + new Date(createTime * 1000l));
            System.out.println("事件类型，subscribe(订阅)、unsubscribe(取消订阅) TEMPLATESENDJOBFINISH(事件为模板消息发送结束)：" + inputMsg.getEvent());
        }else {
            noReply(response);
        }
    }


    private void noReply(HttpServletResponse response) {
        try {
            logger.info("wx-noReply");
            response.sendRedirect("success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
