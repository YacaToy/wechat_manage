package com.yc.wechat_manage.util;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @email 17673178733@163.com
 * @author:YanCan
 * @date: 2019/3/4
 * @time: 14:23
 */
public class MediaIdMessage {
    @XStreamAlias("MediaId")
    @XStreamCDATA
    private String MediaId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

}
