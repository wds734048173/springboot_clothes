package org.lanqiao.clothes.service;

import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Stock;

import java.util.List;

/**
 * @Auther: WDS
 * @Date: 2019/1/17 15:35
 * @Description:
 */
public interface IStockService {
    public List<Stock> getStockAll(Condition condition);
    public int getStockCount(Condition condition);
    public List<Stock> getStockInfo(int storeId,int goodsId);
}
