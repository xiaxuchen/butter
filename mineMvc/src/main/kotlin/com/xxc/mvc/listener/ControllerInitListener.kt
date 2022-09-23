package com.xxc.mvc.listener

import com.xxc.mvc.controller.ControllerManager
import com.xxc.util.logger.factory.LoggerFactory
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener

/**
 * @class ControllerInitListener
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-29 19:57
 */
class ControllerInitListener:ServletContextListener {

    val logger = LoggerFactory.getLogger(ControllerInitListener::class.java)

    override fun contextInitialized(sce: ServletContextEvent) {
        //将在web.xml中指定的包名设置给ControllerManager
        val paths:List<String>? = sce.servletContext.getInitParameter("Controllers").split(",")
        if(paths != null)
        {
            logger.info("正在扫描${paths}下的controller")
            if(!paths.isEmpty())
                ControllerManager.scan(mutableSetOf(*paths.toTypedArray()))
            logger.info("扫描完成")
        }
    }

    override fun contextDestroyed(sce: ServletContextEvent?) {
    }
}