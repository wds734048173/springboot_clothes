package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Stock;
import org.lanqiao.clothes.pojo.User;
import org.lanqiao.clothes.service.IStockService;
import org.lanqiao.clothes.utils.PageModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Auther: WDS
 * @Date: 2019/1/17 14:07
 * @Description:商品库存管理
 */
@Controller
public class StockController {

    @Autowired
    IStockService stockService;

    @Autowired
    RabbitTemplate rabbitTemplate;


    //监听器监听指定的Queue(在新增商品的时候，新增0库存的商品库存)
    @RabbitListener(queues="addStock.queue")
    public void addStock(){
        Object o = rabbitTemplate.receiveAndConvert("addStock.queue");
        List<Stock> goodsInfoList = (List<Stock>) o;
    }
    //监听器监听指定的Queue(在采购下单，销售单作废的时候，商品库存增加)
    @RabbitListener(queues="upStock.queue")
    public void upStock(){
        Object o = rabbitTemplate.receiveAndConvert("upStock.queue");
        System.out.println(o.getClass());
        System.out.println(o);
    }
    //监听器监听指定的Queue(在采购单作废，销售单下单的时候，商品库存减少)
    @RabbitListener(queues="downStock.queue")
    public void downStock(){
        Object o = rabbitTemplate.receiveAndConvert("downStock.queue");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    @RequestMapping("/manager/stockList")
    public String stockList(HttpServletRequest req, HttpServletResponse resp, Model model){
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
        Condition condition = new Condition();
        condition.setName(searchGoodsName);
        condition.setStoreId(storeId);
        int totalRecords = stockService.getStockCount(condition);
        //不同操作，不同的当前页设置
        PageModel pm = new PageModel(pageNum,totalRecords,pageSize);

        PageModel pageModel = new PageModel(pageNum,totalRecords,pageSize);
        //分页条件封装
        condition.setCurrentPage(pageModel.getStartIndex());
        condition.setPageSize(pageModel.getPageSize());
        List<Stock> stocksList = stockService.getStockAll(condition);
        model.addAttribute("stockList",stocksList);
        model.addAttribute("pm",pageModel);
        model.addAttribute("condition",condition);
        model.addAttribute("currentPage",pageNum);
        return "/manager/stockList";
    }
    @RequestMapping("/manager/stockInfo")
    public String getStockInfo (HttpServletResponse resp, HttpServletRequest req, Model model){
        //获取店铺id
        HttpSession session = req.getSession();
        User user  = (User)session.getAttribute("user");
        int storeId = user.getStoreId();
        int goodsId = Integer.valueOf(req.getParameter("goodsId"));
        List<Stock> stockList = stockService.getStockInfo(storeId,goodsId);
        model.addAttribute("stockList",stockList);
        return "manager/stockInfo";
    }
}
