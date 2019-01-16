package org.lanqiao.clothes.pojo;

import lombok.*;
import org.springframework.context.annotation.Configuration;

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
@Configuration
public class Brand {
    private int id;
    private String name;
    private int state;
    private String pic;
    private int storeId;
    private Date ctime;
    private Date rtime;
}
