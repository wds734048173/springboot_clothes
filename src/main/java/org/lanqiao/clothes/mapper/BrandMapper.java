package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lanqiao.clothes.pojo.Brand;
import org.lanqiao.clothes.pojo.Condition;

import java.util.List;


/**
 * @Auther: WDS
 * @Date: 2019/1/11 15:41
 * @Description:品牌
 */
@Mapper
public interface BrandMapper {

    //新增品牌
    public void insertBrand(Brand brand);

    //查询品牌列表（带查询条件和分页）
    public List<Brand> selectBrandAll(Condition condition);

    //获取品牌数量
    public int selectBrandCount(Condition condition);

    //通过id删除品牌
    public void deleteBrandById(int brandId);

    //修改品牌
    public void updateBrandById(Brand brand);

    //通过品牌id查询品牌
    public Brand selectBrandById(int id);

    //获取品牌下拉列表
    public List<Brand> selectBrandSelectedList(int storeId);
}
