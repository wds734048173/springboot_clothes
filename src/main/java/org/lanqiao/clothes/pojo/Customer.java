package org.lanqiao.clothes.pojo;

import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Customer {
    private int id;
    private String username;
    private String password;
    private String realname;
    private int sex;
    private String sexStr;
    private String phone;
    private String address;
    private int state;
    private String stateStr;
    private Date hiredate;
    private Date ctime;
    private Date rtime;
}
