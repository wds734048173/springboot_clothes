package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lanqiao.clothes.pojo.GoodsClass;

import java.util.List;

/**
 * @Auther: WDS
 * @Date: 2019/1/16 10:33
 * @Description:
 */
@Mapper
public interface GoodsClassMapper {
    //获取分类列表
    public List<GoodsClass> selectGoodsClassAll();
    //修改分类
    public void updateGoodsClass(GoodsClass goodsClass);
    //添加分类
    public void insertGoodsClass(GoodsClass goodsClass);
    //删除分类
    public void deleteGoodsClass(int id);
}
