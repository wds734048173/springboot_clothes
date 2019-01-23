package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.User;
import org.lanqiao.clothes.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Auther: WDS
 * @Date: 2019/1/11 14:55
 * @Description:用户管理
 */
@Controller
public class UserController {
    @Autowired
    IUserService userService;

    /*@RequestMapping("/manager/userInfo")
    public String  userInfo(){
        return "/manager/userInfo";
    }
*/
    @RequestMapping("/manager/updateUser")
    public String updateUser(HttpServletRequest req, HttpServletResponse resp){
        int userId = Integer.valueOf(req.getParameter("userId"));
        String realname = req.getParameter("realname");
        int sex = Integer.valueOf(req.getParameter("sex"));
        String telphone = req.getParameter("phone");
        String idCard = req.getParameter("idCard");
        User user = User.builder().build();
        user.setId(userId);
        user.setIdCard(idCard);
        user.setPhone(telphone);
        user.setSex(sex);
        user.setRealname(realname);
        userService.modifyUser(user);
        HttpSession session = req.getSession();
        session.invalidate();
        return "/manager/login";
    }
}
