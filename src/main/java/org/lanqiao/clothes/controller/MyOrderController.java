package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.Customer;
import org.lanqiao.clothes.pojo.Goods;
import org.lanqiao.clothes.pojo.Order;
import org.lanqiao.clothes.pojo.OrderInfo;
import org.lanqiao.clothes.service.IGoodsService;
import org.lanqiao.clothes.service.IMyOrderService;
import org.lanqiao.clothes.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MyOrderController {
    @Autowired
    IOrderService orderService;
    @Autowired
    IGoodsService goodsService;
    @RequestMapping("sale/MyOrder")
    public String getMyOrder(HttpServletRequest req, HttpServletResponse resp, Model model){
        //获取客户id
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        List<Order> orderList = orderService.getOrderId(customer.getId());
        for(Order order : orderList){
            int orderId = order.getId();
            List<OrderInfo> orderInfoList = orderService.salegetOrderInfoListById(orderId,customer.getId());
            order.setOrderInfoList(orderInfoList);
        }
        model.addAttribute("myOrderList",orderList);
        session.setAttribute("customer",customer);
        return "/sale/MyOrder";
    }
}
