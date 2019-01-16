package org.lanqiao.clothes.service;

import org.lanqiao.clothes.pojo.Brand;
import org.lanqiao.clothes.pojo.Condition;

import java.util.List;

public interface IBrandService {
    public void addBrand(Brand brand);
    public List<Brand> getBrandAll(Condition condition);
    public int getBrandCount(Condition condition);
    public void removeBrandById(int brandId);
    public void modifyBrandById(Brand brand);
    public Brand getBrandById(int id);
}
