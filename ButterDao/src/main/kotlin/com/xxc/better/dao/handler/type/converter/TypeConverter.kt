package com.xxc.better.dao.handler.type.converter

import com.xxc.better.sql.bean.SQLBean

/**
 * @class TypeConverter
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-04 16:02
 */
interface TypeConverter {

    fun converter(sqlBean: SQLBean):SQLBean

    fun jdbcType():Array<Int>
}