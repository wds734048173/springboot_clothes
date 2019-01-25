package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lanqiao.clothes.pojo.ShopingCar;
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
    public List<ShopingCar> selectAllToList(int customerId);
    //根据ids批量删除购物车信息
    public void deleteShoppingCarByIds(List<Integer> ids);

    //通过客户id和商品id和skuid查询购物车中是否有该商品
    public ShopingCar selectShoppingCar(int customerId,int goodsId,int skuId);
    //累加购物车数量，并修改单价
    public void updateAddShoppingCar(ShopingCar shopingCar);
}
