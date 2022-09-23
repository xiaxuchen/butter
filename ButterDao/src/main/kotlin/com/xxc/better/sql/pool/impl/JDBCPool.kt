package com.xxc.better.sql.pool.impl

import com.xxc.better.sql.RecycleableConnection
import com.xxc.better.sql.connector.Connector
import com.xxc.better.sql.connector.impl.ConnectorImpl
import com.xxc.better.sql.pool.Pool
import java.sql.Connection
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.TimeoutException

/**
 * @class JDBCPool
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-13 10:12
 */
class JDBCPool(properties: Properties?):Pool {

    private val defaultMaxSize = 30

    private val defaultMinSize = 10

    private val defaultTimeOut = 1000

    private lateinit var connections: Queue<Connection>

    private var maxSize: Int = defaultMaxSize//Connection最大数量

    private var minSize: Int = defaultMinSize//Connection最小数量

    private var currentSize: Int = 0//当前连接数量

    private var timeOut: Int = defaultTimeOut//超时时间

    private lateinit var connector: Connector//数据库连接者


    init{
        if(properties != null)
        {
            maxSize = properties.getProperty("maxSize")?.toInt()?:defaultMaxSize
            minSize = properties.getProperty("minSize")?.toInt()?:defaultMinSize
            timeOut = properties.getProperty("timeOut")?.toInt()?:defaultTimeOut
            connections = ArrayBlockingQueue<Connection>(maxSize)
            connector = ConnectorImpl(properties["url"] as String,
                    properties["username"] as String, properties["password"] as String)
            connections = ArrayBlockingQueue(this.maxSize)
            initPool()
        }
    }


    private fun initPool() {
        for (i in 0 until minSize) {
            createConnection()
        }
    }

    /**
     * 创建新连接
     */
    private fun createConnection() {
        connections.add(RecycleableConnection(connector.connect(), this))
        currentSize++
    }

    private fun destoryConnection() {
        val size = maxSize - minSize
        if (connections.size > size / 2 + minSize) {
            for (i in 0 until size / 3) {
                (connections.poll() as RecycleableConnection).destory()
                currentSize--
            }
        }
    }

    /**
     * 获取数据库连接
     * @return 数据库连接
     */
    override fun getConnection(): Connection {
        val start = System.currentTimeMillis()
        if (currentSize < maxSize && connections.size == 0) {
            synchronized(connections) {
                if (currentSize < maxSize && connections.size == 0) {
                    createConnection()
                    return connections.poll()
                } else if (connections.size != 0) {
                    return connections.poll()
                }
            }
        }
        var connection: Connection? = null
        try {
            Thread.sleep(1)
            //当连接池为空时循环获取直到超时或获取到连接为止
            while (connection == null && System.currentTimeMillis() - start < timeOut) {
                if (connections.size > 0) {
                    synchronized(connections) {
                        if (connections.size > 0)
                            connection = connections.poll()
                    }
                }
                Thread.sleep(1)
            }
            if (connection == null)
                throw TimeoutException()
            return connection!!
        } catch (e: InterruptedException) {
            e.printStackTrace()
            throw TimeoutException()
        } finally {
            destoryConnection()
        }
    }


    /**
     * 回收连接
     * @param con 使用完毕的数据库连接
     */
    override fun recycle(connection: Connection) {
        synchronized(connections) {
            connections.add(connection)
        }
    }


}