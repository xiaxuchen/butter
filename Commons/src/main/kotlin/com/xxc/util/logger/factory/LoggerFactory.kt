package com.xxc.util.logger.factory

import com.xxc.util.logger.Logger
import com.xxc.util.logger.factory.impl.DefaultFactory

/**
 * @class LoggerFactory
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-29 20:41
 */
abstract class LoggerFactory {

    companion object {

        var factory: LoggerFactory = DefaultFactory()

        @JvmStatic
        fun getLogger(clazz:Class<*>):Logger
        {
            return factory.logger(clazz)
        }
    }

    protected abstract fun logger(clazz:Class<*>): Logger

}
