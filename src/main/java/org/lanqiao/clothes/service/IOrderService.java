package org.lanqiao.clothes.service;

import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Order;
import org.lanqiao.clothes.pojo.OrderInfo;

import java.util.List;

public interface IOrderService {
    public List<Order> getOrderAll(Condition condition);
    public int getOrderCount(Condition condition);
    public Order getOrderById(int orderId);
    public List<OrderInfo> getOrderInfoListById(int orderId,int storeId);
    public void modifyOrderStateById(int storeId,int orderId,int state);


    //=================前台操作===============
    //增加订单
    public int addOrder(Order order);
    //查找订单
    public List<Order> getOrderList(Condition condition);
    public int selectCount(Condition condition);
    //付款
    public void payOrder(int id);
    //发货
    public void sendOrder(int id);
    //收货
    public void getOrder(int id);
    //取消订单
    public void cancelOrder(int id);
    //删除订单
    public void deleteOrder(int id);
    //添加到订单信息表
    public void addOrderInfo(OrderInfo info);
    //删除订单信息
    public void deleteOrderInfo(int oId);
    //查找订单详情
    public List<OrderInfo> selectOrderInfo(int oId);
    //    前端根据用户查订单
    public List<OrderInfo> salegetOrderInfoListById(int orderId,int customerId);
    public List<Order> getOrderId(int id);
}
