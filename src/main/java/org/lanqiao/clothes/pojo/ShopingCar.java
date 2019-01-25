package org.lanqiao.clothes.pojo;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class ShopingCar {
    private int id;
    //店铺
    private int storeId;
    private String storeName;
    //客户
    private int customerId;
    private String customerName;
    //商品基础信息
    private int goodsId;
    private String goodsName;
    private int pPrice;
    private int sPrice;
    private int mPrice;
    private String pic;

    //商品详细信息
    private int skuId;
    private int colorId;
    private int sizeId;
    private String colorName;
    private String sizeName;

    //购物车数量和价格
    private int num;
    private int price;
}
