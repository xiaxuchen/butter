package com.xxc.mvc.http

import com.xxc.mvc.exception.ParamInvalidException

/**
 * @class HttpState
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-27 20:52
 */
enum class HttpState {
    S404,S500;
    companion object {

        fun valueOf(code:Int):HttpState
        {
            when(code)
            {
                404 -> return S404
                500 -> return S500
            }
            throw ParamInvalidException("参数无匹配HttpState")
        }
    }
}