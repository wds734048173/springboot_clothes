package org.lanqiao.clothes.controller;

import org.apache.ibatis.annotations.Mapper;
import org.lanqiao.clothes.pojo.User;
import org.lanqiao.clothes.service.IUserService;
import org.lanqiao.clothes.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Auther: WDS
 * @Date: 2019/1/12 20:20
 * @Description:登录注册管理
 */
@Controller
public class LoginController {

    @Autowired
    IUserService userService;

    @RequestMapping("/manager/login")
    public String login(HttpServletRequest req, HttpServletResponse resp, Model model){
        if(req.getParameter("username") == null){
            return "/manager/login";
        }
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(StringUtils.isEmpty(username)){
            model.addAttribute("msg","用户名不能为空！");
            return "/manager/login";
        }
        if(username.length() > 40){
            model.addAttribute("msg","用户名不能多于40个字符！");
            return "/manager/login";
        }
        if(StringUtils.isEmpty(password)){
            model.addAttribute("msg","密码不能为空！");
            return "/manager/login";
        }
        String pwdMD5 = MD5Utils.MD5(password);
        User user = User.builder().build();
        user.setUsername(username);
        user.setPassword(pwdMD5);
        User retUser = userService.getUser(user);
        if(retUser == null){
            model.addAttribute("msg","用户名或密码错误，请重新输入！");
            return "/manager/login";
        }else{
            HttpSession session = req.getSession();
            session.setAttribute("user",retUser);
            return "/manager/index";
        }
    }

    @RequestMapping("/manager/exit")
    public String exit(HttpServletRequest req, HttpServletResponse resp, Model model){
        HttpSession session = req.getSession();
        session.invalidate();
        return "/manager/login";
    }
}
