package com.xxc.better.dao.handler.param

import kotlin.reflect.KFunction

/**
 * @class ParamHandler
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-03 19:12
 */
interface ParamHandler {

    /**
     * @param func 所调用的方法
     * @param sql SQL语句
     * @param args 调用方法的参数
     */
    fun handleParameter(func: KFunction<*>, sql: String, args: Array<out Any>?):ReplaceResult

    data class ReplaceResult(val sql: String,val params:List<Any?>)
}