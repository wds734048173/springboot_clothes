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
    public List<GoodsClass> selectGoodsClassAll(int storeId);
    //修改分类
    public void updateGoodsClass(GoodsClass goodsClass);
    //添加分类
    public void insertGoodsClass(GoodsClass goodsClass);
    //删除分类
//    public void deleteGoodsClass(int id);
    //获取一级分类
    public List<GoodsClass> selectGoodsClass1List(int storeId);
    //获取下一级分类
    public List<GoodsClass> selectGoodsClassNextList(int storeId,int goodsClass2Id);
    //通过ids获取分类详情
    public List<GoodsClass> selectGoodsClassListByIds(List<Integer> ids);
}
