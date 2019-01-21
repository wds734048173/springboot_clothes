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
    private int goodsId;
    private int skuId;
    private int grade;
    private String gradeStr;
    private String comment;
    private String reply;
    private int storeId;
    private int customerId;
    private int state;
    private String stateStr;
    private Date ctime;
    private Date rtime;
}
