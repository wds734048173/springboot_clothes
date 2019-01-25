package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lanqiao.clothes.pojo.Goods;
import org.lanqiao.clothes.pojo.GoodsSKU;
import org.lanqiao.clothes.pojo.Order;
import org.lanqiao.clothes.pojo.OrderInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MyOrderMapper {
    //获取订单列表
    public   List<Order> getMyOrderList(int customerId);
    //根据订单状态获取订单列表
    public List<Order> getMyOrderListByStateId(int state);
    //获取订单详情
    public List<Goods> selectOrderInfoList(int orderId);
}
