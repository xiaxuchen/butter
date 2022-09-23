package com.xxc.homework.util.pool;

import com.xxc.homework.util.pool.exception.PropertiesErrorException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class Connector
 * @Description
 * @createTime 2019-04-23 14:13
 */
public class Connector {

    private Properties props;//配置文件

    public Connector(Properties properties){
        this.props = properties;
        init();
    }

    private void init(){
        try {
            Class.forName(props.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new PropertiesErrorException("JDBC驱动异常");
        }

    }

    /**
     * 连接数据库
     * @return 数据库连接
     */
    public Connection connect()
    {
        try {
            return DriverManager.getConnection(props.getProperty("url"),props.getProperty("username"),props.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PropertiesErrorException("数据库配置异常");
        }
    }

}
