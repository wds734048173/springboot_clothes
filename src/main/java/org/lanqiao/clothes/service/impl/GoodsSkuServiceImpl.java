package org.lanqiao.clothes.service.impl;

import org.lanqiao.clothes.mapper.GoodsSkuMapper;
import org.lanqiao.clothes.service.IGoodsSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: WDS
 * @Date: 2019/1/25 17:15
 * @Description:
 */
@Service
public class GoodsSkuServiceImpl implements IGoodsSkuService {
    @Autowired
    GoodsSkuMapper goodsSkuMapper;
    @Override
    public int getSkuIdBySizeAndColor(int sizeId,int colorId,int goodsId) {
        return goodsSkuMapper.selectSkuIdBySizeAndColor(sizeId, colorId, goodsId);
    }
}
