package org.lanqiao.clothes.service.impl;

import org.lanqiao.clothes.mapper.StoreMapper;
import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Store;
import org.lanqiao.clothes.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: WDS
 * @Date: 2019/1/22 23:37
 * @Description:
 */
@Service
public class StoreServiceImpl implements IStoreService {
    @Autowired
    StoreMapper storeMapper;
    @Override
    public void insertStore(Store store) {
        storeMapper.insertStore(store);
    }

    @Override
    public void updateStore(Store store) {
        storeMapper.updateStore(store);
    }

    @Override
    public Store selectById(int id) {
        return storeMapper.selectById(id);
    }

    @Override
    public List<Store> getStoreAll(Condition condition) {
        return storeMapper.selectStoreAll(condition);
    }

    @Override
    public int getStoreCount(Condition condition) {
        return storeMapper.selectStoreCount(condition);
    }

    @Override
    public void modifyStoreStateById(int id, int state) {
        storeMapper.updateStoreStateById(id,state);
    }
}
