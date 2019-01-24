package org.lanqiao.clothes.service;

import org.lanqiao.clothes.pojo.*;

import java.util.List;

public interface IGoodsListService {
    //商品展示
    public List<GoodsList> getGoodsListSelectedList(Condition condition);
    public int getGoodsListCount(Condition condition);
    //获取品牌名称
    public List<Brand> getBrandListSelectedList();
    //获取服装分类
    public List<GoodsClass> getGoodsClassSelectedList();
    //根据品牌便利商品
    public List<Goods> getGoodsListByBrandName(int brandId);
    //根据分类便利商品
    public List<Goods> getGoodsListByClassName(int classId);
}