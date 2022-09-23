package com.xxc.mvc.handler.returned

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.KFunction

/**
 * @class ReturnedHandler
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-28 14:53
 */
interface ReturnedHandler {

    /**
     * 处理返回值
     */
    fun handleReturned(returned:Any?,func:KFunction<*>,request: HttpServletRequest,response: HttpServletResponse)
}