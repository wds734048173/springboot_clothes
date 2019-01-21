package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.SupplierOrder;
import org.lanqiao.clothes.pojo.SupplierOrderInfo;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface SupplierOrderMapper {
    //获取列表
    public List<SupplierOrder> getSupplierOrderAll(Condition condition);
    public int selectSupplierOrderCount(Condition condition);
    //增
    public void insertSupplierOrder(SupplierOrder supplier_order);

    public SupplierOrder selectSupplierOrderById(int supplier_orderId);

    public List<SupplierOrderInfo> selectSupplierOrderinfoList(int Supplier_orderId);
}
