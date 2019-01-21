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
public class SupplierOrderInfo {
    private int id;
    private int oId;
    private int goodsId;
    private String goodsName;
    private int skuId;
    private int colorId;
    private String colorName;
    private int sizeId;
    private String sizeName;
    private int num;
    private int price;
}
