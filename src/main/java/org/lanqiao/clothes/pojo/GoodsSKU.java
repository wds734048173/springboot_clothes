package org.lanqiao.clothes.pojo;

import lombok.*;

import java.util.Date;

/**
 * @Auther: WDS
 * @Date: 2019/1/18 10:24
 * @Description:
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class GoodsSKU {
    private int id;
    private int goodsId;
    private String goodsName;
    private int colorId;
    private String colorName;
    private int sizeId;
    private String sizeName;
    private Date ctime;
    private Date rtime;
}
