package org.lanqiao.clothes.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lanqiao.clothes.pojo.User;

/**
 * @Auther: WDS
 * @Date: 2019/1/11 14:54
 * @Description:
 */
@Mapper
public interface UserMapper {
    public User selectUser(User user);
    public User selectByName(String username);
    public void insertUser(User user);
    //    修改用户信息表中的店铺id和真实姓名和身份证号
    public void updateStoreId(User user);
    //修改用户信息
    public void updateUser(User user);
}
