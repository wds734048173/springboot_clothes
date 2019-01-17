package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Size;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface SizeMapper {
    public List<Size> selectSizeAll(Condition condition);
    public void insertSize(Size size);
    public void deleteSize(int sizeId);
    public void updateSize(int sizeId);
    public int selectSizeCount(Condition condition);

}
