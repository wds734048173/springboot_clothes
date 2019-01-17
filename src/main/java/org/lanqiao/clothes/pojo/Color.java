package org.lanqiao.clothes.pojo;

import lombok.*;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Configuration
public class Color {
    private int id;
    private String name;
    private int state;
    private int storeId;
    private Date ctime;
    private Date rtime;
}
