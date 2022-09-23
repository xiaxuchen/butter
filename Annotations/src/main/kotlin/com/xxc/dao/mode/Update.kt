package com.xxc.dao.mode

/**
 * @class Select
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-30 19:42
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Update(val value:String)