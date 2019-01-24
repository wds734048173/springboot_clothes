package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Size;

import java.util.List;
@Mapper
public interface SizeMapper {
    //获取列表
    public List<Size> selectSizeAll(Condition condition);
    //新增
    public void insertSize(Size size);
    //删除
    public void deleteSize(int sizeId);
    //修改
    public void updateSize(Size size);
    //获取数量
    public int selectSizeCount(Condition condition);

    public Size selectSizeById(int id);
    //获取尺码下拉列表
    public List<Size> selectSizeSelectedList(int storeId);

    public List<Size> selectSizeListByIds(List<Integer> ids);
}
