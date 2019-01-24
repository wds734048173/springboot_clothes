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
    private int storeId;
    private int customerId;
    private int goodsId;
    private int skuId;
    private int num;
    private int price;
}
