package org.lanqiao.clothes.service;

import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Store;

import java.util.List;

/**
 * @Auther: WDS
 * @Date: 2019/1/22 23:36
 * @Description:店铺服务
 */
public interface IStoreService {
    public void insertStore(Store store);
    public void updateStore(Store store);
    public Store selectById(int id);
    public List<Store> getStoreAll(Condition condition);
    public int getStoreCount(Condition condition);
    public void modifyStoreStateById(int id,int state);

}
