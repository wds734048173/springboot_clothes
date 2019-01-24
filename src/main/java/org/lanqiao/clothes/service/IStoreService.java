package org.lanqiao.clothes.service;

import org.lanqiao.clothes.pojo.Store;

/**
 * @Auther: WDS
 * @Date: 2019/1/22 23:36
 * @Description:店铺服务
 */
public interface IStoreService {
    public void insertStore(Store store);
    public void updateStore(Store store);
    public Store selectById(int id);
}
