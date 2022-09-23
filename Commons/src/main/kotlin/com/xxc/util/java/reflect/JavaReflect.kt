package com.xxc.util.java.reflect

import java.lang.reflect.Method

/**
 * @class JavaReflect
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-03 13:41
 */
fun Class<*>.setter(fieldName:String): Method {
    return this.getMethod("set${fieldName.substring(0,1).toUpperCase()}${fieldName.substring(1)}",getDeclaredField(fieldName).type)
}

fun Class<*>.getter(fieldName:String): Method {
    return this.getMethod("get${fieldName.substring(0,1).toUpperCase()}${fieldName.substring(1)}")?:
    this.getMethod("is${fieldName.substring(0,1).toUpperCase()}${fieldName.substring(1)}")
}

