package org.lanqiao.clothes.service.impl;

import org.lanqiao.clothes.mapper.CustomerMapper;
import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Customer;
import org.lanqiao.clothes.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    CustomerMapper customerMapper;
    @Override
    public List<Customer> getCustomerAll(Condition condition) {
        List<Customer> customerList = customerMapper.selectCustomerAll(condition);
        for(Customer customer : customerList){
            int state = customer.getState();
            int sex = customer.getSex();
            String stateStr = "";
            if(state == 0){
                stateStr = "启用";
            }else if(state == 1){
                stateStr = "停用";
            }
            String sexStr = "";
            if(sex == 0){
                sexStr = "男";
            }else if(sex == 1){
                sexStr = "女";
            }
            customer.setStateStr(stateStr);
            customer.setSexStr(sexStr);
        }
        return customerList;
    }

    @Override
    public int getCustomerCount(Condition condition) {
        return customerMapper.selectCustomerCount(condition);
    }

    @Override
    public void modifyCustomerState(int customerId, int state) {
        customerMapper.updateCustomerState(customerId, state);
    }
}
