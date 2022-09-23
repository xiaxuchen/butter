package com.xxc.mvc.exception

import com.xxc.mvc.http.HttpState

/**
 * @class RequestNotFoundException
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-27 20:08
 */
class RequestNotFoundException( msg: String?,state: HttpState? = null) : HttpException(state, msg)