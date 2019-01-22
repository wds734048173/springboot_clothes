package org.lanqiao.clothes.service.impl;

import org.lanqiao.clothes.mapper.TerritoryMapper;
import org.lanqiao.clothes.pojo.Territory;
import org.lanqiao.clothes.service.ITerritoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: WDS
 * @Date: 2019/1/22 08:33
 * @Description:
 */
@Service
public class TerritoryServiceImpl implements ITerritoryService {
    @Autowired
    TerritoryMapper territoryMapper;
    @Override
    public List<Territory> getProvinceList() {
        return territoryMapper.selectProvinceList();
    }

    @Override
    public List<Territory> getCityList(int provinceId) {
        return territoryMapper.selectCityList(provinceId);
    }

    @Override
    public List<Territory> getAreaList(int cityId) {
        return territoryMapper.selectAreaList(cityId);
    }
}
