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
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    TerritoryMapper territoryMapper;


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
        //状态
        int state = order.getState();
        Map<Integer,String> orderStateMap = DataMapUtil.getOrderStateMap();
        if(orderStateMap.containsKey(state)){
           order.setStateStr(orderStateMap.get(state));
        }
        //收货地址信息
        int addressId = order.getAddressId();
        Address address = addressMapper.selectAddressById(addressId);
        if(address != null){
            order.setName(address.getName());
            order.setPhone(address.getPhone());
            order.setAddress(address.getAddress());
            List<Integer> addressIds = new ArrayList<>();
            int provinceId = address.getProvinceId();
            int cityId = address.getCityId();
            int areaId = address.getAreaId();
            addressIds.add(provinceId);
            addressIds.add(cityId);
            addressIds.add(areaId);
            List<Territory> territoryList = territoryMapper.selectTerritoryListByIds(addressIds);
            Map<Integer,String> territoryMap = new HashMap<>();
            for(Territory territory : territoryList){
                territoryMap.put(territory.getId(),territory.getTerritoryname());
            }
            if(territoryMap.containsKey(provinceId)){
                order.setProvinceId(provinceId);
                order.setProvinceName(territoryMap.get(provinceId));
            }
            if(territoryMap.containsKey(cityId)){
                order.setCityId(cityId);
                order.setCityName(territoryMap.get(cityId));
            }
            if(territoryMap.containsKey(areaId)){
                order.setAreaId(areaId);
                order.setAreaName(territoryMap.get(areaId));
            }
        }
        return order;
    }

    @Override
    public List<OrderInfo> getOrderInfoListById(int orderId,int storeId) {
        List<OrderInfo> orderInfoList = orderMapper.selectOrderInfoList(orderId);
        //获取商品id
        List<Integer> goodsIds = new ArrayList<>();
        Map<Integer,Goods> goodsMap = new HashMap<>();
        //获取skuid
        List<Integer> skuIds = new ArrayList<>();
        Map<Integer,GoodsSKU> skuMap = new HashMap<>();
        Map<Integer,String> colorMap = new HashMap<>();
        Map<Integer,String> sizeMap = new HashMap<>();
        for(OrderInfo orderInfo : orderInfoList){
            goodsIds.add(orderInfo.getGoodsId());
            skuIds.add(orderInfo.getSkuId());
        }
        //获取商品信息
        if(goodsIds.size()>0){
            List<Goods> goodsList = goodsMapper.selectGoodsByIds(goodsIds);
            for(Goods goods : goodsList){
                goodsMap.put(goods.getId(),goods);
            }
        }

        if(skuIds.size()>0){
            List<GoodsSKU> goodsSKUList = goodsMapper.selectSKUByIds(skuIds);
            for(GoodsSKU goodsSKU : goodsSKUList){
                skuMap.put(goodsSKU.getId(),goodsSKU);
            }
            //获取颜色并做成map
            List<Color> colorList = colorMapper.selectColorSelectedList(storeId);
            for(Color color : colorList){
                colorMap.put(color.getId(),color.getName());
            }
            //获取尺码，并做成map
            List<Size> sizeList = sizeMapper.selectSizeSelectedList(storeId);
            for(Size size : sizeList){
                sizeMap.put(size.getId(),size.getName());
            }
        }

        //配置查询到的数据
        for(OrderInfo orderInfo : orderInfoList){
            int goodsId = orderInfo.getGoodsId();
            if(goodsMap.containsKey(goodsId)){
                orderInfo.setGoodsName(goodsMap.get(goodsId).getName());
                orderInfo.setGoodsPic(goodsMap.get(goodsId).getPic());
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



    //===============前台操作=======================
    @Override
    public int addOrder(Order order) {
        return orderMapper.addOrder(order);
    }

    @Override
    public List<Order> getOrderList(Condition condition) {
        return orderMapper.orderList(condition);
    }

    @Override
    public int selectCount(Condition condition) {
        return orderMapper.selectCount(condition);
    }

    @Override
    public void payOrder(int id) {
        orderMapper.updateOrderState(1,id);
    }

    @Override
    public void sendOrder(int id) {
        orderMapper.updateOrderState(2,id);
    }

    @Override
    public void getOrder(int id) {
        orderMapper.updateOrderState(3,id);
    }

    @Override
    public void cancelOrder(int id) {
        orderMapper.updateOrderState(5,id);
    }

    @Override
    public void deleteOrder(int id) {
        orderMapper.deleteOrder(id);
    }

    @Override
    public void addOrderInfo(OrderInfo info) {
        orderMapper.addOrderInfo(info);
    }

    @Override
    public void deleteOrderInfo(int oId) {
        orderMapper.deleteOrderInfo(oId);
    }

    @Override
    public List<OrderInfo> selectOrderInfo(int oId) {
        return orderMapper.selectOrderInfo(oId);
    }
    //    前端订单信息
    @Override
    public List<OrderInfo> salegetOrderInfoListById(int orderId, int customerId) {

        //        获取关联字表信息，一对多
        List<OrderInfo> orderInfoList = orderMapper.selectOrderInfoList(orderId);
        //获取商品id
        List<Integer> goodsIds = new ArrayList<>();
        Map<Integer,Goods> goodsMap = new HashMap<>();
        //获取skuid
        List<Integer> skuIds = new ArrayList<>();
        Map<Integer,GoodsSKU> skuMap = new HashMap<>();
        Map<Integer,String> colorMap = new HashMap<>();
        Map<Integer,String> sizeMap = new HashMap<>();
//        将orderinfo中的goodsid和skuids放入List
        for(OrderInfo orderInfo : orderInfoList){
            goodsIds.add(orderInfo.getGoodsId());
            skuIds.add(orderInfo.getSkuId());
        }
        //获取商品信息
        if(goodsIds.size()>0){
//            获取
            List<Goods> goodsList = goodsMapper.selectGoodsByIds(goodsIds);
            for(Goods goods : goodsList){
                goodsMap.put(goods.getId(),goods);
            }
        }

        if(skuIds.size()>0){
            List<GoodsSKU> goodsSKUList = goodsMapper.selectSKUByIds(skuIds);
            List<Integer> colorIdList = new ArrayList<>();
            List<Integer> sizeIdList = new ArrayList<>();
            for(GoodsSKU goodsSKU : goodsSKUList){
                skuMap.put(goodsSKU.getId(),goodsSKU);
                colorIdList.add(goodsSKU.getColorId());
                sizeIdList.add(goodsSKU.getSizeId());
            }
            //获取颜色并做成map
            if(colorIdList.size()>0){
                List<Color> colorList = colorMapper.selectColorListByIds(colorIdList);
                for(Color color : colorList){
                    colorMap.put(color.getId(),color.getName());
                }
            }
            if(sizeIdList.size()>0){
                //获取尺码，并做成map
                List<Size> sizeList = sizeMapper.selectSizeListByIds(sizeIdList);
                for(Size size : sizeList){
                    sizeMap.put(size.getId(),size.getName());
                }
            }
        }
        //配置查询到的数据
        for(OrderInfo orderInfo : orderInfoList){
            int goodsId = orderInfo.getGoodsId();
            if(goodsMap.containsKey(goodsId)){
//                设置orderinfo中的物品名字
                orderInfo.setGoodsName(goodsMap.get(goodsId).getName());
                orderInfo.setGoodsPic(goodsMap.get(goodsId).getPic());
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
    public List<Order> getOrderId(int id) {
        List<Order> orderList =  orderMapper.getOrderId(id);
        Map<Integer,String> stateMap = DataMapUtil.getOrderStateMap();
        for(Order order : orderList){
            int state = order.getState();
            if(stateMap.containsKey(state)){
                order.setStateStr(stateMap.get(state));
            }
        }
        return orderList;
    }
}
