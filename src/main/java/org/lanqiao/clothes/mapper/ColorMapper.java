package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lanqiao.clothes.pojo.Color;
import org.lanqiao.clothes.pojo.Condition;

import java.util.List;

@Mapper
public interface ColorMapper {
    //新增
    public void insertColor(Color color);
    //修改
    public void updateColorById(Color color);
    //删除
    public void deleteColorById(int colorId);
    //查询所有的
    public List<Color> selectColorAll(Condition condition);
    //查询数量
    public int selectColorCount(Condition condition);
    //根据id查询
    public Color selectColorById(int id);
    //获取颜色下拉列表
    public List<Color> selectColorSelectedList(int storeId);

    public List<Color> selectColorListByIds(List<Integer> ids);

    public int selectSkuIdBySizeAndColor(int sizeId,int colorId,int goodsId);
}
