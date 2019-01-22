package org.lanqiao.clothes.pojo;

import lombok.*;

/**
 * @Auther: WDS
 * @Date: 2019/1/22 08:25
 * @Description:中国领土
 */
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Territory {
    private int id;//id
    private String territoryname;//领地名称
    private int territoryparentid;//领地父级id
    private String territory_pinyin;//领地拼音
    private int territorylevel;//领地级别
    private int territorysort;//领地排序
}
