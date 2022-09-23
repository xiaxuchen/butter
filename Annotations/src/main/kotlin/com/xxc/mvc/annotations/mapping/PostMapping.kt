package com.xxc.mvc.annotations.mapping

/**
 * @class PostMapping
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-27 10:35
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class PostMapping(val url:String)