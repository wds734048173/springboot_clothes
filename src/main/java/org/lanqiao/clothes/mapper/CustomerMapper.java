package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lanqiao.clothes.pojo.Color;
import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CustomerMapper {
    //查询所有的
    public List<Customer> selectCustomerAll(Condition condition);
    //查询数量
    public int selectCustomerCount(Condition condition);

    //修改客户状态
    public void updateCustomerState(int customerId,int state);

    //通过客户ids获取客户详情
    public List<Customer> selectCustomerByIds(List<Integer> ids);

    //通过客户ids获取客户详情
    public Customer selectCustomerById(Integer id);
}