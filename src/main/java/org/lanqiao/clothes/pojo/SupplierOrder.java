package org.lanqiao.clothes.pojo;

import lombok.*;
import org.springframework.context.annotation.Configuration;

import javax.xml.crypto.Data;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Configuration
public class SupplierOrder {
    private int id;
    private String no;
    private int supplierId;
    private String supplierName;
    private int storeId;
    private int totalMoney;
    private int payMoney;
    private int state;
    private String stateStr;
    private Date ctime;
    private Date rtime;
}
