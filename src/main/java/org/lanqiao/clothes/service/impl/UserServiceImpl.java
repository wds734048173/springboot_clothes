package org.lanqiao.clothes.service.impl;

import org.lanqiao.clothes.mapper.UserMapper;
import org.lanqiao.clothes.pojo.User;
import org.lanqiao.clothes.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: WDS
 * @Date: 2019/1/12 20:18
 * @Description:
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser(User user) {
        return userMapper.selectUser(user);
    }

    @Override
    public void add(User user) {

    }

    @Override
    public User selectByName(String username) {
        return null;
    }

    @Override
    public void updateStoreId(User user) {

    }
}
