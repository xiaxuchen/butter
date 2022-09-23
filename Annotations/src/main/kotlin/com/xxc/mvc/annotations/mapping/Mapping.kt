package com.xxc.mvc.annotations.mapping

/**
 * @class Mapping
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-27 10:38
 */
@Target(AnnotationTarget.FUNCTION,AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Mapping(val url : String,val methods : Array<Method> = [(Method.Any)])