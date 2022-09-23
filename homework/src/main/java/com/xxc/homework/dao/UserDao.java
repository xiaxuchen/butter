package com.xxc.homework.dao;

import com.xxc.dao.mode.Insert;
import com.xxc.dao.mode.Select;
import com.xxc.dao.param.Param;
import com.xxc.dao.result.ID;
import com.xxc.dao.result.Result;
import com.xxc.homework.entity.User;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class UserDao
 * @Description
 * @createTime 2019-05-12 21:47
 */
public interface UserDao {

    /**
     * 通过用户名获取
     * @param username 用户名
     * @return 用户信息
     */
    @Select("SELECT username,pwd,sex FROM user WHERE username=#{username}")
    @Result(id = @ID(property = "username"),autowire = true,
            property = "user",
            javaType = "com.xxc.homework.entity.User")
    User selectByUsername(@Param("username") String username);

    /**
     * 插入一条用户记录
     * @param u 用户信息
     * @return 更新值
     */
    @Insert("insert into user values(#{u.username},#{u.pwd},#{u.sex},#{u.phone})")
    int insert(@Param("u") User u);

}
