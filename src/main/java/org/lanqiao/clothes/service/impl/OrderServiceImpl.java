package org.lanqiao.clothes.service.impl;

import org.lanqiao.clothes.mapper.*;
import org.lanqiao.clothes.pojo.*;
import org.lanqiao.clothes.service.IOrderService;
import org.lanqiao.clothes.utils.DataMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    ColorMapper colorMapper;
    @Autowired
    SizeMapper sizeMapper;


    @Override
    public List<Order> getOrderAll(Condition condition) {
        List<Order> orderList = orderMapper.selectOrderAll(condition);
        //客户ids
        List<Integer> customerIds = new ArrayList<>();
        //销售订单状态
        Map<Integer,String> orderStateMap = DataMapUtil.getOrderStateMap();
        for(Order order : orderList){
            int customerId = order.getCustomerId();
            customerIds.add(customerId);
            //修改订单状态
            int state = order.getState();
            if(orderStateMap.containsKey(state)){
                order.setStateStr(orderStateMap.get(state));
            }
        }
        //获取客户信息
        if(customerIds.size() > 0){
            List<Customer> customerList = customerMapper.selectCustomerByIds(customerIds);
            Map<Integer,String> customerMap = new HashMap<>();
            for(Customer customer : customerList){
                customerMap.put(customer.getId(),customer.getUsername());
            }
            for(Order order : orderList){
                int customerId = order.getCustomerId();
                if(customerMap.containsKey(customerId)){
                    order.setCustomerName(customerMap.get(customerId));
                }
            }
        }
        return orderList;
    }

    @Override
    public int getOrderCount(Condition condition) {
        return orderMapper.selectOrderCount(condition);
    }

    @Override
    public Order getOrderById(int orderId) {
        Order order = orderMapper.selectOrderById(orderId);
        //客户信息
        int customerId = order.getCustomerId();
        Customer customer = customerMapper.selectCustomerById(customerId);
        order.setCustomerName(customer.getUsername());
        //收货地址信息


        return order;
    }

    @Override
    public List<OrderInfo> getOrderInfoListById(int orderId,int storeId) {
        List<OrderInfo> orderInfoList = orderMapper.selectOrderInfoList(orderId);
        //获取商品id
        List<Integer> goodsIds = new ArrayList<>();
        //获取skuid
        List<Integer> skuIds = new ArrayList<>();
        for(OrderInfo orderInfo : orderInfoList){
            goodsIds.add(orderInfo.getGoodsId());
            skuIds.add(orderInfo.getSkuId());
        }
        //获取商品信息
        List<Goods> goodsList = goodsMapper.selectGoodsByIds(goodsIds);
        Map<Integer,String> goodsMap = new HashMap<>();
        for(Goods goods : goodsList){
            goodsMap.put(goods.getId(),goods.getName());
        }
        //获取颜色并做成map
        List<Color> colorList = colorMapper.selectColorSelectedList(storeId);
        Map<Integer,String> colorMap = new HashMap<>();
        for(Color color : colorList){
            colorMap.put(color.getId(),color.getName());
        }
        //获取尺码，并做成map
        List<Size> sizeList = sizeMapper.selectSizeSelectedList(storeId);
        Map<Integer,String> sizeMap = new HashMap<>();
        for(Size size : sizeList){
            sizeMap.put(size.getId(),size.getName());
        }
        List<GoodsSKU> goodsSKUList = goodsMapper.selectSKUByIds(skuIds);
        Map<Integer,GoodsSKU> skuMap = new HashMap<>();
        for(GoodsSKU goodsSKU : goodsSKUList){
            skuMap.put(goodsSKU.getId(),goodsSKU);
        }
        //配置查询到的数据
        for(OrderInfo orderInfo : orderInfoList){
            int goodsId = orderInfo.getGoodsId();
            if(goodsMap.containsKey(goodsId)){
                orderInfo.setGoodsName(goodsMap.get(goodsId));
            }
            int skuId = orderInfo.getSkuId();
            if(skuMap.containsKey(skuId)){
                orderInfo.setColorId(skuMap.get(skuId).getColorId());
                if(colorMap.containsKey(skuMap.get(skuId).getColorId())){
                    orderInfo.setColorName(colorMap.get(skuMap.get(skuId).getColorId()));
                }
                orderInfo.setSizeId(skuMap.get(skuId).getSizeId());
                if(sizeMap.containsKey(skuMap.get(skuId).getSizeId())){
                    orderInfo.setSizeName(sizeMap.get(skuMap.get(skuId).getSizeId()));
                }
            }
        }
        return orderInfoList;
    }

    @Override
    public void modifyOrderStateById(int storeId, int orderId, int state) {
        orderMapper.updateOrderStateById(storeId,orderId,state);
    }
}
