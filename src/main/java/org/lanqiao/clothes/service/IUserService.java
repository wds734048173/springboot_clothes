package org.lanqiao.clothes.service;

import org.lanqiao.clothes.pojo.User;

/**
 * @Auther: WDS
 * @Date: 2019/1/12 20:15
 * @Description:
 */
public interface IUserService {
    public User getUser(User user);
    public void add(User user);
    public User selectByName(String username);
    public void updateStoreId(User user);
    public void modifyUser(User user);
}
