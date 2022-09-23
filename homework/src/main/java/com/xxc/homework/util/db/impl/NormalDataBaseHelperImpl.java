package com.xxc.homework.util.db.impl;

import com.xxc.homework.util.db.DataBaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class NormalDataBaseHelperImpl
 * @Description
 * @createTime 2019-04-29 10:35
 */
public class NormalDataBaseHelperImpl extends DataBaseHelper {

    /**
     * 查询
     * @param sql SQL语句
     * //@param resultType 结果类型
     * @param params 参数
     * //@param <T> 结果类型
     * @return 查询结果
     */
    public ResultSet query(String sql, Object...params){
        final PreparedStatement ps = createPreparedStatement(sql, params);
        if ( ps != null) {
            try {
                return ps.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private PreparedStatement createPreparedStatement(String sql,Object...params)
    {
        Connection connection = null;
        try {
            connection = getConnection();
            final PreparedStatement ps = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i+1,params[i]);
            }
            return ps;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    public int update(String sql,Object...params){
        final PreparedStatement ps = createPreparedStatement(sql, params);
        if ( ps != null) {
            try {
                return ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
