package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.Store;
import org.lanqiao.clothes.pojo.User;
import org.lanqiao.clothes.service.IStoreService;
import org.lanqiao.clothes.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @Auther: WDS
 * @Date: 2019/1/22 23:26
 * @Description:
 */
@Controller
public class StoreControllr {
    @Autowired
    IUserService userService;

    @Autowired
    IStoreService storeService;
    @RequestMapping("/manager/store")
    public String fanhuistore(){
        return "/manager/storeInfo";
    }
    //    完善商家信息
    @RequestMapping("/manager/storeInfo")
    public String addstoreInfo(HttpServletRequest request){
        System.out.println("============进入完成店铺信息方法。。。");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
//        店铺名称
        String name = request.getParameter("name");
//        营业执照编号
        String bussinessNo = request.getParameter("bussinessNo");
//        店招
        String pic = request.getParameter("pic");
//        店铺地址
        String address = request.getParameter("address");
//        商家电话
        String telphone = request.getParameter("telphone");
//        真实姓名
        String realname = request.getParameter("realname");
//        身份账号
        String idCard = request.getParameter("idCard");

        Store store = new Store();
        store.setName(name);
        store.setBussinessNo(bussinessNo);
        store.setPic(pic);
        store.setAddress(address);
        store.setTelphone(telphone);
        Date date = new Date();
        store.setCtime(date);
//        已查到数据，将数据添加到数据库,id为自增
        storeService.insertStore(store);
//        设置user的storeid与store的自动增长主键id相同
        user.setStoreId(store.getId());
        user.setRealname(realname);
        user.setIdCard(idCard);
//        修改user的storeid
        userService.updateStoreId(user);
//        重新为seeeion中的user赋值
        session.setAttribute("user",user);
        return "/manager/index";
    }
}
