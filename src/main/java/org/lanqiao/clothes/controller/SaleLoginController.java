package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.Customer;
import org.lanqiao.clothes.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;


@Controller
public class SaleLoginController {
    @Autowired
    ICustomerService customerService;

    @Autowired
    HomeController homeController;

//    前端登录
    @RequestMapping("/sale/saleLogin")
    public String saleLogin(HttpServletRequest request, HttpServletResponse response, Model model){
        //要验证的验证码
        String rcode = request.getParameter("verifycode_value");
        //验证码中的值
        String s = (String) request.getSession().getAttribute("rcodes");
        if (rcode.equalsIgnoreCase(s)){
            Customer customer = new Customer();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            customer.setUsername(username);
            Customer customer1 = customerService.selectOne(customer);
            if (customer1 ==null){
                request.setAttribute("msg","该用户不存在");
                return "/sale/login";
            }else if (password.equals(customer1.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("customer",customer1);
                return homeController.homePage(request,response,model);
            }else {
                request.setAttribute("msg","密码错误");
                return "/sale/login";
            }
        }else {
            request.setAttribute("msg","验证码错误");
            return "/sale/login";
        }
    }
//    前端登录跳转到注册
    /*@RequestMapping("/sale/cusRegister")
    public String cusregister(){
        return "/sale/cusregister";
    }*/
//    进行注册,并将前段传过来的数据作为Customer类
    @RequestMapping("/sale/cusRegister1")
    public String cusregister1(Customer customer,HttpServletRequest request){
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        if (password.equalsIgnoreCase(repassword)){
            Date date = new Date();
            customer.setCtime(date);
            customerService.addCustomer(customer);
            return "/sale/login";
        }else {
            request.setAttribute("msg","密码不一致");
            return "/sale/cusregister";
        }
    }

    @RequestMapping("/sale/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "/sale/login";
    }

    //    跳转修改页面
    /*@RequestMapping("/sale/salePwd")
    public String salePwd(){
        return "/sale/salepwd";
    }*/
    //    前端忘记密码
    @RequestMapping("/sale/salePwd1")
    public String salepwd1(HttpServletRequest request,HttpServletResponse response){
        //要验证的验证码
        String rcode = request.getParameter("verifycode_value");
        //验证码中的值
        String s = (String) request.getSession().getAttribute("rcodes");
        if (rcode.equalsIgnoreCase(s)){
            String username = request.getParameter("username");
            if (StringUtils.isEmpty(username)){
                request.setAttribute("msg","用户名不能为空");
                return "/sale/salepwd";
            }
            Customer customer = customerService.selectByName(username);
            if (customer == null){
                request.setAttribute("msg","用户名不存在");
                return "/sale/salepwd";
            }else {
                String password = request.getParameter("password");
                String password1 = request.getParameter("password1");
                if (password.equalsIgnoreCase(password1)){
                    customerService.updatePwdByName(password,username);
                    request.setAttribute("msg","修改成功");
                    return "/sale/login";
                }else {
                    request.setAttribute("msg","两次密码不一致");
                    return "/sale/salepwd";
                }
            }
        }else {
            request.setAttribute("msg","验证码不正确");
            return "/sale/salepwd";
        }
    }
    @RequestMapping("/sale/longout")
    public String longout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.invalidate();
        return "/sale/login";

    }
}
