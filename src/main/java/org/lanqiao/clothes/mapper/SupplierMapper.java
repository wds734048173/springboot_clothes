package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Supplier;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface SupplierMapper {
    //新增
    public void insertSupplier(Supplier color);
    //修改
    public void updateSupplierById(Supplier color);
    //删除
    public void deleteSupplierById(int colorId);
    //查询所有的
    public List<Supplier> selectSupplierAll(Condition condition);
    //查询数量
    public int selectSupplierCount(Condition condition);
    //根据id查询
    public Supplier selectSupplierById(int id);

    //根据ids查询
    public List<Supplier> selectSupplierByIds(List<Integer> ids);
}
