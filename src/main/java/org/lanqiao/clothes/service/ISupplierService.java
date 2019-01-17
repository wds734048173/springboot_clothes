package org.lanqiao.clothes.service;

import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Supplier;

import java.util.List;

public interface ISupplierService {
    public void addSupplier(Supplier supplier);
    public List<Supplier> getSupplierAll(Condition condition);
    public int getSupplierCount(Condition condition);
    public void removeSupplierById(int supplierId);
    public void modifySupplierById(Supplier supplier);
    public Supplier getSupplierById(int id);
}
