package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Goods;
import org.lanqiao.clothes.service.IGoodsService;
import org.lanqiao.clothes.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class GoodsController {
    @Autowired
    IGoodsService goodsService;
    @RequestMapping("/manager/goodsList")
    public String goodsList(HttpServletRequest req, HttpServletResponse resp, Model model){
        int pageNum = 1;
        if(req.getParameter("currentPage") != null){
            pageNum = Integer.valueOf(req.getParameter("currentPage"));
        }
        int pageSize = 5;
        if(req.getParameter("pageSize") != null){
            pageSize = Integer.valueOf(req.getParameter("pageSize"));
        }

        //查询条件
        String searchGoodsName = "";
        if(req.getParameter("searchGoodsName") != null){
            searchGoodsName = req.getParameter("searchGoodsName");
        }
        Condition condition = new Condition();
        condition.setName(searchGoodsName);
       int totalRecords = goodsService.getGoodsCount(condition);
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
        List<Goods> goodsList = goodsService.getGoodsAll(condition);
        model.addAttribute("goodsList",goodsList);
        model.addAttribute("pm",pageModel);
        model.addAttribute("condition",condition);
        model.addAttribute("currentPage",pageNum);
        return "/manager/goodsList";
    }

    @RequestMapping("/manager/addGoods")
    public String addGoods(HttpServletRequest req, HttpServletResponse resp, Model model){
        Goods goods = Goods.builder().build();
        String no = req.getParameter("goodsNo");
        String name = req.getParameter("goodsName");
        int pPrice = Integer.valueOf(req.getParameter("goodspPrice"));
        int sPrice = Integer.valueOf(req.getParameter("goodssPrice"));
        int mPrice = Integer.valueOf(req.getParameter("goodsmPrice"));
//        int class1Id = Integer.valueOf(req.getParameter("goodsclass1Id"));
//        int class2Id = Integer.valueOf(req.getParameter("goodsclass2Id"));
//        int class3Id = Integer.valueOf(req.getParameter("goodsclass3Id"));
//        int brandId = Integer.valueOf(req.getParameter("goodsbrandId"));
        int class1Id = 1;
        int class2Id = 2;
        int class3Id = 3;
        int brandId = 1;
        String year = req.getParameter("goodsYear");
        String season = req.getParameter("goodsSeason");
        String sex = req.getParameter("goodsSex");
//        int storeId = Integer.valueOf(req.getParameter("goodsStoreId"));
        int storeId = 1;
        int isshelf = Integer.valueOf(req.getParameter("goodsIsshelf"));
        /*HttpSession session = req.getSession();
        int storeId = Integer.valueOf(session.getAttribute("storeId").toString());*/
        goods.setNo(no);
        goods.setName(name);
        goods.setPPrice(pPrice);
        goods.setSPrice(sPrice);
        goods.setMPrice(mPrice);
        goods.setClass1Id(class1Id);
        goods.setClass2Id(class2Id);
        goods.setClass3Id(class3Id);
        goods.setBrandId(brandId);
        goods.setYear(year);
        goods.setSeason(season);
        goods.setSex(sex);
        goods.setStoreId(storeId);
        goods.setIsshelf(isshelf);
        goodsService.addGoods(goods);
        return goodsList(req,resp,model);
    }


    @RequestMapping("/manager/updateGoodsById")
    public String updateGoodsById(HttpServletRequest req, HttpServletResponse resp, Model model){
        Goods goods = Goods.builder().build();
        String no = req.getParameter("goodsNo");
        String name = req.getParameter("goodsName");
        int pPrice = Integer.valueOf(req.getParameter("goodspPrice"));
        int sPrice = Integer.valueOf(req.getParameter("goodssPrice"));
        int mPrice = Integer.valueOf(req.getParameter("goodsmPrice"));
//        int class1Id = Integer.valueOf(req.getParameter("goodsclass1Id"));
//        int class2Id = Integer.valueOf(req.getParameter("goodsclass2Id"));
//        int class3Id = Integer.valueOf(req.getParameter("goodsclass3Id"));
//        int brandId = Integer.valueOf(req.getParameter("goodsbrandId"));
        int class1Id = 1;
        int class2Id = 2;
        int class3Id = 3;
        int brandId = 1;
        String id = req.getParameter("goodsId");
        String year = req.getParameter("goodsYear");
        String season = req.getParameter("goodsSeason");
        String sex = req.getParameter("goodsSex");
        int storeId = Integer.valueOf(req.getParameter("goodsStoreId"));
        int isshelf = Integer.valueOf(req.getParameter("goodsIsshelf"));
        /*HttpSession session = req.getSession();
        int storeId = Integer.valueOf(session.getAttribute("storeId").toString());*/
        goods.setId(Integer.valueOf(id));
        goods.setNo(no);
        goods.setName(name);
        goods.setPPrice(pPrice);
        goods.setSPrice(sPrice);
        goods.setMPrice(mPrice);
        goods.setClass1Id(class1Id);
        goods.setClass2Id(class2Id);
        goods.setClass3Id(class3Id);
        goods.setBrandId(brandId);
        goods.setYear(year);
        goods.setSeason(season);
        goods.setSex(sex);
        goods.setStoreId(storeId);
        goods.setIsshelf(isshelf);
        goodsService.updateGoodsById(goods);
        return goodsList(req,resp,model);
    }

    @RequestMapping("/manager/selectGoodsById")
    @ResponseBody
    public Goods selectColorById(HttpServletRequest req, HttpServletResponse resp){
        String goodsId = req.getParameter("goodsId");
        Goods goods = goodsService.getGoodsById(Integer.valueOf(goodsId));
        return goods;
    }

}
