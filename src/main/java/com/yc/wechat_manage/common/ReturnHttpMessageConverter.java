package com.yc.wechat_manage.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.yc.wechat_manage.common.threadLocal.LogThreadLocal;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReturnHttpMessageConverter extends FastJsonHttpMessageConverter {

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        if(LogThreadLocal.getLogDTO()!=null) {
            LogThreadLocal.getLogDTO().setLogReturn(JSONObject.toJSONString(obj));
        }
        super.writeInternal(obj, outputMessage);
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream in = inputMessage.getBody();
        byte[] buf = new byte[1024];
        for (;;) {
            int len = in.read(buf);
            if (len == -1) {
                break;
            }

            if (len > 0) {
                baos.write(buf, 0, len);
            }
        }
        byte[] bytes = baos.toByteArray();
        Object obj = JSON.parseObject(bytes, 0, bytes.length, this.getFastJsonConfig().getCharset().newDecoder(), clazz);
        return obj;
    }

}
