package com.xxc.better.sql.connector

import java.sql.Connection

/**
 * @class Connector
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-12 22:24
 */
interface Connector {

    /**
     * 连接数据库获取到连接
     */
    fun connect():Connection
}