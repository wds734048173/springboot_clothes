package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lanqiao.clothes.pojo.Brand;
import org.lanqiao.clothes.pojo.Condition;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @Auther: WDS
 * @Date: 2019/1/11 15:41
 * @Description:
 */
@Mapper
@Component
public interface BrandMapper {

    public void insertBrand(Brand brand);

    public List<Brand> selectBrandAll(Condition condition);

    public int selectBrandCount(Condition condition);

    public void deleteBrandById(int brandId);

    public void updateBrandById(Brand brand);

    public Brand selectBrandById(int id);
}
