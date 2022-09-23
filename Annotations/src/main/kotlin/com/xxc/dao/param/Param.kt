package com.xxc.dao.param

/**
 * @class Select
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-30 19:42
 */
@Target(AnnotationTarget.VALUE_PARAMETER,AnnotationTarget.TYPE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
//value为参数名称
annotation class Param(val value:String = "")