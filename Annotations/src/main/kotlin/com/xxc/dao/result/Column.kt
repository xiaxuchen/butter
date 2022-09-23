package com.xxc.dao.result

/**
 * @class Column
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-03 10:34
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Column(val column:String = "",val property:String,
                    val javaType:String = "",val jdbcType:String = "")