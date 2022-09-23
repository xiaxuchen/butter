package com.xxc.mvc.handler.param.impl

import com.xxc.mvc.annotations.param.RequestParams
import com.xxc.mvc.exception.ParamInvalidException
import com.xxc.mvc.exception.ParamNotFoundException
import com.xxc.mvc.exception.TypeNotMatchException
import com.xxc.mvc.file.MultiFile
import com.xxc.mvc.handler.param.ParamHandler
import com.xxc.mvc.request.decorate.FileUploadRequest
import com.xxc.util.logger.factory.LoggerFactory
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession
import kotlin.reflect.KFunction
import kotlin.reflect.KType
import kotlin.reflect.full.findAnnotation

/**
 * @class NormalParamHandler
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-28 14:33
 */
class NormalParamHandler:ParamHandler {
    val logger = LoggerFactory.getLogger(NormalParamHandler::class.java)
    override fun handleParams(func: KFunction<*>, request: HttpServletRequest, response: HttpServletResponse): ArrayList<Any?> {
        val parameters = arrayListOf<Any?>()
        func.parameters.forEach{
            //第一个参数是调用者,什么也不做
            if(it.index != 0)
            {
                if(it.type.classifier == ServletRequest::class || it.type.classifier == HttpServletRequest::class)
                    parameters.add(request)
                else if(it.type.classifier == ServletResponse::class || it.type.classifier == HttpServletResponse::class)
                    parameters.add(response)
                else if(it.type.classifier == HttpSession::class)
                    parameters.add(request.session)
                else {
                    val requestParams = it.findAnnotation<RequestParams>()
                    println(requestParams)
                    if (requestParams == null) {
                        //TODO 如果参数不对，则抛异常
                        throw ParamInvalidException("parameter ${it.name} 不是WEB相关类型,必须使用注解")
                    } else {
                        var paramName: String = requestParams.value//从注解获取参数名称
                        if (paramName == "") paramName = it.name ?: ""//如果注解为默认值，则直接获取参数名
                        //如果获取的参数名为空则抛出异常
                        if (paramName == "") throw ParamInvalidException("要调用的方法的参数名称未知")
                        //将请求中的参数装填到方法的形参中
                        val parameter:Any?
                        //处理文件类型
                        parameter = if(requestParams.isFile && request is FileUploadRequest) {
                            when {
                                it.type.classifier == List::class -> request.getFiles(paramName)
                                it.type.classifier == MultiFile::class -> request.getFile(paramName)
                                else -> null
                            }

                        }else {
                            println(request.getParameter(paramName))
                            request.getParameter(paramName)
                        }
                        //当参数为空且请求参数不可空时抛出异常
                        if (parameter == null && !requestParams.nullable)
                            throw ParamNotFoundException("param named ${it.name} and reflect to $paramName not found in request")
                        parameters.add(handleBaseTypes(parameter, it.type))
                    }
                }
            }
        }
        return parameters
    }

    private fun handleBaseTypes(value:Any?,type: KType):Any?{
        if(value == null)
            return null
        return if(value is String)
        {
            when(type.classifier)
            {
                String::class -> value
                Int::class -> value.toInt()
                Long::class -> value.toLong()
                Float::class -> value.toFloat()
                Boolean::class -> value.toBoolean()
                Byte::class -> value.toByte()
                Double::class -> value.toDouble()
                Short::class -> value.toShort()
                else ->
                    throw TypeNotMatchException("当前RequestParams注解暂不支持此类型:${type.classifier}")
            }
        }else
            value
    }
}