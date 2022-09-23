package com.xxc.mvc.exception

import com.xxc.mvc.http.HttpState

/**
 * @class ParamInvalidException
 * @author 夏旭晨
 * @version 1.0.0
 * @Description 参数错误异常
 * @createTime 2019-05-27 19:24
 */
class ParamInvalidException( msg: String?,state: HttpState? = null) : HttpException(state, msg)