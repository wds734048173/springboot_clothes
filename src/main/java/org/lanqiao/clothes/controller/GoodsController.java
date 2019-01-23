package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.*;
import org.lanqiao.clothes.service.*;
import org.lanqiao.clothes.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class GoodsController {
    @Autowired
    IGoodsService goodsService;
    @Autowired
    IBrandService brandService;
    @Autowired
    IGoodsClassService goodsClassService;
    @Autowired
    IColorService colorService;
    @Autowired
    ISizeService sizeService;
    @RequestMapping("/manager/goodsList")
    @Transactional
    public String goodsList(HttpServletRequest req, HttpServletResponse resp, Model model){
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
        String searchGoodsName = "";
        if(req.getParameter("searchGoodsName") != null){
            searchGoodsName = req.getParameter("searchGoodsName");
        }
        String searchGoodsNo = "";
        if(req.getParameter("searchGoodsNo") != null){
            searchGoodsNo = req.getParameter("searchGoodsNo");
        }
        String searchGoodsBrand = "-1";
        if(req.getParameter("searchGoodsBrand") != null){
            searchGoodsBrand = req.getParameter("searchGoodsBrand");
        }
        String searchGoodsYear = "-1";
        if(req.getParameter("searchGoodsYear") != null){
            searchGoodsYear = req.getParameter("searchGoodsYear");
        }
        String searchGoodsIsshelf = "-1";
        if(req.getParameter("searchGoodsIsshelf") != null){
            searchGoodsIsshelf = req.getParameter("searchGoodsIsshelf");
        }
        Condition condition = new Condition();
        condition.setName(searchGoodsName);
        condition.setStoreId(storeId);
        condition.setState(searchGoodsIsshelf);
        condition.setYear(Integer.valueOf(searchGoodsYear));
        condition.setBrandId(Integer.valueOf(searchGoodsBrand));
        condition.setGoodsNo(searchGoodsNo);
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

        //获取品牌查询列表
        List<Brand> brandList = brandService.getBrandSelectedList(storeId);
        //获取一级分类列表
        List<GoodsClass> goodsClass1List = goodsClassService.getGoodsClass1List();
        //通过店铺id获取颜色列表
        List<Color> colorList = colorService.getColorSelectedList(storeId);
        //通过店铺id获取尺码列表
        List<Size> sizeList = sizeService.getSizeSelectedList(storeId);
        //数据封装
        model.addAttribute("goodsList",goodsList);
        model.addAttribute("brandList",brandList);
        model.addAttribute("goodsClass1List",goodsClass1List);
        model.addAttribute("pm",pageModel);
        model.addAttribute("condition",condition);
        model.addAttribute("currentPage",pageNum);
        model.addAttribute("colorList",colorList);
        model.addAttribute("sizeList",sizeList);
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
        int class1Id = Integer.valueOf(req.getParameter("goodsClass1Id"));
        int class2Id = Integer.valueOf(req.getParameter("goodsClass2Id"));
        int class3Id = Integer.valueOf(req.getParameter("goodsClass3Id"));
        int brandId = Integer.valueOf(req.getParameter("goodsbrandId"));
        String year = req.getParameter("goodsYear");
        String season = req.getParameter("goodsSeason");
        String sex = req.getParameter("goodsSex");
        int isshelf = Integer.valueOf(req.getParameter("goodsIsshelf"));
        //获取店铺id
        HttpSession session = req.getSession();
        User user  = (User)session.getAttribute("user");
        int storeId = user.getStoreId();
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
        //获取颜色
        List<Color> colorList = colorService.getColorSelectedList(storeId);
        //获取尺码
        List<Size> sizeList = sizeService.getSizeSelectedList(storeId);
        List<GoodsSKU> goodsSKUList = new ArrayList<>();
        for(Color color : colorList){
            for(Size size : sizeList){
                int colorId = color.getId();
                int sizeId = size.getId();
                GoodsSKU goodsSKU = GoodsSKU.builder().build();
                goodsSKU.setGoodsId(goods.getId());
                goodsSKU.setColorId(colorId);
                goodsSKU.setSizeId(sizeId);
                goodsSKUList.add(goodsSKU);
            }
        }
//        goods.setGoodsSKUList(goodsSKUList);
        goodsService.addGoods(goods);
        goodsService.addGoodsSKUList(goodsSKUList);
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
        int class1Id = Integer.valueOf(req.getParameter("goodsClass1Id"));
        int class2Id = Integer.valueOf(req.getParameter("goodsClass2Id"));
        int class3Id = Integer.valueOf(req.getParameter("goodsClass3Id"));
        int brandId = Integer.valueOf(req.getParameter("goodsbrandId"));
        String id = req.getParameter("goodsId");
        String year = req.getParameter("goodsYear");
        String season = req.getParameter("goodsSeason");
        String sex = req.getParameter("goodsSex");
        int isshelf = Integer.valueOf(req.getParameter("goodsIsshelf"));
        //获取店铺id
        HttpSession session = req.getSession();
        User user  = (User)session.getAttribute("user");
        int storeId = user.getStoreId();
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
    public Goods selectGoodsById(HttpServletRequest req, HttpServletResponse resp){
        String goodsId = req.getParameter("goodsId");
        Goods goods = goodsService.getGoodsById(Integer.valueOf(goodsId));
        /*List<Integer> goodsClassIdList = new ArrayList<>();
        int goodsClass1Id = goods.getClass1Id();
        int goodsClass2Id = goods.getClass2Id();
        int goodsClass3Id = goods.getClass3Id();
        goodsClassIdList.add(goodsClass1Id);
        goodsClassIdList.add(goodsClass2Id);
        goodsClassIdList.add(goodsClass3Id);
        List<GoodsClass> goodsClassList = goodsClassService.getGoodsClassListByIds(goodsClassIdList);
        Map<Integer,String> goodsClassMap = new HashMap<>();
        for(GoodsClass goodsClass : goodsClassList){
            goodsClassMap.put(goodsClass.getId(),goodsClass.getName());
        }
        if(goodsClassMap.containsKey(goodsClass1Id)){
            goods.setClass1Name(goodsClassMap.get(goodsClass1Id));
        }
        if(goodsClassMap.containsKey(goodsClass2Id)){
            goods.setClass2Name(goodsClassMap.get(goodsClass2Id));
        }
        if(goodsClassMap.containsKey(goodsClass3Id)){
            goods.setClass3Name(goodsClassMap.get(goodsClass3Id));
        }
        System.out.println(goods);*/
        return goods;
    }


    @RequestMapping("/manager/updateGoodsIsshelf")
    public String updateGoodsIsshelf(HttpServletRequest req, HttpServletResponse resp, Model model){
        //获取店铺id
        HttpSession session = req.getSession();
        User user  = (User)session.getAttribute("user");
        int storeId = user.getStoreId();
        int goodsId = Integer.valueOf(req.getParameter("goodsId"));
        int isshelf = Integer.valueOf(req.getParameter("isshelf"));
        goodsService.modifyGoodsIsshelf(storeId,goodsId,isshelf);
        return goodsList(req, resp, model);
    }

}
