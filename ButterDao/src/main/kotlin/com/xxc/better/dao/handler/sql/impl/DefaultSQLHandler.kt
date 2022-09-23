package com.xxc.better.dao.handler.sql.impl

import com.xxc.better.dao.handler.sql.SQLHandler
import com.xxc.dao.mode.Delete
import com.xxc.dao.mode.Insert
import com.xxc.dao.mode.Select
import com.xxc.dao.mode.Update
import kotlin.reflect.KFunction

/**
 * @class DefaultSQLHandler
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-03 20:03
 */
class DefaultSQLHandler:SQLHandler {
    override fun handleSQL(func: KFunction<*>): Pair<Boolean,String> {
        val annotations = func.annotations
        var sql:String = ""
        var isQuery:Boolean = false
        annotations.forEach{
            when(it)
            {
                is Select ->{
                    sql = it.value
                    isQuery = true
                }
                is Delete ->{
                    sql = it.value
                }
                is Insert ->{
                    sql = it.value
                }
                is Update ->{
                    sql = it.value
                }
                else -> {
                    //TODO 停止
                }
            }
        }
        return Pair(isQuery,sql)
    }
}