package com.xxc.better.sql.connector.impl

import com.xxc.better.sql.connector.Connector
import java.sql.Connection
import java.sql.DriverManager

/**
 * @class ConnectorImpl
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-12 22:26
 */
class ConnectorImpl(private val url:String,private val username:String,private val pwd:String):Connector {


    init{
        Class.forName("com.mysql.jdbc.Driver")
    }


    override fun connect(): Connection {
        return DriverManager.getConnection(url,username,pwd)
    }

}