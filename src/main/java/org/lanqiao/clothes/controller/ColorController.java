package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.Brand;
import org.lanqiao.clothes.pojo.Color;
import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.User;
import org.lanqiao.clothes.service.IColorService;
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
public class ColorController {
    @Autowired
    IColorService colorService;
    @RequestMapping("/manager/colorList")
    public String colorList(HttpServletRequest req, HttpServletResponse resp, Model model){
        int pageNum = 1;
        if(req.getParameter("currentPage") != null){
            pageNum = Integer.valueOf(req.getParameter("currentPage"));
        }
        int pageSize = 5;
        if(req.getParameter("pageSize") != null){
            pageSize = Integer.valueOf(req.getParameter("pageSize"));
        }

        //查询条件
        String searchColorName = "";
        if(req.getParameter("searchColorName") != null){
            searchColorName = req.getParameter("searchColorName");
        }
        String searchColorState = "-1";
        if(req.getParameter("searchColorState") != null){
            searchColorState = req.getParameter("searchColorState");
        }
        //获取店铺id
        HttpSession session = req.getSession();
        User user  = (User)session.getAttribute("user");
        int storeId = user.getStoreId();
        Condition condition = new Condition();
        condition.setName(searchColorName);
        condition.setState(searchColorState);
        condition.setStoreId(storeId);
        int totalRecords = colorService.getColorCount(condition);
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
        List<Color> colorList = colorService.getColorAll(condition);
        model.addAttribute("colorList",colorList);
        model.addAttribute("pm",pageModel);
        model.addAttribute("condition",condition);
        model.addAttribute("currentPage",pageNum);
        return "/manager/colorList";
    }

    @RequestMapping("manager/addColor")
    public String addColor(HttpServletRequest req, HttpServletResponse resp, Model model){
        Color color = Color.builder().build();
        String name = req.getParameter("colorName");
        int state = Integer.valueOf(req.getParameter("colorState"));
        /*HttpSession session = req.getSession();
        int storeId = Integer.valueOf(session.getAttribute("storeId").toString());*/
        int storeId = 1;
        color.setName(name);
        color.setState(state);
        color.setStoreId(storeId);
        colorService.addColor(color);
        return colorList(req,resp,model);
    }

    @RequestMapping("/manager/deleteColor")
    public String deleteColor(HttpServletRequest req, HttpServletResponse resp, Model model){
        String colorId = req.getParameter("colorId");
        colorService.removeColorById(Integer.valueOf(colorId));
        return colorList(req,resp,model);
    }

    @RequestMapping("/manager/updateColorById")
    public String updateColorById(HttpServletRequest req, HttpServletResponse resp, Model model){
        Color color = Color.builder().build();
        String id = req.getParameter("colorId");
        String name = req.getParameter("colorName");
        int state = Integer.valueOf(req.getParameter("colorState"));
        /*HttpSession session = req.getSession();
        int storeId = Integer.valueOf(session.getAttribute("storeId").toString());*/
        int storeId = 1;
        color.setId(Integer.valueOf(id));
        color.setName(name);
        color.setState(state);
        color.setStoreId(storeId);
        colorService.modifyColorById(color);
        return colorList(req,resp,model);
    }

    @RequestMapping("/manager/selectColorById")
    @ResponseBody
    public Color selectColorById(HttpServletRequest req, HttpServletResponse resp){
        String colorId = req.getParameter("colorId");
        Color color = colorService.getColorById(Integer.valueOf(colorId));
        return color;
    }

    //通过店铺id查询该店铺可用的所有的颜色id和name
    @RequestMapping("/manager/colorSelectedList")
    @ResponseBody
    public List<Color> getColorSelectedList(HttpServletRequest req, HttpServletResponse resp){
        //获取店铺id
        HttpSession session = req.getSession();
        User user  = (User)session.getAttribute("user");
        int storeId = user.getStoreId();
        List<Color> colorList = colorService.getColorSelectedList(storeId);
        return colorList;
    }
}
