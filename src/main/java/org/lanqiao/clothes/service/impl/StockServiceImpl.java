package org.lanqiao.clothes.service.impl;

import org.lanqiao.clothes.mapper.StockMapper;
import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Stock;
import org.lanqiao.clothes.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: WDS
 * @Date: 2019/1/17 15:35
 * @Description:
 */
@Service("stockService")
public class StockServiceImpl implements IStockService {
    @Autowired
    StockMapper stockMapper;
    @Override
    public List<Stock> getStockAll(Condition condition) {
        return stockMapper.selectStockAll(condition);
    }

    @Override
    public int getStockCount(Condition condition) {
        return stockMapper.selectStockCount(condition);
    }

    @Override
    public List<Stock> getStockInfo(int storeId,int goodsId) {
        return stockMapper.selectStockInfo(storeId,goodsId);
    }
}
