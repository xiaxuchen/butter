package com.xxc.mvc.handler.param

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.KFunction

/**
 * @class ParamHandler
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-28 14:30
 */
interface ParamHandler {

    /**
     * 处理调用方法需要的参数
     */
    fun handleParams(func:KFunction<*>, request: HttpServletRequest, response: HttpServletResponse):ArrayList<Any?>
}