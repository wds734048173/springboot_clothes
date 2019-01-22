package org.lanqiao.clothes.pojo;

import lombok.*;

import java.util.Date;

/**
 * @Auther: WDS
 * @Date: 2019/1/22 10:21
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Address {
    private int id;
    private String name;
    private String phone;
    private int customerId;
    private String customerName;
    private int provinceId;
    private String provinceName;
    private int cityId;
    private String cityName;
    private int areaId;
    private String areaName;
    private String address;
    private int state;
    private Date ctime;
    private Date rtime;
}
