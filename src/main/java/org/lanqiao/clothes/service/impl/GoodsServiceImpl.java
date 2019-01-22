package org.lanqiao.clothes.service.impl;

import org.lanqiao.clothes.mapper.BrandMapper;
import org.lanqiao.clothes.mapper.GoodsMapper;
import org.lanqiao.clothes.pojo.Brand;
import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Goods;
import org.lanqiao.clothes.service.IGoodsService;
import org.lanqiao.clothes.utils.DataMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    BrandMapper brandMapper;
    @Override
    public void addGoods(Goods goods) {
        goodsMapper.insertGoods(goods);
    }

    @Override
    public void removeGoodsById(int goodsId) {
        goodsMapper.deleteGoodsById(goodsId);
    }


    @Override
    public void updateGoodsById(Goods goods) {
        goodsMapper.updateGoodsById(goods);
    }

    @Override
    public List<Goods> getGoodsAll(Condition condition) {
        int storeId = condition.getStoreId();
        List<Goods> goodsList = goodsMapper.selectGoodsAll(condition);
        //上下架状态
        Map<Integer,String> isshelfMap = DataMapUtil.getIsshelfMap();
        //品牌
        List<Brand> brandList = brandMapper.selectBrandSelectedList(storeId);
        Map<Integer,String> brandMap = new HashMap<>();
        for(Brand brand : brandList){
            brandMap.put(brand.getId(),brand.getName());
        }
        for(Goods goods : goodsList){
            int isshelf = goods.getIsshelf();
            if(isshelfMap.containsKey(isshelf)){
                goods.setIsshelfStr(isshelfMap.get(isshelf));
            }
            int brandId = goods.getBrandId();
            if(brandMap.containsKey(brandId)){
                goods.setBrandName(brandMap.get(brandId));
            }
        }
        return goodsList;
    }

    @Override
    public int getGoodsCount(Condition condition) {
        return goodsMapper.selectGoodsCount(condition);
    }


    @Override
    public Goods getGoodsById(int id) {
        return goodsMapper.selectGoodsById(id);
    }

    @Override
    public void modifyGoodsIsshelf(int storeId, int goodsId, int isshelf) {
        goodsMapper.updateGoodsIsshelf(storeId, goodsId, isshelf);
    }

    /*@Override
    public List<Goods> getGoodsByIds(List<Integer> ids) {
        return goodsMapper.selectGoodsByIds(ids);
    }*/


}
