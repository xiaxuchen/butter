package com.xxc.util.logger.impl

import com.xxc.util.logger.Logger

/**
 * @class DefaultLogger
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-29 20:44
 */
class DefaultLogger:Logger {

    private fun getTag():String{
        var index = 0
        for ((i,stackTraceElement) in Thread.currentThread().stackTrace.withIndex()) {
            if(stackTraceElement.className == DefaultLogger::class.java.name)
            {
                index = i+2
            }
        }
        val element = Thread.currentThread().stackTrace[index]
        return "${element.className}.${element.methodName}() lines:${element.lineNumber}"
    }

    override fun info(msg: Any, format: String?) {
        println("#${getTag()} INFO -> $msg")
    }

    override fun error(msg: Any, format: String?) {
        println("#${getTag()} ERROR -> $msg")
    }

    override fun debug(msg: Any, format: String?) {
        println("#${getTag()} DEBUG -> $msg")
    }

    override fun warn(msg: Any, format: String?) {
        println("#${getTag()} WARN -> $msg")
    }

    override fun trace(msg: Any, format: String?) {
        println("#${getTag()} TRACE -> $msg")
    }
}