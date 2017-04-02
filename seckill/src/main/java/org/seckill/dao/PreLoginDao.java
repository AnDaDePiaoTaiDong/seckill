package org.seckill.dao;


import org.apache.ibatis.annotations.Param;
import org.seckill.entity.User;

public interface PreLoginDao {
    //添加用户
    boolean add(User user);
    //注销
    boolean delete(int id);
    //修改信息
    boolean updateUserById(@Param("id")int id,@Param("password")String password);
    //根据手机号查询用户
    User queryByPhone(Long phone);

}
