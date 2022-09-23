package com.xxc.better.dao.handler.type.impl

import com.xxc.better.dao.handler.type.TypeHandler
import com.xxc.better.dao.handler.type.converter.TypeConverter
import com.xxc.better.dao.handler.type.converter.impl.TimesConverter
import com.xxc.better.sql.bean.SQLBean

/**
 * @class DefaultTypeHandler
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-04 15:59
 */
class DefaultTypeHandler:TypeHandler {

    val converters:HashMap<Int,TypeConverter> = HashMap()

    init {
        registerTypeConverter(TimesConverter())
    }

    override fun registerTypeConverter(converter: TypeConverter) {
        converter.jdbcType().forEach {
            converters[it] = converter
        }
    }


    override fun handleType(sqlBean: SQLBean): SQLBean {
        if(converters.containsKey(sqlBean.jdbcType))
            return converters[sqlBean.jdbcType]?.converter(sqlBean)?:sqlBean
        return sqlBean
    }
}