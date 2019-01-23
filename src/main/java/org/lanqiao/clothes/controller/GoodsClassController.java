package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.GoodsClass;
import org.lanqiao.clothes.pojo.User;
import org.lanqiao.clothes.service.IGoodsClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: WDS
 * @Date: 2019/1/16 11:05
 * @Description:商品分类管理
 */
@Controller
public class GoodsClassController {

    @Autowired
    IGoodsClassService goodsClassService;
    @RequestMapping("/manager/goodsClassList")
    @ResponseBody
    public List<GoodsClass> goodsClassList(HttpServletRequest req, HttpServletResponse resp){
        List<GoodsClass> goodsClassList = goodsClassService.getGoodsClassTree();
        return goodsClassList;
    }

    //商品列表页面，获取下一级列表
    @RequestMapping("/manager/selectGoodsClassById")
    @ResponseBody
    public List<GoodsClass> selectGoodsClass(HttpServletRequest req, HttpServletResponse resp){
        int goodsClassId = Integer.valueOf(req.getParameter("goodsClassId"));
        List<GoodsClass> goodsClassList = goodsClassService.getNextGoodsClassList(goodsClassId);
        return goodsClassList;
    }
}
