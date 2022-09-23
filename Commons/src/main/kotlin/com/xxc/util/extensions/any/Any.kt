package com.xxc.util.extensions.any

import com.xxc.bean.Property
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.kotlinProperty

/**
 * @class Any
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-13 0:59
 */
fun Any.attrIsEmpty(excludes:List<String>? = null):Boolean{
    this::class.java.declaredFields.forEach {
        if (it.kotlinProperty?.findAnnotation<Property>() != null) {
            if(excludes?.contains(it.name) != true)
            {
                it.isAccessible = true
                if(it.get(this) == null)
                {
                    println(it.name)
                    return false
                }
            }
        }
    }
    return true
}