package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lanqiao.clothes.pojo.ShopingCar;
import org.lanqiao.clothes.pojo.ShopingCarItem;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface ShoppingCarMapper {
    //添加到购物车
    public void addCar(ShopingCar car);
    //修改对应商品数量
    public void updateCar(ShopingCar car);
    //删除商品
    public void deleteShop(int id);
    //根据用户id查询所有的商品
    public List<ShopingCarItem> selectAllToList(int customerId);
}
