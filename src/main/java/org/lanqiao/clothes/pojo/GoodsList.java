package org.lanqiao.clothes.pojo;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsList {
    private int id;
    private String name;
    private String pic;
    private int pPrice;/*采购价*/
    private int sPrice;/*销售价*/
    private int mPrice;/*会员价*/
    private String sex;
    private String no;
}
