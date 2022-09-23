package com.xxc.better.dao.handler.type

import com.xxc.better.dao.handler.type.converter.TypeConverter
import com.xxc.better.sql.bean.SQLBean

/**
 * @class TypeHandler
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-04 15:58
 */
interface TypeHandler {


    fun registerTypeConverter(converter: TypeConverter)

    /**
     * 对sqlbean进行类型转换
     */
    fun handleType(sqlBean: SQLBean):SQLBean
}