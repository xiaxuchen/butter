package com.xxc.security

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @class Realm
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-07 10:33
 */
interface Realm {

    fun authorize(request: HttpServletRequest,response: HttpServletResponse):Boolean
}