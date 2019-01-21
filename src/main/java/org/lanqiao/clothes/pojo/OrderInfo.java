package org.lanqiao.clothes.pojo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderInfo {
    private int id;
    private int oId;
    private int goodsId;
    private String goodsName;
    private String goodsPic;
    private int skuId;
    private int sizeId;
    private int colorId;
    private String sizeName;
    private String colorName;
    private int price;
    private int num;
}
