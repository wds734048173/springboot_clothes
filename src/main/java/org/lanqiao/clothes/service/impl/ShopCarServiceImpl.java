package org.lanqiao.clothes.service.impl;

import org.lanqiao.clothes.mapper.ShoppingCarMapper;
import org.lanqiao.clothes.pojo.ShopingCar;
import org.lanqiao.clothes.pojo.ShopingCarItem;
import org.lanqiao.clothes.service.IShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShopCarServiceImpl implements IShopCarService {
    @Autowired
    ShoppingCarMapper carMapper;
    @Override
    public void addCar(ShopingCar car) {
        carMapper.addCar(car);
    }

    @Override
    public void updateCar(ShopingCar car) {
        carMapper.updateCar(car);
    }

    @Override
    public void deleteShop(int id) {
        carMapper.deleteShop(id);
    }

    @Override
    public List<ShopingCarItem> selectAllToList(int customerId) {
        return carMapper.selectAllToList(customerId);
    }
}
