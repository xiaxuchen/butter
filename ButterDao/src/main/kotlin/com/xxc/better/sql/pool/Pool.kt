package com.xxc.better.sql.pool

import com.xxc.better.sql.pool.impl.JDBCPool
import java.io.FileInputStream
import java.sql.Connection
import java.util.*

/**
 * @class Pool
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-13 10:10
 */
interface Pool {

    companion object {

        val DEFAULT_PATH = "jdbc.properties"

        private var path: String? = null

        private var INSTANCE: JDBCPool? = null

        fun init(path: String) {
            this.path = path
        }
        fun getInstance(): Pool {
            if (INSTANCE == null) {
                synchronized(JDBCPool::class.java) {
                    if (INSTANCE == null)
                        INSTANCE = JDBCPool(loadProperties())
                }
            }
            return INSTANCE!!
        }

        private fun loadProperties(): Properties {
            val props = Properties()
            props.load(FileInputStream(path))
            return props
        }
    }

    /**
     * 通过数据库连接池获取连接
     */
    fun getConnection():Connection

    /**
     * 回收连接
     */
    fun recycle(connection: Connection)
}