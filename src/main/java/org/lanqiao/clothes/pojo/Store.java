package org.lanqiao.clothes.pojo;

import lombok.*;

import java.util.Date;

/**
 * @Auther: WDS
 * @Date: 2019/1/22 22:35
 * @Description:店铺信息类
 */
@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    private int id;
    //    店铺名称
    private String name;
    //    营业执照编号
    private String bussinessNo;
    //    店招
    private String pic;
    //    地址
    private String address;
    //    联系电话
    private String telphone;
    //    状态（0未审核1审核失败2审核成功）
    private int state;
    //    营业执照图片
    private String bussinessPic;
    private Date ctime;
    private Date rtime;

}
