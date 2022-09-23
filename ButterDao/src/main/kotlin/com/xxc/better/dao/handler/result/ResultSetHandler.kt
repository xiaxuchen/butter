package com.xxc.better.dao.handler.result

import com.xxc.better.sql.bean.SQLBean
import com.xxc.dao.result.Result

/**
 * @class ResultSetHandler
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-03 20:00
 */
interface ResultSetHandler{

    fun handleRS(data:List<HashMap<String,SQLBean>>, result: Result?, isMany: Boolean, type:Class<*>? = null):Any?
}