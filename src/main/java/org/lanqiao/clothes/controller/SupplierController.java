package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Supplier;
import org.lanqiao.clothes.pojo.User;
import org.lanqiao.clothes.service.ISupplierService;
import org.lanqiao.clothes.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class SupplierController {
    @Autowired
    ISupplierService supplierService;
    @RequestMapping("/manager/supplierList")
    public String supplierList(HttpServletRequest req, HttpServletResponse resp, Model model){
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

        //查询条件
        String searchSupplierName = "";
        if(req.getParameter("searchSupplierName") != null){
            searchSupplierName = req.getParameter("searchSupplierName");
        }
        String searchSupplierState = "-1";
        if(req.getParameter("searchSupplierState") != null){
            searchSupplierState = req.getParameter("searchSupplierState");
        }
        Condition condition = new Condition();
        condition.setName(searchSupplierName);
        condition.setState(searchSupplierState);
        condition.setStoreId(storeId);
        int totalRecords = supplierService.getSupplierCount(condition);
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
        List<Supplier> supplierList = supplierService.getSupplierAll(condition);
        model.addAttribute("supplierList",supplierList);
        model.addAttribute("pm",pageModel);
        model.addAttribute("condition",condition);
        model.addAttribute("currentPage",pageNum);
        return "/manager/supplierList";
    }

    @RequestMapping("manager/addSupplier")
    public String addColor(HttpServletRequest req, HttpServletResponse resp, Model model){
        Supplier supplier = Supplier.builder().build();
        String name = req.getParameter("supplierName");
        int sex = Integer.valueOf(req.getParameter("supplierSex"));
        String telphone = req.getParameter("supplierTelphone");
        String address = req.getParameter("supplierAddress");
        int state = Integer.valueOf(req.getParameter("supplierState"));
        int storeId = 1;
        supplier.setName(name);
        supplier.setState(state);
        supplier.setStoreId(storeId);
        supplier.setSex(sex);
        supplier.setTelphone(telphone);
        supplier.setAddress(address);
        supplierService.addSupplier(supplier);
        return supplierList(req,resp,model);
    }

    @RequestMapping("/manager/deleteSupplier")
    public String deleteColor(HttpServletRequest req, HttpServletResponse resp, Model model){
        String supplierId = req.getParameter("supplierId");
        supplierService.removeSupplierById(Integer.valueOf(supplierId));
        return supplierList(req,resp,model);
    }

    @RequestMapping("/manager/updateSupplierById")
    public String updateSupplierById(HttpServletRequest req, HttpServletResponse resp, Model model){
        Supplier supplier = Supplier.builder().build();
        String id = req.getParameter("supplierId");
        String name = req.getParameter("supplierName");
        int sex = Integer.valueOf(req.getParameter("supplierSex"));
        String telphone = req.getParameter("supplierTelphone");
        String address = req.getParameter("supplierAddress");
        int state = Integer.valueOf(req.getParameter("supplierState"));
        /*HttpSession session = req.getSession();
        int storeId = Integer.valueOf(session.getAttribute("storeId").toString());*/
        int storeId = 1;
        supplier.setId(Integer.valueOf(id));
        supplier.setName(name);
        supplier.setState(state);
        supplier.setStoreId(storeId);
        supplier.setSex(sex);
        supplier.setTelphone(telphone);
        supplier.setAddress(address);
        supplierService.modifySupplierById(supplier);
        return supplierList(req,resp,model);
    }

    @RequestMapping("/manager/selectSupplierById")
    @ResponseBody
    public Supplier selectColorById(HttpServletRequest req, HttpServletResponse resp){
        String supplierId = req.getParameter("supplierId");
        Supplier supplier = supplierService.getSupplierById(Integer.valueOf(supplierId));
        return supplier;
    }
}


