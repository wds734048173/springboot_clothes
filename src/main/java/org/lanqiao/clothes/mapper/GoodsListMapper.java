package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lanqiao.clothes.pojo.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface GoodsListMapper {

    //获取商品列表
    public List<GoodsList> selectGoodsListSelectedList(Condition condition);
    public int selectGoodsListCount(Condition condition);
    //获取品牌
    public List<Brand> getBrandListSelectedList();
    //获取分类
    public List<GoodsClass> getGoodsClassSelectedList();
    //根据品牌便利商品
    public List<Goods> getGoodsListByBrandName(int brandId);
    //根据分类便利商品
    public List<Goods> getGoodsListByClassName(int classId);
}
