package org.lanqiao.clothes.service;

import org.lanqiao.clothes.pojo.GoodsClass;

import java.util.List;

public interface IGoodsClassService {
    //新增商品分类
    public void addGoodsClass(GoodsClass goodsClass);
    //删除商品分类
//    public void removeGoodClass(int id);
    //获取商品分类列表
    public List<GoodsClass> getGoodsClassList(int storeId);
    //修改商品分类
    public void modifyGoodsClass(GoodsClass goodsClass);

    //获取tree分类
    public List<GoodsClass> getGoodsClass1List(int storeId);
    //获取下一级分类
    public List<GoodsClass> getGoodsClassNextList(int storeId,int goodsClass1Id);

}
