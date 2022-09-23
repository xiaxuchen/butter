package com.xxc.dao.result

/**
 * @class Result
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-03 10:22
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Result(val id:ID, val columns:Array<Column> = [],
                        val one:Array<Result> = [], val many:Array<Result> = [],
                        val property:String = "",
                        val javaType:String = "",
                        val autowire:Boolean = false)

