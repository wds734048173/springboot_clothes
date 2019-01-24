package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.mapper.ColorMapper;
import org.lanqiao.clothes.pojo.*;
import org.lanqiao.clothes.service.IGoodsService;
import org.lanqiao.clothes.service.impl.ShopCarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShoppingCarController {
    @Autowired
    ShopCarServiceImpl shopCarService;

    @Autowired
    IGoodsService goodsService;
    @Autowired
    ColorMapper colorMapper;
    //购物车查询
    @RequestMapping("/sale/shoppingCar")
    public String goShopingCar(HttpServletRequest req, HttpServletResponse resp, Model model){
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        System.out.println(customer.getId());
        List<ShopingCarItem> carItems=shopCarService.selectAllToList(customer.getId());
        Map<Integer,List<ShopingCarItem>> storeMap = new HashMap<>();
        //将购物车按店铺id分类
        for (ShopingCarItem item:carItems){
            int storeId=item.getStoreId();
            if (storeMap.containsKey(storeId)){
                storeMap.get(storeId).add(item);
            }else {
                List<ShopingCarItem> items=new ArrayList<>();
                items.add(item);
                storeMap.put(storeId,items);
            }
        }
        System.out.println(carItems.size());
        req.setAttribute("carMap",storeMap);
        req.setAttribute("carItems",carItems);
        return "/sale/shoppingcar";
    }

//    //选择变动
//    @RequestMapping("/sale/checkChange")
//    public String checkchange(HttpServletRequest req, HttpServletResponse resp, Model model){
//
//    }
    //商品数量及价格变更
    @RequestMapping("/sale/itemNumChange")
    public String changeNum(HttpServletRequest req, HttpServletResponse resp, Model model){
        int num=Integer.parseInt(req.getParameter("num"));
        int price=Integer.parseInt(req.getParameter("price"));
        int id = Integer.parseInt(req.getParameter("id"));
        ShopingCar car = new ShopingCar();
        car.setId(id);
        car.setNum(num);
        car.setPrice(price);
        shopCarService.updateCar(car);
        List<ShopingCarItem> carItems=shopCarService.selectAllToList(1);
        req.setAttribute("carItems",carItems);
        return "/sale/shoppingcar";
    }
    // 删除指定商品
    @RequestMapping("/sale/itemDelete")
    public String delete(HttpServletRequest req, HttpServletResponse resp, Model model){
        int id = Integer.parseInt(req.getParameter("id"));
        shopCarService.deleteShop(id);
        return "/sale/shoppingcar";
    }
    //添加商品 未完成
    @RequestMapping("/sale/addToCar")
    public String addToCar(HttpServletRequest req, HttpServletResponse resp, Model model){
       int goodsId= Integer.parseInt(req.getParameter("goodsId"));
       int num=Integer.parseInt(req.getParameter("num"));
       int sizeId=Integer.parseInt(req.getParameter("sizeId"));
        int colorId=Integer.parseInt(req.getParameter("colorId"));
        Goods goods  = goodsService.getGoodsById(goodsId);
        int skuId=colorMapper.selectSkuIdBySizeAndColor(sizeId,colorId,goodsId);
//       根据session获取顾客id
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        //将相关内容填入购物车。。
        ShopingCar shopingCar = new ShopingCar();
        shopingCar.setPrice(goods.getMPrice()*num);
        shopingCar.setNum(num);
        shopingCar.setCustomerId(customer.getId());
        shopingCar.setGoodsId(goodsId);
        shopingCar.setStoreId(goods.getStoreId());
        shopingCar.setSkuId(skuId);
       //根据商品id查询商品添加到购物车
        shopCarService.addCar(shopingCar);
       return goShopingCar(req,resp,model);
    }
}
