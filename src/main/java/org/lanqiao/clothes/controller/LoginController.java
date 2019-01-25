package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.Store;
import org.lanqiao.clothes.pojo.User;
import org.lanqiao.clothes.service.IStoreService;
import org.lanqiao.clothes.service.IUserService;
import org.lanqiao.clothes.utils.MD5Utils;
import org.lanqiao.clothes.utils.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

/**
 * @Auther: WDS
 * @Date: 2019/1/12 20:20
 * @Description:登录注册管理
 */
@Controller
public class LoginController {

    @Autowired
    IUserService userService;

    @Autowired
    IStoreService storeService;

    @RequestMapping("/imageCodePage")
    public void imageCodePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VerifyCode vc  = new VerifyCode();
        BufferedImage bi = vc.getImage();
        String rcode = vc.getText();
        rcode = rcode.toLowerCase();
        HttpSession s = request.getSession();
        s.setAttribute("rcodes",rcode);
        VerifyCode.output(bi,response.getOutputStream());
    }


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
        //要验证的验证码
        String rcode = req.getParameter("rcode");
        //验证码中的值
        String s = (String) req.getSession().getAttribute("rcodes");
        //验证码的比较
        if (StringUtils.isEmpty(s)||!rcode.equalsIgnoreCase(s)){
            model.addAttribute("msg","验证码错误");
            return "/manager/login";
        }else {
            String pwdMD5 = MD5Utils.MD5(password);
            User user = User.builder().build();
            user.setUsername(username);
            user.setPassword(pwdMD5);
            User retUser = userService.getUser(user);
            if (retUser == null) {
                model.addAttribute("msg", "用户名或密码错误，请重新输入！");
                return "/manager/login";
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("user", retUser);
                int storeId = retUser.getStoreId();
                if(storeId == -1){
                    return "/manager/storeInfo";
                }else{
                    Store store = storeService.selectById(storeId);
                    session.setAttribute("store",store);
                    return "/manager/index";
                }
            }
        }
    }
    //商家注册
    @RequestMapping("/manager/register")
    public String register(HttpServletRequest req, HttpServletResponse resp, Model model){
        //注册页面密码确认
        String username = req.getParameter("username");
        User users = userService.selectByName(username);
//        判断用户名是否存在
        if (users !=null){
            model.addAttribute("msg","用户名已存在");
            return "/manager/userregister";
        }else {
            String password1 = req.getParameter("password1");
            String password2 = req.getParameter("password2");
            //性别信息转换
            String sex =req.getParameter("sex");
            int usersex = 0;
            if ("女".equals(sex)){
                usersex=1;
            }
            //密码的判断
            if (StringUtils.isEmpty(password1)||StringUtils.isEmpty(password2)||!password1.equalsIgnoreCase(password2)){
                model.addAttribute("msg","密码不一致");
                return "/manager/userRegister";
            }else {
                User user = new User();
                user.setUsername(username);
                String pwdMD5 = MD5Utils.MD5(password2);
                user.setPassword(pwdMD5);
                user.setSex(usersex);
//                状态默认0
                user.setState(0);
//                注册时商店id默认-1
                user.setStoreId(-1);
                Date date = new Date();
                user.setCtime(date);
                userService.add(user);
                model.addAttribute("msg","注册成功");
                return "/manager/login";
            }
        }
    }
    @RequestMapping("/manager/exit")
    public String exit(HttpServletRequest req, HttpServletResponse resp, Model model){
        HttpSession session = req.getSession();
        session.invalidate();
        return "/manager/login";
    }
}
