package org.lanqiao.clothes.service.impl;

import org.lanqiao.clothes.mapper.GoodsMapper;
import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Goods;
import org.lanqiao.clothes.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    GoodsMapper goodsMapper;
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
        return goodsMapper.selectGoodsAll(condition);
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
