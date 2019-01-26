package org.lanqiao.clothes.pojo;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Comment {
    private int id;
    private int oId;
    private int goodsId;
    private String goodsName;
    private String goodsPic;
    private int skuId;
    private int colorId;
    private int sizeId;
    private String colorName;
    private String sizeName;
    private int grade;
    private String gradeStr;
    private String comment;
    private String reply;
    private int storeId;
    private int customerId;
    private String customerName;
    private int state;
    private String stateStr;
    private Date ctime;
    private Date rtime;



}
