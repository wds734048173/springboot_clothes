package org.lanqiao.clothes.pojo;

import lombok.*;
import org.springframework.context.annotation.Configuration;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Configuration
public class Stock {
    private int id;
    private int storeId;
    private int goodsId;
    private String goodsName;
    private String goodsPic;
    private int skuId;
    private int colorId;
    private int sizeId;
    private String colorName;
    private String sizeName;
    private int num;
}
