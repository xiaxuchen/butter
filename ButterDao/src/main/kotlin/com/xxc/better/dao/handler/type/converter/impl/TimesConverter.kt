package com.xxc.better.dao.handler.type.converter.impl

import com.xxc.better.dao.handler.type.converter.TypeConverter
import com.xxc.better.sql.bean.SQLBean
import java.sql.Timestamp
import java.sql.Types
import java.util.*

/**
 * @class TimesConverter
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-04 16:04
 */
class TimesConverter:TypeConverter {
    override fun converter(sqlBean: SQLBean): SQLBean {
        val data = sqlBean.data
        data?.let {
            when(data)
            {
                is Timestamp -> sqlBean.data = Date(data.time)
                is java.sql.Date -> sqlBean.data = Date(data.time)
            }
        }
        return sqlBean
    }

    override fun jdbcType(): Array<Int> {
        return arrayOf(Types.DATE,Types.TIME,Types.TIMESTAMP,Types.TIME_WITH_TIMEZONE,Types.TIMESTAMP_WITH_TIMEZONE)
    }
}