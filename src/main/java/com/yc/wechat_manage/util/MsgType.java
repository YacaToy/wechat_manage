package com.yc.wechat_manage.util;

/**
 * @email 17673178733@163.com
 * @author:YanCan
 * @date: 2019/3/4
 * @time: 14:24
 */
public enum MsgType {
    Text("text"),
    Image("image"),
    Event("event"),
    Music("music"),
    Video("video"),
    Voice("voice"),
    Location("location"),
    Link("link");
    private String msgType = "";

    MsgType(String msgType) {
        this.msgType = msgType;
    }

    /**
     * @return the msgType
     */
    @Override
    public String toString() {
        return msgType;
    }
}
