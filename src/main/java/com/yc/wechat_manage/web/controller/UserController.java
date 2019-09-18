package com.yc.wechat_manage.web.controller;

import com.yc.wechat_manage.common.ResponseInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @ResponseBody
    @RequestMapping(value="/add",method={RequestMethod.POST,RequestMethod.GET})
    public ResponseInfo add(String str) throws Exception {
        return new ResponseInfo(str);
    }


}
