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
    private int skuId;
    private int num;
}
