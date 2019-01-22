package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lanqiao.clothes.pojo.Territory;

import java.util.List;

/**
 * @Auther: WDS
 * @Date: 2019/1/22 08:32
 * @Description:
 */
@Mapper
public interface TerritoryMapper {
    //查询所有的省
    public List<Territory> selectProvinceList();
    //通过省id查询下面的所有市
    public List<Territory> selectCityList(int provinceId);
    //通过市id查询下面的所有区
    public List<Territory> selectAreaList(int cityId);
    public List<Territory> selectTerritoryListByIds(List<Integer> ids);
}
