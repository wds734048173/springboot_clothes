package org.lanqiao.clothes.pojo;

import lombok.*;

/**
 * @Auther: WDS
 * @Date: 2019/1/11 21:03
 * @Description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Condition {

    private String state;
    private String name;
    private int storeId;
    private int brandId;
    private int goodsClass1Id;
    private int goodsClass2Id;
    private int goodsClass3Id;
    private String goodsNo;
    private String season;
    private int year;

    private int currentPage;
    private  int pageSize;
}
