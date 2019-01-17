package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.Stock;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @Auther: WDS
 * @Date: 2019/1/17 14:07
 * @Description:商品库存管理
 */
@Controller
public class StockController {

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
}
