package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Order;
import org.lanqiao.clothes.pojo.OrderInfo;
import org.lanqiao.clothes.pojo.User;
import org.lanqiao.clothes.service.IOrderService;
import org.lanqiao.clothes.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class OrderController {
    @Autowired
    IOrderService orderService;
    @RequestMapping("/manager/orderList")
    public String orderList(HttpServletRequest req, HttpServletResponse resp, Model model){
        int pageNum = 1;
        if(req.getParameter("currentPage") != null){
            pageNum = Integer.valueOf(req.getParameter("currentPage"));
        }
        int pageSize = 5;
        if(req.getParameter("pageSize") != null){
            pageSize = Integer.valueOf(req.getParameter("pageSize"));
        }

        //查询条件
        String searchOrderState = "-1";
        if(req.getParameter("searchOrderState") != null){
            searchOrderState = req.getParameter("searchOrderState");
        }
        //获取店铺id
        HttpSession session = req.getSession();
        User user  = (User)session.getAttribute("user");
        int storeId = user.getStoreId();
        Condition condition = new Condition();
        condition.setState(searchOrderState);
        condition.setStoreId(storeId);
        int totalRecords = orderService.getOrderCount(condition);
        PageModel pageModel = new PageModel(pageNum,totalRecords,pageSize);
        //分页条件封装
        condition.setCurrentPage(pageModel.getStartIndex());
        condition.setPageSize(pageModel.getPageSize());
        List<Order> orderList = orderService.getOrderAll(condition);
        model.addAttribute("orderList",orderList);
        model.addAttribute("pm",pageModel);
        model.addAttribute("condition",condition);
        model.addAttribute("currentPage",pageNum);
        return "/manager/orderList";
    }

    @RequestMapping("/manager/orderInfo")
    public String getOrderInfo(HttpServletRequest req, HttpServletResponse resp, Model model){
        //获取店铺id
        HttpSession session = req.getSession();
        User user  = (User)session.getAttribute("user");
        int storeId = user.getStoreId();
        int orderId = Integer.valueOf(req.getParameter("orderId"));
        Order order = orderService.getOrderById(orderId);
        List<OrderInfo> orderInfoList = orderService.getOrderInfoListById(orderId,storeId);
        model.addAttribute("order",order);
        model.addAttribute("orderInfoList",orderInfoList);
        return "/manager/orderInfo";
    }
}
