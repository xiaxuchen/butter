package com.xxc.better.listener

import com.xxc.better.db.DataBaseHelper
import com.xxc.better.sql.pool.Pool

import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class JDBCListener
 * @Description
 * @createTime 2019-04-23 15:55
 */
class JDBCListener : ServletContextListener {


    override fun contextInitialized(sce: ServletContextEvent) {
        var propsPath: String? = sce.servletContext.getInitParameter("JDBCPropertiesPath")
        println(propsPath)
        if (propsPath == null)
            propsPath = Pool.DEFAULT_PATH
        println(sce.servletContext.getRealPath(propsPath))
        try {
            Pool.init(sce.servletContext.getRealPath(propsPath))
            DataBaseHelper.init(Pool.getInstance())
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun contextDestroyed(sce: ServletContextEvent) {}
}
