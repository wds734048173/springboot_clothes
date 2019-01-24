package org.lanqiao.clothes.pojo;

import lombok.*;

import java.util.Date;

/**
 * @Auther: WDS
 * @Date: 2019/1/11 15:37
 * @Description:
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Brand {
    private int id;
    private String name;
    private int state;
    private String stateStr;
    private String pic;
    private int storeId;
    private Date ctime;
    private Date rtime;
}
