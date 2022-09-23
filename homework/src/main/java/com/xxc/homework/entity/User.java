package com.xxc.homework.entity;

import com.xxc.better.dao.session.factory.SqlSessionFactory;
import com.xxc.homework.dao.UserDao;
import com.xxc.util.string.StringUtil;

import java.io.Serializable;
import java.security.InvalidParameterException;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class User
 * @Description 用户信息
 * @createTime 2019-03-17 10:15
 */
public class User implements Serializable {

    private static final UserDao USER_DAO = SqlSessionFactory.getSqlSession().open(UserDao.class);

    private String username;

    private String pwd;

    private String sex;

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User()
    {}

    public User(String username)
    {
        this.username = username;
    }

    public User(String username,String pwd)
    {
        this.username = username;
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 登录
     * @return 是否成功
     */
    public boolean login()
    {
        if(StringUtil.isEmpty(username) || StringUtil.isEmpty(pwd))
            throw new InvalidParameterException("用户名或密码不能为空");
        User u = USER_DAO.selectByUsername(this.username);
        System.out.println(u);
        if(u == null)
            return false;
        if(u.getPwd().equals(this.pwd))
        {
            this.setSex(u.getSex());
            return true;
        }
        return false;
    }

    /**
     * 注册
     * @return 是否注册成功
     */
    public boolean register(){
        return USER_DAO.insert(this) > 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }


}
