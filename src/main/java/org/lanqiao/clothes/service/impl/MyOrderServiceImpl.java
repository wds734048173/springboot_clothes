package org.lanqiao.clothes.service.impl;

import org.lanqiao.clothes.mapper.MyOrderMapper;
import org.lanqiao.clothes.pojo.Goods;
import org.lanqiao.clothes.pojo.Order;
import org.lanqiao.clothes.pojo.OrderInfo;
import org.lanqiao.clothes.service.IMyOrderService;
import org.lanqiao.clothes.utils.DataMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("myorderService")
public class MyOrderServiceImpl implements IMyOrderService {
@Autowired
MyOrderMapper myOrderMapper;

    @Override
    public List<Order> getMyOrderList(int customerId) {
        List<Order> orderList = myOrderMapper.getMyOrderList(customerId);
       Map<Integer,String> orderStateMap = DataMapUtil.getOrderStateMap();
       for(Order order : orderList){
           int state = order.getState();
           if(orderStateMap.containsKey(state)){
               order.setStateStr(orderStateMap.get(state));
           }
       }
        return orderList;
    }


    @Override
    public List<Order> getMyOrderListByStateId(int state) {

        return myOrderMapper.getMyOrderListByStateId(state);
    }


}
