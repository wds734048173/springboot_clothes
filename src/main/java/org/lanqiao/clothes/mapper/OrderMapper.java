package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Order;
import org.lanqiao.clothes.pojo.OrderInfo;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface OrderMapper {
    //查询所有的
    public List<Order> selectOrderAll(Condition condition);
    //查询数量
    public int selectOrderCount(Condition condition);
    //获取订单详情
    public Order selectOrderById(int orderId);
    //获取订单详情子表信息
    public List<OrderInfo> selectOrderInfoList(int orderId);
    public void updateOrderStateById(int storeId,int supplierOrderId,int state);

    //=====================前台操作========================
    //增加订单
    public int addOrder(Order order);
    //查找订单
    public List<Order> orderList(Condition condition);
    public int selectCount(Condition condition);
    //更新订单状态
    public void updateOrderState(int state, int id);
    //删除订单
    public void deleteOrder(int id);
    //添加到订单信息表
    public void addOrderInfo(OrderInfo info);
    //删除订单信息
    public void deleteOrderInfo(int oId);
    //查找订单详情
    public List<OrderInfo> selectOrderInfo(int oId);
    //查找订单详情
    public List<Order> getOrderId(@Param(value = "customerId") int id);
}
