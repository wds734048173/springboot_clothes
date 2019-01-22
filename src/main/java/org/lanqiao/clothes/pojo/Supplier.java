package org.lanqiao.clothes.pojo;

import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Supplier {
    private int id;
    private String name;
    private int sex;
    private String sexStr;
    private String telphone;
    private String address;
    private int state;
    private String stateStr;
    private int storeId;
    private Date ctime;
    private Date rtime;
}
