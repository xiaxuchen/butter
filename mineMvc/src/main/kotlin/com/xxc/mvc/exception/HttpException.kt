package com.xxc.mvc.exception

import com.xxc.mvc.http.HttpState

/**
 * @class HttpException
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-27 20:56
 */
open class HttpException(val state:HttpState? = null,val msg:String? = null):Exception(msg)