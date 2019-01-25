package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    //====================前台操作====================
    //查询一个
    public Customer  selectOne(Customer customer);
    //添加
    public void insertCustomer(Customer customer);

    //    查询所有
    public List<Customer> selectSaleAll();
    //    根据name查询
    public Customer selectByName(@Param(value="username") String username);
    //    根据name修改密码
    public void updatePwdByName(@Param(value = "password") String password, @Param(value="username") String username);
}
