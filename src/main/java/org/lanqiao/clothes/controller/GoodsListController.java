package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.*;
import org.lanqiao.clothes.service.IGoodsListService;
import org.lanqiao.clothes.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GoodsListController {
    @Autowired
    IGoodsListService goodsListService;

    @RequestMapping("/sale/goodsList")
    public String goodsList(HttpServletRequest req, HttpServletResponse resp, Model model){
        /*int pageNum = 1;
        if(req.getParameter("currentPage") != null){
            pageNum = Integer.valueOf(req.getParameter("currentPage"));
        }
        int pageSize = 12;
        if(req.getParameter("pageSize") != null){
            pageSize = Integer.valueOf(req.getParameter("pageSize"));
        }*/
        int classId = Integer.valueOf(req.getParameter("classId"));
        Condition condition = new Condition();
        condition.setGoodsClass1Id(classId);
        int totalRecords = goodsListService.getGoodsListCount(condition);
        //不同操作，不同的当前页设置
//        PageModel pm = new PageModel(pageNum,totalRecords,pageSize);
//
//        PageModel pageModel = new PageModel(pageNum,totalRecords,pageSize);
       //分页条件封装
//        condition.setCurrentPage(pageModel.getStartIndex());
//        condition.setPageSize(pageModel.getPageSize());

        List<GoodsList> goodsList = goodsListService.getGoodsListSelectedList(condition);
        List<Brand> brandList = goodsListService.getBrandListSelectedList();
        List<GoodsClass> goodsClassList=goodsListService.getGoodsClassSelectedList();
        model.addAttribute("goodsList",goodsList);
        model.addAttribute("brandList",brandList);
        model.addAttribute("goodsClassList",goodsClassList);
//        model.addAttribute("pm",pageModel);
//        model.addAttribute("condition",condition);
//        model.addAttribute("currentPage",pageNum);
        return "/sale/goodsList";
    }

    @RequestMapping("/sale/brandsList")
    public String brandsList(HttpServletRequest req, HttpServletResponse resp, Model model){
        List<Goods> classes =new ArrayList<>();
        if (req.getParameter("classId")!=null){
            int classId=Integer.parseInt(req.getParameter("classId"));
            classes= goodsListService.getGoodsListByClassName(classId);

        }
        List<Goods> brands = new ArrayList<>();
        if (req.getParameter("brandId")!=null){
            int brandId=Integer.parseInt(req.getParameter("brandId"));
            brands=goodsListService.getGoodsListByBrandName(brandId);

        }
        model.addAttribute("classList",classes);
        model.addAttribute("brandList",brands);
        return "/sale/brand";
    }
}
