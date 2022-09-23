package com.xxc.mvc.annotations.param

/**
 * @class RequestParams
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-27 17:29
 */
@Target(AnnotationTarget.TYPE_PARAMETER,AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class RequestParams(val value : String = "",val nullable:Boolean = false,val isFile:Boolean = false)