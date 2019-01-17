package org.lanqiao.clothes.pojo;

import lombok.*;

import java.util.Date;

/**
 * @Auther: WDS
 * @Date: 2019/1/17 16:36
 * @Description:
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Size {
    private int id;
    private String name;
    private int state;
    private String stateStr;
    private int storeId;
    private Date ctime;
    private Date rtime;

}
