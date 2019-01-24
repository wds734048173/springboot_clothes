package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Size;
import org.lanqiao.clothes.pojo.User;
import org.lanqiao.clothes.service.ISizeService;
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
public class SizeController {
    @Autowired
    ISizeService sizeService;

//    查询
    @RequestMapping("/manager/sizeList")
    public String sizeList(HttpServletRequest req, HttpServletResponse resp, Model model){
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
        String searchSizeName = "";
        if (req.getParameter("searchSizeName")!=null){
            searchSizeName = req.getParameter("searchSizeName");
        }
        String searchSizeState ="-1";
        if (req.getParameter("searchSizeState")!=null){
            searchSizeState = req.getParameter("searchSizeState");
        }
        Condition condition = new Condition();
        condition.setName(searchSizeName);
        condition.setState(searchSizeState);
        condition.setStoreId(storeId);
        int totalRecords = sizeService.getSizeCount(condition);
        //不同的操作不同的当前页面
        PageModel pm=new PageModel(pageNum,totalRecords,pageSize);
        String method = req.getParameter("method");
        if ("add".equals(method)){
            pageNum = pm.getEndPage();
        }else {
            //如果当前页大于总页数，但是排除查询不到数据的情况。当前页等于最大页
            if(pageNum > pm.getTotalPageNum() && pm.getTotalPageNum() != 0){
                pageNum = pm.getTotalPageNum();
            }
        }
        PageModel pageModel = new PageModel(pageNum,totalRecords,pageSize);
        //分页封装条件
        condition.setCurrentPage(pageModel.getStartIndex());
        condition.setPageSize(pageModel.getPageSize());
        List<Size> sizeList  = sizeService.getSizeAll(condition);
        model.addAttribute("sizeList",sizeList);
        model.addAttribute("pm",pageModel);
        model.addAttribute("condition",condition);
        model.addAttribute("currentPage",pageNum);
        return "/manager/sizeList";
    }
    //增加一条
    @RequestMapping("/manager/addSize")
    public String addSizre(HttpServletRequest req, HttpServletResponse resp, Model model) {
        Size size =Size.builder().build();
        String name = req.getParameter("sizeName");
        int state = Integer.valueOf(req.getParameter("sizeState"));
        //获取店铺id
        HttpSession session = req.getSession();
        User user  = (User)session.getAttribute("user");
        int storeId = user.getStoreId();

        size.setName(name);
        size.setState(state);
        size.setStoreId(storeId);
        sizeService.addSize(size);
        return sizeList(req,resp,model);
    }
    //删除
    @RequestMapping("/manager/deleteSize")
    public String deleteSize(HttpServletRequest req, HttpServletResponse resp, Model model){
       String sizeId = req.getParameter("sizeId");
       sizeService.removeSize(Integer.valueOf(sizeId));
       return sizeList(req,resp,model);
    }
    //修改
    @RequestMapping("/manager/updateSizeById")
    public String updateSizeById(HttpServletRequest req, HttpServletResponse resp, Model model){
        Size size =Size.builder().build();
        String id = req.getParameter("sizeId");
        String name = req.getParameter("sizeName");
        int state = Integer.valueOf(req.getParameter("sizeState"));
        //获取店铺id
        HttpSession session = req.getSession();
        User user  = (User)session.getAttribute("user");
        int storeId = user.getStoreId();

        size.setId(Integer.valueOf(id));
        size.setName(name);
        size.setState(state);
        size.setStoreId(storeId);
        sizeService.modifySize(size);
        return sizeList(req,resp,model);
    }


    //通过店铺id查询该店铺可用的所有的颜色id和name
    @RequestMapping("/manager/sizeSelectedList")
    @ResponseBody
    public List<Size> getSizeSelectedList(HttpServletRequest req, HttpServletResponse resp){
        //获取店铺id
        HttpSession session = req.getSession();
        User user  = (User)session.getAttribute("user");
        int storeId = user.getStoreId();
        List<Size> sizeList = sizeService.getSizeSelectedList(storeId);
        return sizeList;
    }

    @RequestMapping("/manager/selectSizeById")
    @ResponseBody
    public Size selectSizeById(HttpServletRequest req, HttpServletResponse resp){
        int sizeId = Integer.valueOf(req.getParameter("sizeId"));
        System.out.println("sizeId=================="+sizeId);
        Size size = sizeService.getSizeById(sizeId);
        return size;
    }
}
