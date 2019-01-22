package org.lanqiao.clothes.service;

import org.lanqiao.clothes.pojo.Territory;

import java.util.List;

/**
 * @Auther: WDS
 * @Date: 2019/1/22 08:32
 * @Description:
 */
public interface ITerritoryService {
    //获取所有的省
    public List<Territory> getProvinceList();
    //获取省下所有的市
    public List<Territory> getCityList(int provinceId);
    //获取市下所有的区
    public List<Territory> getAreaList(int cityId);
}
