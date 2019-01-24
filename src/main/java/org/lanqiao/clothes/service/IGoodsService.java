package org.lanqiao.clothes.service;

import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Goods;
import org.lanqiao.clothes.pojo.GoodsSKU;

import java.util.List;

public interface IGoodsService {
    public void addGoods(Goods goods);
    public void removeGoodsById(int goodsId);
    public void updateGoodsById(Goods goods);
    public List<Goods> getGoodsAll(Condition condition);
    public int getGoodsCount(Condition condition);
    public Goods getGoodsById(int id);
    /*public List<Goods> getGoodsByIds(List<Integer> ids);*/
    public void modifyGoodsIsshelf(int storeId,int goodsId,int isshelf);
    public void addGoodsSKUList(List<GoodsSKU> goodsSKUList);

    public List<GoodsSKU> getGoodsSKUList(int goodsId);
}
