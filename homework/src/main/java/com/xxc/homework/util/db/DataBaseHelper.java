package com.xxc.homework.util.db;

import com.xxc.homework.util.db.exception.PoolNotSetException;
import com.xxc.homework.util.db.impl.NormalDataBaseHelperImpl;
import com.xxc.homework.util.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.concurrent.TimeoutException;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class DataBaseHelper
 * @Description
 * @createTime 2019-04-29 10:23
 */
public abstract class DataBaseHelper {
    
    private static DataBaseHelper INSTANCE;

    private static ConnectionPool STATIC_POOL;

    protected ConnectionPool pool;

    public static void init(ConnectionPool pool)
    {
        DataBaseHelper.STATIC_POOL = pool;
    }
    
    public static DataBaseHelper getInstance()
    {
        if(INSTANCE == null)
        {
            synchronized (DataBaseHelper.class)
            {
                if(INSTANCE == null)
                {
                    if(STATIC_POOL == null)
                        throw new PoolNotSetException("使用DataBaseHelper之前必须显示调用init方法进行初始化");
                    INSTANCE = new NormalDataBaseHelperImpl();
                    INSTANCE.setPool(STATIC_POOL);
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 设置数据库助手的实现类
     * @param helper
     */
    public synchronized void setDataBaseImpl(DataBaseHelper helper){
        INSTANCE = helper;
        if(helper.pool == null)
            helper.setPool(STATIC_POOL);
    }

    public void setPool(ConnectionPool pool){
        this.pool = pool;
    }

    public Connection getConnection() throws TimeoutException {
        if(pool != null)
            return pool.getConnection();
        throw new PoolNotSetException("使用DataBaseHelper之前必须显示调用init方法进行初始化");
    }

    public abstract ResultSet query(String sql,Object...params);

    public abstract int update(String sql,Object...params);

    /**
     * 查询
     * @param sql SQL语句
     * @param params 参数
     * @return 查询结果
     */
    public static ResultSet QUERY(String sql, Object...params){
        return getInstance().query(sql,params);
    }


    /**
     * 更新数据
     * @param sql sql语句
     * @param params 参数
     * @return 影响记录条数
     */
    public static int UPDATE(String sql, Object...params){
        return getInstance().update(sql,params);
    }

}
