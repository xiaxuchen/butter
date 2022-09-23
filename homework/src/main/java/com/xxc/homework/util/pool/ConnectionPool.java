package com.xxc.homework.util.pool;

import com.xxc.homework.util.pool.exception.PropertiesNotFoundException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeoutException;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class ConnectionPool
 * @Description
 * @createTime 2019-04-23 13:52
 */
public class ConnectionPool {


    private final Queue<Connection> connections;

    private final int maxSize;//Connection最大数量

    private final int minSize;//Connection最小数量

    private int currentSize;//当前连接数量

    private final int timeOut;//超时时间

    private final Connector connector;//数据库连接者


    public static final String DEFAULT_PATH = "jdbc.properties";

    private static String path;

    private static final int DEFAULT_MAX_SIZE = 30;

    private static final int DEFAULT_MIN_SIZE = 10;

    private static final int DEFAULT_TIME_OUT = 1000;

    private static ConnectionPool INSTANCE;

    public static void init(String path)
    {
        ConnectionPool.path = path;
    }

    public static ConnectionPool getInstance()
    {
        if(INSTANCE == null)
        {
            synchronized (ConnectionPool.class)
            {
                if(INSTANCE == null)
                    INSTANCE = new ConnectionPool(loadProperties());
            }
        }
        return INSTANCE;
    }

    private static Properties loadProperties() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            throw new PropertiesNotFoundException(path);
        }
        return props;
    }

    private ConnectionPool(Properties properties)
    {
        String maxSize = properties.getProperty("maxSize");
        String minSize = properties.getProperty("minSize");
        String timeOut = properties.getProperty("timeOut");
        if (maxSize != null)
            this.maxSize = Integer.parseInt(maxSize);
        else
            this.maxSize = DEFAULT_MAX_SIZE;
        if (minSize != null)
            this.minSize = Integer.parseInt(minSize);
        else
            this.minSize = DEFAULT_MIN_SIZE;
        if(timeOut != null)
            this.timeOut = Integer.parseInt(timeOut);
        else
            this.timeOut = DEFAULT_TIME_OUT;
        connector = new Connector(properties);
        connections = new ArrayBlockingQueue<>(this.maxSize);
        initPool();
    }


    private void initPool() {
        for(int i = 0;i<minSize;i++)
        {
            createConnection();
        }
    }

    /**
     * 创建新连接
     */
    private void createConnection()
    {
        connections.add(RecycleableConnection.wapper(connector.connect(),this));
        currentSize++;
    }

    private void destoryConnection()
    {
        int size = (maxSize - minSize);
        if(connections.size() > size/2+minSize)
        {
            for(int i = 0;i<size/3;i++)
            {
                ((RecycleableConnection) connections.poll()).destory();
                currentSize--;
            }
        }
    }

    /**
     * 获取数据库连接
     * @return 数据库连接
     */
    public Connection getConnection() throws TimeoutException {
        long start = System.currentTimeMillis();
        if (currentSize < maxSize && connections.size()==0) {
            synchronized (connections) {
                if (currentSize < maxSize && connections.size() == 0) {
                    createConnection();
                    return connections.poll();
                }else if(connections.size() != 0){
                    return connections.poll();
                }
            }
        }
        Connection connection = null;
        try {
            Thread.sleep(1);
            //当连接池为空时循环获取直到超时或获取到连接为止
            while (connection == null && System.currentTimeMillis() - start < timeOut) {
                if (connections.size() > 0) {
                    synchronized (connections) {
                        if (connections.size() > 0)
                            connection = connections.poll();
                    }
                }
                Thread.sleep(1);
            }
            if (connection == null)
                throw new TimeoutException();
            return connection;
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new TimeoutException();
        }finally {
            destoryConnection();
        }
    }


        /**
     * 回收连接
     * @param con 使用完毕的数据库连接
     */
    public void recycle(Connection con)
    {
        synchronized (connections)
        {
            connections.add(con);
        }
    }


}
