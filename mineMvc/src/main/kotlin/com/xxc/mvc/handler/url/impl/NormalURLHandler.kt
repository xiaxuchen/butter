package com.xxc.mvc.handler.url.impl

import com.xxc.mvc.annotations.mapping.*
import com.xxc.mvc.controller.ControllerManager
import com.xxc.mvc.exception.ParamInvalidException
import com.xxc.mvc.exception.TypeNotMatchException
import com.xxc.mvc.handler.url.URLHandler
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions

/**
 * @class NormalURLHandler
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-28 14:41
 */
class NormalURLHandler:URLHandler {
    override fun handleURL(controller: Any): List<URLHandler.URLObject> {
        val mapping = controller::class.findAnnotation<Mapping>()?:
        throw ParamInvalidException("参数不是符合规定的Controller")
        val prefix = mapping.url
        val list = ArrayList<URLHandler.URLObject>()
        controller::class.functions.forEach{
            func ->
            func.annotations.forEach{
                try{
                    val methods = Method.valuesOf(it)
                    val url = prefix + getUrl(it)
                    methods.forEach { method->
                        list.add(URLHandler.URLObject(method,url, ControllerManager.HandleObject(controller, func)))
                    }
                }catch (e:Exception)
                { }
            }
        }
        return list
//        throw ParamInvalidException("此Controller没有映射")
    }

    /**
     * 获取不同注解的url信息
     */
    private fun getUrl(annotation: Annotation):String
    {
        when(annotation){
            is PostMapping -> return annotation.url
            is GetMapping -> return annotation.url
            is PutMapping -> return annotation.url
            is DeleteMapping -> return annotation.url
            is Mapping -> return annotation.url
        }
        throw TypeNotMatchException("注解不匹配")
    }

}