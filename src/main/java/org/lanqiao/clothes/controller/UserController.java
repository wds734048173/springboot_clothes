package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: WDS
 * @Date: 2019/1/11 14:55
 * @Description:用户管理
 */
@Controller
public class UserController {
    @Autowired
    IUserService userService;

    @RequestMapping("/manager/userInfo")
    public String  userInfo(){
        return "/manager/userInfo";
    }
}
