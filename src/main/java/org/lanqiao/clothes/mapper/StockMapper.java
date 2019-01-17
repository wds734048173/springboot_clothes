package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lanqiao.clothes.pojo.Stock;

import java.util.List;

/**
 * @Auther: WDS
 * @Date: 2019/1/17 15:35
 * @Description:
 */
@Mapper
public interface StockMapper {
    //批量新增
    public void insertStockList(List<Stock> stockList);
    //批量增加
    public void updateUpStockList(List<Stock> stockList);
    //批量减少
    public void updateDownStockList(List<Stock> stockList);
}
