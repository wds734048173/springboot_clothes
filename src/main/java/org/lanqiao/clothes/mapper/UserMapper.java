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
}
