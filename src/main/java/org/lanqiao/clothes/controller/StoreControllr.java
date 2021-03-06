package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Store;
import org.lanqiao.clothes.pojo.User;
import org.lanqiao.clothes.service.IStoreService;
import org.lanqiao.clothes.service.IUserService;
import org.lanqiao.clothes.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

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

    //    完善商家信息
    @RequestMapping("/manager/storeInfo")
    public String addstoreInfo(HttpServletRequest request, HttpServletResponse response){
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

    //   修改页面跳转
    @RequestMapping("/manager/store")
    public String store(){
        return "/manager/store";
    }

    //    修改店铺信息
    @RequestMapping("/manager/storeUpdate")
    public String storeUpdate(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Store store = (Store)session.getAttribute("store");
//        店铺名称
        String name = request.getParameter("name");
//        营业执照编号
        String bussinessNo = request.getParameter("bussinessNo");
//         店铺地址
        String address = request.getParameter("address");
//        商家联系电话
        String telphone = request.getParameter("telphone");
        store.setName(name);
        store.setBussinessNo(bussinessNo);
        store.setAddress(address);
        store.setTelphone(telphone);
        storeService.updateStore(store);
        session.setAttribute("store",store);
        return "/manager/index";
    }


    @RequestMapping("/manager/storeList")
    public String storeList(HttpServletRequest req, HttpServletResponse resp, Model model){
        int pageNum = 1;
        if(req.getParameter("currentPage") != null){
            pageNum = Integer.valueOf(req.getParameter("currentPage"));
        }
        int pageSize = 5;
        if(req.getParameter("pageSize") != null){
            pageSize = Integer.valueOf(req.getParameter("pageSize"));
        }
        //查询条件
        String searchStoreState = "-1";
        if(req.getParameter("searchStoreState") != null){
            searchStoreState = req.getParameter("searchStoreState");
        }
        Condition condition = new Condition();
        condition.setState(searchStoreState);
        int totalRecords = storeService.getStoreCount(condition);
        PageModel pageModel = new PageModel(pageNum,totalRecords,pageSize);
        //分页条件封装
        condition.setCurrentPage(pageModel.getStartIndex());
        condition.setPageSize(pageModel.getPageSize());
        List<Store> storeList = storeService.getStoreAll(condition);
        model.addAttribute("storeList",storeList);
        model.addAttribute("pm",pageModel);
        model.addAttribute("condition",condition);
        model.addAttribute("currentPage",pageNum);
        return "/manager/storeList";
    }


    @RequestMapping("/manager/updateStoreState")
    public String updateStoreState(HttpServletRequest req, HttpServletResponse resp, Model model){
        int storeId = Integer.valueOf(req.getParameter("storeId"));
        int state = Integer.valueOf(req.getParameter("state"));
        storeService.modifyStoreStateById(storeId,state);
        return storeList(req, resp, model);
    }
}
