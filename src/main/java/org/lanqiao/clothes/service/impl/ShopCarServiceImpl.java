package org.lanqiao.clothes.service.impl;

import org.lanqiao.clothes.mapper.*;
import org.lanqiao.clothes.pojo.*;
import org.lanqiao.clothes.service.IShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopCarServiceImpl implements IShopCarService {
    @Autowired
    ShoppingCarMapper shoppingCarMapper;

    @Autowired
    StoreMapper storeMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    ColorMapper colorMapper;

    @Autowired
    SizeMapper sizeMapper;
    @Override
    public void addCar(ShopingCar car) {
        int customerId = car.getCustomerId();
        int goodsId = car.getGoodsId();
        int skuId = car.getSkuId();
        ShopingCar shopingCar = shoppingCarMapper.selectShoppingCar(customerId,goodsId,skuId);
        if(shopingCar == null){
            shoppingCarMapper.addCar(car);
        }else{
            int num = shopingCar.getNum();
            int addNum = car.getNum();
            car.setNum(num+addNum);
            shoppingCarMapper.updateAddShoppingCar(car);
        }

    }

    @Override
    public void updateCar(ShopingCar car) {
        shoppingCarMapper.updateCar(car);
    }

    @Override
    public void deleteShop(int id) {
        shoppingCarMapper.deleteShop(id);
    }

    @Override
    public List<ShopingCar> selectAllToList(int customerId) {
        List<ShopingCar> shopingCarList = shoppingCarMapper.selectAllToList(customerId);
        //获取店铺名称
        List<Integer> storeIdList = new ArrayList<>();
        Map<Integer,String> storeMap = new HashMap<>();
        //获取商品基础信息
        List<Integer> goodsIdList = new ArrayList<>();
        Map<Integer,Goods> goodsMap = new HashMap<>();
        //获取skuid
        List<Integer> skuIdList = new ArrayList<>();
        List<Integer> colorIdList = new ArrayList<>();
        List<Integer> sizeIdList = new ArrayList<>();
        Map<Integer, GoodsSKU> skuMap = new HashMap<>();
        Map<Integer,String> colorMap = new HashMap<>();
        Map<Integer,String> sizeMap = new HashMap<>();
        for(ShopingCar shopingCar : shopingCarList){
            int storeId = shopingCar.getStoreId();
            int goodsId = shopingCar.getGoodsId();
            int skuId = shopingCar.getSkuId();
            storeIdList.add(storeId);
            goodsIdList.add(goodsId);
            skuIdList.add(skuId);
        }
        //获取店铺信息
        if(storeIdList.size()>0){
            List<Store> storeList = storeMapper.selectStoreListByIds(storeIdList);
            for(Store store : storeList){
                storeMap.put(store.getId(),store.getName());
            }
        }
        //获取商品信息
        if(goodsIdList.size()>0){
            List<Goods> goodsList = goodsMapper.selectGoodsByIds(goodsIdList);
            for(Goods goods : goodsList){
                goodsMap.put(goods.getId(),goods);
            }
        }
        //获取sku信息
        if(skuIdList.size()>0){
            List<GoodsSKU> goodsSKUList = goodsMapper.selectSKUByIds(skuIdList);
            for(GoodsSKU goodsSKU : goodsSKUList){
                int sizeId = goodsSKU.getSizeId();
                int colorId = goodsSKU.getColorId();
                colorIdList.add(colorId);
                sizeIdList.add(sizeId);
                skuMap.put(goodsSKU.getId(),goodsSKU);
            }
            if(colorIdList.size()>0){
                List<Color> colorList = colorMapper.selectColorListByIds(colorIdList);
                for(Color color : colorList){
                    colorMap.put(color.getId(),color.getName());
                }
            }
            if(sizeIdList.size()>0){
                List<Size> sizeList = sizeMapper.selectSizeListByIds(sizeIdList);
                for(Size size : sizeList){
                    sizeMap.put(size.getId(),size.getName());
                }
            }
            for(ShopingCar shopingCar : shopingCarList){
                //店铺名称
                int storeId = shopingCar.getStoreId();
                if(storeMap.containsKey(storeId)){
                    shopingCar.setStoreName(storeMap.get(storeId));
                }
                //商品信息
                int goodsId = shopingCar.getGoodsId();
                if(goodsMap.containsKey(goodsId)){
                    shopingCar.setGoodsName(goodsMap.get(goodsId).getName());
                    shopingCar.setPic(goodsMap.get(goodsId).getPic());
                    shopingCar.setMPrice(goodsMap.get(goodsId).getMPrice());
                    shopingCar.setPPrice(goodsMap.get(goodsId).getPPrice());
                    shopingCar.setSPrice(goodsMap.get(goodsId).getSPrice());
                }
                //商品详细信息
                int skuId = shopingCar.getSkuId();
                if(skuMap.containsKey(skuId)){
                    int colorId = skuMap.get(skuId).getColorId();
                    int sizeId = skuMap.get(skuId).getSizeId();
                    shopingCar.setColorId(colorId);
                    shopingCar.setSkuId(skuId);
                    if(sizeMap.containsKey(sizeId)){
                        shopingCar.setSizeName(sizeMap.get(sizeId));
                    }
                    if(colorMap.containsKey(colorId)){
                        shopingCar.setColorName(colorMap.get(colorId));
                    }
                }
            }
        }
        return shopingCarList;
    }

    @Override
    public void deleteShopByIds(List<Integer> ids) {
        shoppingCarMapper.deleteShoppingCarByIds(ids);
    }
}
