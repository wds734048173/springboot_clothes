package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;
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
}
