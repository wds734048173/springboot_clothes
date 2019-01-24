package org.lanqiao.clothes.service.impl;

import org.lanqiao.clothes.mapper.SupplierMapper;
import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Supplier;
import org.lanqiao.clothes.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SupplierServiceImpl implements ISupplierService {
    @Autowired
    SupplierMapper supplierMapper;
    @Override
    public void addSupplier(Supplier supplier) {
        supplierMapper.insertSupplier(supplier);
    }

    @Override
    public List<Supplier> getSupplierAll(Condition condition) {
        List<Supplier> supplierList = supplierMapper.selectSupplierAll(condition);
        for (Supplier supplier : supplierList){
            int state = supplier.getState();
            if (state==0){
                supplier.setStateStr("启用");
            }else if(state==1){
                supplier.setStateStr("停用");
            }
            int sex = supplier.getSex();
            if(sex == 0){
                supplier.setSexStr("女");
            }else if(sex == 1){
                supplier.setSexStr("男");
            }
        }
        return supplierList;
    }

    @Override
    public int getSupplierCount(Condition condition) {
        return supplierMapper.selectSupplierCount(condition);
    }

    @Override
    public void removeSupplierById(int supplierId) {
        supplierMapper.deleteSupplierById(supplierId);
    }

    @Override
    public void modifySupplierById(Supplier supplier) {
        supplierMapper.updateSupplierById(supplier);
    }

    @Override
    public Supplier getSupplierById(int id) {
        return supplierMapper.selectSupplierById(id);
    }
}
