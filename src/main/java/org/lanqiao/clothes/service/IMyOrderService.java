package org.lanqiao.clothes.service;

import org.lanqiao.clothes.pojo.Goods;
import org.lanqiao.clothes.pojo.Order;
import org.lanqiao.clothes.pojo.OrderInfo;

import java.util.List;

public interface IMyOrderService {
    //获取订单列表
    public List<Order> getMyOrderList(int customerId);
    //获取商品图片和名称
//    public List<Goods> getGoodsInfo(int goodsId);
    //根据订单状态获取订单列表
    public List<Order> getMyOrderListByStateId(int state);

}
