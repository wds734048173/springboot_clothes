package org.lanqiao.clothes.pojo;

import lombok.*;

import java.util.Date;
import java.util.List;

//商品基础信息表
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Goods {
    private int id;
    private String no;
    private String name;
    private String pic;
    private int pPrice;
    private int sPrice;
    private int mPrice;
    private int class1Id;
    private String class1Name;
    private int class2Id;
    private String class2Name;
    private int class3Id;
    private String class3Name;
    private int brandId;
    private String brandName;
    private String year;
    private String season;
    private int isshelf;
    private String isshelfStr;
    private String sex;
    private int storeId;
    private Date ctime;
    private Date rtime;

    private List<GoodsSKU> goodsSKUList;


}
