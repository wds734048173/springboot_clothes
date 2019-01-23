package org.lanqiao.clothes.service;

import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Customer;

import java.util.List;

public interface ICustomerService {
    public List<Customer> getCustomerAll(Condition condition);
    public int getCustomerCount(Condition condition);
    public void modifyCustomerState(int customerId,int state);


    //==============前台操作================
    public Customer selectOne(Customer customer);
    public void addCustomer(Customer customer);
}
