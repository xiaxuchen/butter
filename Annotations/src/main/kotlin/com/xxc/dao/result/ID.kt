package com.xxc.dao.result

/**
 * @class ID
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-03 10:33
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class ID(val column:String = "",val property:String,
                    val javaType:String = "",val jdbcType:String = "")