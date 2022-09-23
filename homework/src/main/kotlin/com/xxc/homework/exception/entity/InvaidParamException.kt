package com.xxc.homework.exception.entity

/**
 * @class InvaidParamException
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-12 23:36
 */
class InvaidParamException: Exception {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
    constructor(message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) : super(message, cause, enableSuppression, writableStackTrace)
}