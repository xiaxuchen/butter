package com.xxc.util.logger.factory.impl

import com.xxc.util.logger.Logger
import com.xxc.util.logger.factory.LoggerFactory
import com.xxc.util.logger.impl.DefaultLogger

/**
 * @class DefaultFactory
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-29 21:00
 */
class DefaultFactory:LoggerFactory() {
    override fun logger(clazz: Class<*>): Logger {
        return DefaultLogger()
    }
}