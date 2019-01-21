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
}
