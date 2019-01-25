package org.lanqiao.clothes.service;

/**
 * @Auther: WDS
 * @Date: 2019/1/25 17:15
 * @Description:
 */
public interface IGoodsSkuService {

    public int getSkuIdBySizeAndColor(int sizeId,int colorId,int goodsId);
}
