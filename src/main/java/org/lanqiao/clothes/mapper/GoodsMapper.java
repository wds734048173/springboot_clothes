package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Goods;
import org.lanqiao.clothes.pojo.GoodsSKU;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface GoodsMapper {
    //新增
    public void insertGoods(Goods goods);
    //修改
    public void updateGoodsById(Goods goods);
    //删除
    public void deleteGoodsById(int goodsId);
    //查询所有的
    public List<Goods> selectGoodsAll(Condition condition);
    //查询数量
    public int selectGoodsCount(Condition condition);
    //根据id查询
    public Goods selectGoodsById(int id);
    //通过商品ids获取商品信息
    public List<Goods> selectGoodsByIds(List<Integer> ids);

    //通过skuIds查询sku详细信息
    public List<GoodsSKU> selectSKUByIds(List<Integer> ids);

    //修改商品上下架状态
    public void updateGoodsIsshelf(int storeId,int goodsId,int isshelf);

    //新增商品sku表
    public void insertGoodsSKUList(List<GoodsSKU> goodsSKUList);

    //通过商品id查询商品子表
    public List<GoodsSKU> selectGoodsSKUList(int goodsIs);
}
