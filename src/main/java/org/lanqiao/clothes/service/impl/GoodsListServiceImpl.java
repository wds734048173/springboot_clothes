package org.lanqiao.clothes.service.impl;

import org.lanqiao.clothes.mapper.GoodsListMapper;
import org.lanqiao.clothes.pojo.*;
import org.lanqiao.clothes.service.IGoodsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("goodListService")
public class GoodsListServiceImpl implements IGoodsListService {
    @Autowired
    GoodsListMapper goodsListMapper;
    @Override
    public List<GoodsList> getGoodsListSelectedList(Condition condition) {
        return goodsListMapper.selectGoodsListSelectedList(condition);
    }

    @Override
    public int getGoodsListCount(Condition condition) {
        return goodsListMapper.selectGoodsListCount(condition);
    }

    @Override
    public List<Brand> getBrandListSelectedList() {
        return goodsListMapper.getBrandListSelectedList();
    }

    @Override
    public List<GoodsClass> getGoodsClassSelectedList() {
        return goodsListMapper.getGoodsClassSelectedList();
    }

    @Override
    public List<Goods> getGoodsListByBrandName(int brandId) {
        return goodsListMapper.getGoodsListByBrandName(brandId);
    }

    @Override
    public List<Goods> getGoodsListByClassName(int classId) {
        return goodsListMapper.getGoodsListByClassName(classId);
    }

}
