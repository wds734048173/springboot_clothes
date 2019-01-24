package org.lanqiao.clothes.service.impl;

import org.lanqiao.clothes.mapper.*;
import org.lanqiao.clothes.pojo.*;
import org.lanqiao.clothes.service.ISupplierOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("supplier_orderService")
public class SupplierOrderServiceImpl implements ISupplierOrderService {

    @Autowired
    SupplierOrderMapper supplierOrderMapper;
    @Autowired
    SupplierMapper supplierMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    ColorMapper colorMapper;
    @Autowired
    SizeMapper sizeMapper;
    @Override
    public List<SupplierOrder> getSupplierOrderAll(Condition condition) {
        List<SupplierOrder> supplierOrderList = supplierOrderMapper.getSupplierOrderAll(condition);
        //供应商ids查询供应商信息
        List<Integer> supplierIds = new ArrayList<>();
        for(SupplierOrder supplierOrder : supplierOrderList){
            int supplierId = supplierOrder.getSupplierId();
            int state = supplierOrder.getState();
            if(state == 0){
                supplierOrder.setStateStr("未审核");
            }else if(state == 1){
                supplierOrder.setStateStr("已审核");
            }else if(state == 2){
                supplierOrder.setStateStr("已作废");
            }
            supplierIds.add(supplierId);
        }
        if(supplierIds.size() > 0){
            List<Supplier> supplierList = supplierMapper.selectSupplierByIds(supplierIds);
            Map<Integer,String> supplierMap = new HashMap<>();
            for(Supplier supplier : supplierList){
                supplierMap.put(supplier.getId(),supplier.getName());
            }
            for(SupplierOrder supplierOrder : supplierOrderList){
                int supplierId = supplierOrder.getSupplierId();
                if(supplierMap.containsKey(supplierId)){
                    supplierOrder.setSupplierName(supplierMap.get(supplierId));
                }
            }
        }
        return supplierOrderList;
    }

    @Override
    public int getSupplierOrderCount(Condition condition) {
        return supplierOrderMapper.selectSupplierOrderCount(condition);
    }

    @Override
    public void addSupplierOrder(SupplierOrder supplierOrder) {
        supplierOrderMapper.insertSupplierOrder(supplierOrder);
    }

    @Override
    public SupplierOrder getSupplierOrderById(int supplierOrderId) {
        SupplierOrder supplierOrder = supplierOrderMapper.selectSupplierOrderById(supplierOrderId);
        //供应商id
        int supplierId = supplierOrder.getSupplierId();
        Supplier supplier = supplierMapper.selectSupplierById(supplierId);
        supplierOrder.setSupplierName(supplier.getName());
        //状态
        int state = supplierOrder.getState();
        if(state == 0){
            supplierOrder.setStateStr("未审核");
        }else if(state == 1){
            supplierOrder.setStateStr("已审核");
        }else if(state == 2){
            supplierOrder.setStateStr("已作废");
        }
        return supplierOrder;
    }

    @Override
    public List<SupplierOrderInfo> getSupplierOrderInfoListById(int supplierOrderId,int storeId) {
        List<SupplierOrderInfo> supplierOrderInfoList = supplierOrderMapper.selectSupplierOrderinfoList(supplierOrderId);
        //获取商品id
        List<Integer> goodsIds = new ArrayList<>();
        //获取skuid
        List<Integer> skuIds = new ArrayList<>();
        for(SupplierOrderInfo supplierOrderInfo : supplierOrderInfoList){
            goodsIds.add(supplierOrderInfo.getGoodsId());
            skuIds.add(supplierOrderInfo.getSkuId());
        }
        //获取商品信息
        Map<Integer,String> goodsMap = new HashMap<>();
        if(goodsIds.size()>0){
            List<Goods> goodsList = goodsMapper.selectGoodsByIds(goodsIds);
            for(Goods goods : goodsList){
                goodsMap.put(goods.getId(),goods.getName());
            }
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
        Map<Integer,GoodsSKU> skuMap = new HashMap<>();
        if(skuIds.size()>0){
            List<GoodsSKU> goodsSKUList = goodsMapper.selectSKUByIds(skuIds);
            for(GoodsSKU goodsSKU : goodsSKUList){
                skuMap.put(goodsSKU.getId(),goodsSKU);
            }
        }
        //配置查询到的数据
        for(SupplierOrderInfo supplierOrderInfo : supplierOrderInfoList){
            int goodsId = supplierOrderInfo.getGoodsId();
            if(goodsMap.containsKey(goodsId)){
                supplierOrderInfo.setGoodsName(goodsMap.get(goodsId));
            }
            int skuId = supplierOrderInfo.getSkuId();
            if(skuMap.containsKey(skuId)){
                supplierOrderInfo.setColorId(skuMap.get(skuId).getColorId());
                if(colorMap.containsKey(skuMap.get(skuId).getColorId())){
                    supplierOrderInfo.setColorName(colorMap.get(skuMap.get(skuId).getColorId()));
                }
                supplierOrderInfo.setSizeId(skuMap.get(skuId).getSizeId());
                if(sizeMap.containsKey(skuMap.get(skuId).getSizeId())){
                    supplierOrderInfo.setSizeName(sizeMap.get(skuMap.get(skuId).getSizeId()));
                }
            }
        }
        return supplierOrderInfoList;
    }

    @Override
    public void modifySupplierOrderStateById(int storeId, int supplierOrderId, int state) {
        supplierOrderMapper.updateSupplierOrderStateById(storeId,supplierOrderId,state);
    }
}
