package org.lanqiao.clothes.service.impl;

import org.lanqiao.clothes.mapper.GoodsMapper;
import org.lanqiao.clothes.mapper.StockMapper;
import org.lanqiao.clothes.mapper.ColorMapper;
import org.lanqiao.clothes.mapper.SizeMapper;
import org.lanqiao.clothes.pojo.*;
import org.lanqiao.clothes.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: WDS
 * @Date: 2019/1/17 15:35
 * @Description:
 */
@Service("stockService")
public class StockServiceImpl implements IStockService {
    @Autowired
    StockMapper stockMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    ColorMapper colorMapper;
    @Autowired
    SizeMapper sizeMapper;
    @Override
    public List<Stock> getStockAll(Condition condition) {
        List<Stock> stockList = stockMapper.selectStockAll(condition);
        /*int storeId = condition.getStoreId();*/
        //从库存列表中获取商品id
        List<Integer> goodsIds = new ArrayList<>();
        for(Stock stock : stockList){
            int goodsId = stock.getGoodsId();
            goodsIds.add(goodsId);
        }
        //通过ids获取商品详情
        if(goodsIds.size()>0){
            List<Goods> goodsList = goodsMapper.selectGoodsByIds(goodsIds);
            Map<Integer,Goods> goodsMap = new HashMap<>();
            for(Goods goods : goodsList){
                goodsMap.put(goods.getId(),goods);
            }
            for(Stock stock : stockList){
                int goodsId = stock.getGoodsId();
                if(goodsMap.containsKey(goodsId)){
                    stock.setGoodsName(goodsMap.get(goodsId).getName());
                    stock.setGoodsPic(goodsMap.get(goodsId).getPic());
                }
            }
        }
        return stockList;
    }

    @Override
    public int getStockCount(Condition condition) {
        return stockMapper.selectStockCount(condition);
    }

    @Override
    public List<Stock> getStockInfo(int storeId,int goodsId) {
        //获取库存列表
        List<Stock> stockList =  stockMapper.selectStockInfo(storeId,goodsId);
        //通过id获取商品详情
        Goods goods = goodsMapper.selectGoodsById(goodsId);
        //获取stockList中的skuIds
        List<Integer> ids = new ArrayList<>();
        for(Stock stock : stockList){
            stock.setGoodsName(goods.getName());
            stock.setGoodsPic(goods.getPic());
            int skuId = stock.getSkuId();
            ids.add(skuId);
        }
        if(ids.size()>0){
            List<GoodsSKU> goodsSKUList = goodsMapper.selectSKUByIds(ids);
            Map<Integer,GoodsSKU> skuMap = new HashMap<>();
            for(GoodsSKU goodsSKU : goodsSKUList){
                skuMap.put(goodsSKU.getId(),goodsSKU);
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
            for(Stock stock : stockList){
                int skuId = stock.getSkuId();
                if(skuMap.containsKey(skuId)){
                    stock.setColorId(skuMap.get(skuId).getColorId());
                    stock.setColorName(colorMap.get(skuMap.get(skuId).getColorId()));
                    stock.setSizeId(skuMap.get(skuId).getSizeId());
                    stock.setSizeName(sizeMap.get(skuMap.get(skuId).getSizeId()));
                }
            }
        }
        return stockList;
    }
}
