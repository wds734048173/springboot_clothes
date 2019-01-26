package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Customer;
import org.lanqiao.clothes.service.ICustomerService;
import org.lanqiao.clothes.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    ICustomerService customerService;
    @RequestMapping("/manager/customerList")
    public String customerList(HttpServletRequest req, HttpServletResponse resp, Model model){
        int pageNum = 1;
        if(req.getParameter("currentPage") != null){
            pageNum = Integer.valueOf(req.getParameter("currentPage"));
        }
        int pageSize = 5;
        if(req.getParameter("pageSize") != null){
            pageSize = Integer.valueOf(req.getParameter("pageSize"));
        }

        //查询条件
        String searchCustomerName = "";
        if(req.getParameter("searchCustomerName") != null){
            searchCustomerName = req.getParameter("searchCustomerName");
        }
        String searchCustomerState = "-1";
        if(req.getParameter("searchCustomerState") != null){
            searchCustomerState = req.getParameter("searchCustomerState");
        }
        Condition condition = new Condition();
        condition.setName(searchCustomerName);
        condition.setState(searchCustomerState);
        int totalRecords = customerService.getCustomerCount(condition);
        PageModel pageModel = new PageModel(pageNum,totalRecords,pageSize);
        //分页条件封装
        condition.setCurrentPage(pageModel.getStartIndex());
        condition.setPageSize(pageModel.getPageSize());
        List<Customer> customerList = customerService.getCustomerAll(condition);
        model.addAttribute("customerList",customerList);
        model.addAttribute("pm",pageModel);
        model.addAttribute("condition",condition);
        model.addAttribute("currentPage",pageNum);
        return "/manager/customerList";
    }

    @RequestMapping("/manager/updateCustomer")
    public String updateCustomerState(HttpServletRequest req, HttpServletResponse resp, Model model){
        int state = Integer.valueOf(req.getParameter("state"));
        int customerId = Integer.valueOf(req.getParameter("customerId"));
        customerService.modifyCustomerState(customerId,state);
        return customerList(req,resp,model);
    }
    @RequestMapping("/sale/updateMessage")
    public String updateMessage(HttpServletRequest req, HttpServletResponse resp) throws ParseException {
        Customer customer = Customer.builder().build();
        HttpSession session = req.getSession();
        int id = ((Customer)session.getAttribute("customer")).getId();
        String realname = req.getParameter("realname");
        int sex = Integer.valueOf(req.getParameter("sex"));
        String phone = req.getParameter("phone");
        String hiredate1 = req.getParameter("hiredate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date hiredate = sdf.parse(hiredate1);
        customer.setId(id);
        customer.setSex(sex);
        customer.setRealname(realname);
        customer.setPhone(phone);
        customer.setHiredate(hiredate);
        customerService.updateMessage(customer);
        session.invalidate();
        return "/sale/login";
    }
}
