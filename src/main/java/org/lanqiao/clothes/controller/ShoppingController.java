package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.*;
import org.lanqiao.clothes.service.IColorService;
import org.lanqiao.clothes.service.ICommentService;
import org.lanqiao.clothes.service.IGoodsService;
import org.lanqiao.clothes.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShoppingController {
    @Autowired
    IGoodsService goodsService;
    @Autowired
    IColorService colorService;
    @Autowired
    ISizeService sizeService;
    @Autowired
ICommentService commentService;
    @RequestMapping("/sale/detalis")
    public String goodsList(HttpServletRequest req, HttpServletResponse resp, Model model){
        int goodsId = Integer.valueOf(req.getParameter("goodsId"));
        //获取商品的评论信息------------------start
        //获取商品id

        //根据评价等级查询评价
        String searchCommentGrade = "-1";
        if (req.getParameter("searchCommentGrade")!=null){
            searchCommentGrade = req.getParameter("searchCommentGrade");
        }
        Condition condition =new Condition();
        condition.setGrade(searchCommentGrade);
        condition.setGoodsId(goodsId);
        int totalRecords = commentService.getAllCountCommentGoodsId(condition);
        List<Comment> commentListByGoodsId = commentService.getAllCommentByGoodsId(condition);
        model.addAttribute("commentListByGoodsId",commentListByGoodsId);
        model.addAttribute("condition",condition);
        model.addAttribute("count",totalRecords);
        //获取商品的评论信息------------------end


        //获取商品详情------------start
        Goods goods = goodsService.getGoodsById(goodsId);
        List<GoodsSKU> goodsSKUList = goodsService.getGoodsSKUList(goodsId);
        List<Integer> colorIds = new ArrayList<>();
        List<Integer> sizeIds = new ArrayList<>();
        for(GoodsSKU goodsSKU : goodsSKUList){
            int colorId = goodsSKU.getColorId();
            int sizeId = goodsSKU.getSizeId();
            colorIds.add(colorId);
            sizeIds.add(sizeId);
        }

        Map<Integer,String> colorMap = new HashMap<>();
        Map<Integer,String> sizeMap= new HashMap<>();
        if(colorIds.size()>0){
            List<Color> colorList = colorService.getColorListByIds(colorIds);
            for(Color color : colorList){
                colorMap.put(color.getId(),color.getName());
            }
        }
        if(sizeIds.size()>0){
            List<Size> sizeList = sizeService.getSizeListByIds(sizeIds);
            for (Size size : sizeList){
                sizeMap.put(size.getId(),size.getName());
            }
        }
        model.addAttribute("goods",goods);
        model.addAttribute("sizeMap",sizeMap);
        model.addAttribute("colorMap",colorMap);
        //获取商品详情------------end
        return "/sale/detalis";
    }
    @RequestMapping("/sale/comments")
    public String getComment(HttpServletRequest req, HttpServletResponse resp, Model model){
        //获取商品id
//        int goodsId=1;
        int goodsId = Integer.valueOf(req.getParameter("goodsId"));
        System.out.println(goodsId+"---------------------------");

        //根据评价等级查询评价
        String searchCommentGrade = "-1";
        if (req.getParameter("searchCommentGrade")!=null){
            searchCommentGrade = req.getParameter("searchCommentGrade");
        }
        Condition condition =new Condition();
        condition.setGrade(searchCommentGrade);
        condition.setGoodsId(goodsId);
        int totalRecords = commentService.getAllCountCommentGoodsId(condition);
        List<Comment> commentListByGoodsId = commentService.getAllCommentByGoodsId(condition);
        model.addAttribute("commentListByGoodsId",commentListByGoodsId);
        model.addAttribute("condition",condition);
        model.addAttribute("count",totalRecords);
        return "/sale/comment";
    }


}
