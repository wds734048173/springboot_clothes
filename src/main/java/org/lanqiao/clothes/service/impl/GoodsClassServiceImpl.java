package org.lanqiao.clothes.service.impl;

import org.lanqiao.clothes.mapper.GoodsClassMapper;
import org.lanqiao.clothes.pojo.GoodsClass;
import org.lanqiao.clothes.service.IGoodsClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: WDS
 * @Date: 2019/1/16 10:58
 * @Description:
 */
@Service("goodsClassService")
public class GoodsClassServiceImpl implements IGoodsClassService {
    @Autowired
    GoodsClassMapper goodsClassMapper;
    @Override
    public void addGoodsClass(GoodsClass goodsClass) {
        goodsClassMapper.insertGoodsClass(goodsClass);
    }

    /*@Override
    public void removeGoodClass(int id) {
        goodsClassMapper.deleteGoodsClass(id);
    }*/

    @Override
    public List<GoodsClass> getGoodsClassList(int storeId) {
        return goodsClassMapper.selectGoodsClassAll(storeId);
    }

    @Override
    public void modifyGoodsClass(GoodsClass goodsClass) {
        goodsClassMapper.updateGoodsClass(goodsClass);
    }

    @Override
    public List<GoodsClass> getGoodsClass1List(int storeId) {
        //获取一级分类
        List<GoodsClass> goodsClassList = goodsClassMapper.selectGoodsClass1List(storeId);
        for(GoodsClass goodsClass : goodsClassList){
            int id = goodsClass.getId();
            List<GoodsClass> goodsClassList1 = this.getGoodsClassNextList(storeId,id);
            goodsClass.setChildren(goodsClassList1);
        }
        return goodsClassList;
    }

    @Override
    public List<GoodsClass> getGoodsClassNextList(int storeId, int goodsClass1Id) {
        List<GoodsClass> goodsClassList =  goodsClassMapper.selectGoodsClassNextList(storeId, goodsClass1Id);
        for(GoodsClass goodsClass : goodsClassList){
            int id = goodsClass.getId();
            goodsClass.setChildren(getGoodsClassNextList(storeId,id));
        }
        return goodsClassList;
    }

}
