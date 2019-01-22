package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lanqiao.clothes.pojo.Address;

import java.util.List;

/**
 * @Auther: WDS
 * @Date: 2019/1/22 10:19
 * @Description:
 */
@Mapper
public interface AddressMapper {
    //通过客户id获取地址列表
    public List<Address> selectAddressList(int customerId);
    //通过客户id获取默认地址
    public Address selectAddressDefault(int customerId);
    //通过id获取地址详情
    public Address selectAddressById(int id);
}
