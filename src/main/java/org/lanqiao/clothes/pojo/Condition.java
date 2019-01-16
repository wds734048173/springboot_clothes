package org.lanqiao.clothes.pojo;

import lombok.*;

/**
 * @Auther: WDS
 * @Date: 2019/1/11 21:03
 * @Description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Condition {
    private String state;
    private String name;
    private int storeId;
    private int brandId;
    private int currentPage;
    private  int pageSize;
}
