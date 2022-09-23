package com.xxc.util.logger

/**
 * @class Logger
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-29 20:29
 */
interface Logger {

    fun info(msg:Any,format:String? = null)

    fun error(msg:Any,format:String? = null)

    fun debug(msg:Any,format:String? = null)

    fun warn(msg:Any,format:String? = null)

    fun trace(msg:Any,format:String? = null)

}

