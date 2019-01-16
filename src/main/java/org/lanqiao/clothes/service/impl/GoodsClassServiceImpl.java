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

    @Override
    public void removeGoodClass(int id) {
        goodsClassMapper.deleteGoodsClass(id);
    }

    @Override
    public List<GoodsClass> getGoodsClassList() {
        return goodsClassMapper.selectGoodsClassAll();
    }

    @Override
    public void modifyGoodsClass(GoodsClass goodsClass) {
        goodsClassMapper.updateGoodsClass(goodsClass);
    }
}
