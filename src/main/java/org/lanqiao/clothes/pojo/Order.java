package org.lanqiao.clothes.pojo;

import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Order {
    private int id;
    private String no;
    private int customerId;
    private String customerName;
    private int totalMoney;
    private int payMoney;
    private int addressId;
    private String address;
    private int storeId;
    private int state;
    private String stateStr;
    private Date ctime;
    private Date rtime;
    private Date payTime;
    private Date sendTime;
    private Date getTime;
    private Date cancelTime;

}
