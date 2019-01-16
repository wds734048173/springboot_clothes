package org.lanqiao.clothes.pojo;

import lombok.*;

import java.util.Date;

/**
 * @Auther: WDS
 * @Date: 2019/1/11 14:54
 * @Description:
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    private String username;
    private String password;
    private String realname;
    private int role;
    private int sex;
    private String phone;
    private int state;
    private int storeId;
    private Date ctime;
    private Date rtime;
}
