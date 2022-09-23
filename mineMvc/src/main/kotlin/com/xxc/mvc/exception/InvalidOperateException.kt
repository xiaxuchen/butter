package com.xxc.mvc.exception

/**
 * @class InvalidOperateException
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-13 2:17
 */
class InvalidOperateException:Exception {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
}