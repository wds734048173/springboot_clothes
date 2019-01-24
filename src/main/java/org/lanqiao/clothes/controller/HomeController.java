package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Goods;
import org.lanqiao.clothes.pojo.GoodsClass;
import org.lanqiao.clothes.service.IGoodsClassService;
import org.lanqiao.clothes.service.IGoodsService;
import org.lanqiao.clothes.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    IGoodsService goodsService;

    @Autowired
    IGoodsClassService goodsClassService;

    @RequestMapping("/sale/index")
    public String homePage(HttpServletRequest req, HttpServletResponse resp, Model model){
        //获取分类
        List<GoodsClass> goodsClass = goodsClassService.getGoodsClass1List();


        //获取商品列表
        int pageNum = 1;
        int pageSize = 8;
        String searchGoodsName = "";
        String searchGoodsNo = "";
        String searchGoodsBrand = "-1";
        String searchGoodsYear = "-1";
        String searchGoodsIsshelf = "-1";
        Condition condition = new Condition();
        condition.setName(searchGoodsName);
        condition.setState(searchGoodsIsshelf);
        condition.setYear(Integer.valueOf(searchGoodsYear));
        condition.setBrandId(Integer.valueOf(searchGoodsBrand));
        condition.setGoodsNo(searchGoodsNo);
        condition.setStoreId(1);

        int totalRecords = goodsService.getGoodsCount(condition);
        PageModel pageModel = new PageModel(pageNum,totalRecords,pageSize);
        condition.setCurrentPage(pageModel.getStartIndex());
        condition.setPageSize(pageModel.getPageSize());
        List<Goods> goodsList = goodsService.getGoodsAll(condition);
        System.out.println(goodsList.size());
        model.addAttribute("goodsList",goodsList);
        return "/sale/home";
    }
}
