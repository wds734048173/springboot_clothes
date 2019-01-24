package org.lanqiao.clothes.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.lanqiao.clothes.pojo.Store;

@Mapper
public interface StoreMapper {
//    添加用户相对应的店铺信息
    public void insertStore(Store store);
    //    修改
    public void updateStore(Store store);
    //    按id查询
    public Store selectById(int id);
}
