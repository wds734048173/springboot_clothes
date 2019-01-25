package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther: WDS
 * @Date: 2019/1/25 17:11
 * @Description:
 */
@Mapper
public interface GoodsSkuMapper {
    //通过商品id，颜色id，尺码id获取skuid
    public int selectSkuIdBySizeAndColor(int sizeId,int colorId,int goodsId);
}
