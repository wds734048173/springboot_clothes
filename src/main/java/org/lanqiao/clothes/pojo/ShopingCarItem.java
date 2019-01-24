package org.lanqiao.clothes.pojo;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class ShopingCarItem {
    private int id;
    private int storeId;
    private int customerId;
    private int goodsId;
    private int skuId;
    private int num;
    private int price;
    private String goodsName;
    private String color;
    private String size;
    private int pPrice;
    private int sPrice;
    private int mPrice;
    private String pic;
    private String storeName;
}
