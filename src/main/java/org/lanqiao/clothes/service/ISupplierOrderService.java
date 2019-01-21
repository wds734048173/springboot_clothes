package org.lanqiao.clothes.service;

import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.SupplierOrder;
import org.lanqiao.clothes.pojo.SupplierOrderInfo;

import java.util.List;

public interface ISupplierOrderService {
    public List<SupplierOrder> getSupplierOrderAll(Condition condition);
    public int getSupplierOrderCount(Condition condition);
    public void addSupplierOrder(SupplierOrder supplierOrder);
    public SupplierOrder getSupplierOrderById(int supplierOrderId);
    public List<SupplierOrderInfo> getSupplierOrderInfoListById(int supplierOrderId,int storeId);
}
