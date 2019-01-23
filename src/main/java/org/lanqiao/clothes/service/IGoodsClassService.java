package org.lanqiao.clothes.service;

import org.lanqiao.clothes.pojo.GoodsClass;

import java.util.List;

public interface IGoodsClassService {
    //新增商品分类
    public void addGoodsClass(GoodsClass goodsClass);
    //删除商品分类
//    public void removeGoodClass(int id);
    //修改商品分类
    public void modifyGoodsClass(GoodsClass goodsClass);

    //获取tree分类
    public List<GoodsClass> getGoodsClassTree();
    //获取下一级分类
    //使用递归获取所有的下一级代码
    public List<GoodsClass> getGoodsClassNextList(int goodsClass1Id);

    //只获取其下一级
    public List<GoodsClass> getNextGoodsClassList(int goodsClass1Id);

    public List<GoodsClass> getGoodsClassListByIds(List<Integer> ids);

    //获取一级分类
    public List<GoodsClass> getGoodsClass1List();

}
