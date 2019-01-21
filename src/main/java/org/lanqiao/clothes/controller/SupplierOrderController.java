package org.lanqiao.clothes.controller;


import org.lanqiao.clothes.pojo.*;
import org.lanqiao.clothes.service.ISupplierOrderService;
import org.lanqiao.clothes.utils.PageModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
public class SupplierOrderController {

    @Autowired
    ISupplierOrderService supplierOrderService;

    @Autowired
    RabbitTemplate rabbitTemplate;
    @RequestMapping("/manager/supplierOrderList")
    public String supplierOrderList(HttpServletRequest req, HttpServletResponse resp, Model model){
        int pageNum = 1;
        if(req.getParameter("currentPage") != null){
            pageNum = Integer.valueOf(req.getParameter("currentPage"));
        }
        int pageSize = 5;
        if(req.getParameter("pageSize") != null){
            pageSize = Integer.valueOf(req.getParameter("pageSize"));
        }
        //获取店铺id
        HttpSession session = req.getSession();
        User user  = (User)session.getAttribute("user");
        int storeId = user.getStoreId();
        String searchSupplierOrderNo = "";
        if(req.getParameter("searchSupplierOrderNo") != null){
            searchSupplierOrderNo = req.getParameter("searchSupplierOrderNo");
        }
        String searchSupplierOrderState = "-1";
        if(req.getParameter("searchSupplierOrderState") != null){
            searchSupplierOrderState = req.getParameter("searchSupplierOrderState");
        }
        String searchSupllierId = "-1";
        if(req.getParameter("searchSupllierId") != null){
            searchSupllierId = req.getParameter("searchSupllierId");
        }

        Condition condition = new Condition();
        condition.setState(searchSupplierOrderState);
        condition.setSupplierId(Integer.valueOf(searchSupllierId));
        condition.setNo(searchSupplierOrderNo);
        condition.setStoreId(storeId);
        int totalRecords = supplierOrderService.getSupplierOrderCount(condition);
        //不同操作，不同的当前页设置
        PageModel pm = new PageModel(pageNum,totalRecords,pageSize);
        String method = req.getParameter("method");
        if("add".equals(method)){
            pageNum = pm.getEndPage();
        }else{
            //如果当前页大于总页数，但是排除查询不到数据的情况。当前页等于最大页
            if(pageNum > pm.getTotalPageNum() && pm.getTotalPageNum() != 0){
                pageNum = pm.getTotalPageNum();
            }
        }
        PageModel pageModel = new PageModel(pageNum,totalRecords,pageSize);
        //分页条件封装
        condition.setCurrentPage(pageModel.getStartIndex());
        condition.setPageSize(pageModel.getPageSize());
        List<SupplierOrder> supplierOrderList = supplierOrderService.getSupplierOrderAll(condition);
        model.addAttribute("supplierOrderList",supplierOrderList);
        model.addAttribute("pm",pageModel);
        model.addAttribute("condition",condition);
        model.addAttribute("currentPage",pageNum);
        return "/manager/supplierOrderList";
    }
    /*@RequestMapping("manager/addSupplierOrder")
    public String addSupplierOrder(HttpServletRequest req, HttpServletResponse resp, Model model){
        SupplierOrder supplier_order = SupplierOrder.builder().build();
        String no = req.getParameter("supplier_orderNo");
        int supplierId = Integer.valueOf(req.getParameter("supplier_orderSupplierId"));
        int totalMoney = Integer.valueOf(req.getParameter("supplier_orderTotalMoney"));
        int payMoney = Integer.valueOf(req.getParameter("supplier_orderPayMoney"));
        int state = Integer.valueOf(req.getParameter("supplier_orderState"));

        //获取店铺id
        HttpSession session = req.getSession();
        User user  = (User)session.getAttribute("user");
        int storeId = user.getStoreId();
        supplier_order.setNo(no);
        supplier_order.setSupplierId(supplierId);
        supplier_order.setStoreId(storeId);
        supplier_order.setTotalMoney(totalMoney);
        supplier_order.setPayMoney(payMoney);
        supplier_order.setState(state);
        return supplierOrderList(req,resp,model);
    }*/

    @RequestMapping("/manager/supplierOrderInfo")
    public String getSupplierOrderInfo(HttpServletRequest req, HttpServletResponse resp, Model model){
        //获取店铺id
        HttpSession session = req.getSession();
        User user  = (User)session.getAttribute("user");
        int storeId = user.getStoreId();
        int supplierOrderId = Integer.valueOf(req.getParameter("supplierOrderId"));
        SupplierOrder supplierOrder = supplierOrderService.getSupplierOrderById(supplierOrderId);
        List<SupplierOrderInfo> supplierOrderInfoList = supplierOrderService.getSupplierOrderInfoListById(supplierOrderId,storeId);
        model.addAttribute("supplierOrder",supplierOrder);
        model.addAttribute("supplierOrderInfoList", supplierOrderInfoList);
        return "/manager/supplierOrderInfo";
    }
}
