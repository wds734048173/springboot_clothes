package org.lanqiao.clothes.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Store;

import java.util.List;

@Mapper
public interface StoreMapper {
//    添加用户相对应的店铺信息
    public void insertStore(Store store);
    //    修改
    public void updateStore(Store store);
    //    按id查询
    public Store selectById(int id);



    //获取总数
    public List<Store> selectStoreAll(Condition condition);
    public int selectStoreCount(Condition condition);
    public void updateStoreStateById(int id,int state);

    //通过店铺ids获取店铺信息
    public List<Store> selectStoreListByIds(List<Integer> ids);

}
