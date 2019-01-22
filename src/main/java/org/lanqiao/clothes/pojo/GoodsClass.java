package org.lanqiao.clothes.pojo;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: WDS
 * @Date: 2019/1/16 10:38
 * @Description:
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsClass {
    private int id;
    private int pId;
    private String name;
    private List<GoodsClass> children = new ArrayList<>();
}
