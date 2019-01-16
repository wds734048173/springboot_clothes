package org.lanqiao.clothes.service.impl;

import org.lanqiao.clothes.mapper.BrandMapper;
import org.lanqiao.clothes.pojo.Brand;
import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: WDS
 * @Date: 2019/1/11 15:55
 * @Description:
 */
@Service("brandService")
public class BrandServiceImpl implements IBrandService {
    @Autowired
    BrandMapper brandMapper;
    @Override
    public void addBrand(Brand brand) {
        brandMapper.insertBrand(brand);
    }

    @Override
    public List<Brand> getBrandAll(Condition condition) {
        List<Brand> brandList = brandMapper.selectBrandAll(condition);
        for(Brand brand : brandList){
            int state = brand.getState();
            if(state == 0){
                brand.setStateStr("启用");
            }else if(state == 1){
                brand.setStateStr("停用");
            }
        }
        return brandList;
    }

    @Override
    public int getBrandCount(Condition condition) {
        return brandMapper.selectBrandCount(condition);
    }

    @Override
    public void removeBrandById(int brandId) {
        brandMapper.deleteBrandById(brandId);
    }

    @Override
    public void modifyBrandById(Brand brand) {
        brandMapper.updateBrandById(brand);
    }

    @Override
    public Brand getBrandById(int id) {
        return brandMapper.selectBrandById(id);
    }
}
