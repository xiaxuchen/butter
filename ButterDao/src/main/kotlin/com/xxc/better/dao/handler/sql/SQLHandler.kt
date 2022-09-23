package com.xxc.better.dao.handler.sql

import kotlin.reflect.KFunction

/**
 * @class SQLHandler
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-03 19:25
 */
interface SQLHandler {

    fun handleSQL(func: KFunction<*>): Pair<Boolean,String>

}