package com.xxc.util.string

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class StringUtil
 * @Description
 * @createTime 2019-04-29 10:56
 */
class StringUtil {

    companion object {

        @JvmStatic
        fun isEmpty(str: String?): Boolean {
            return str?.isEmpty() ?: true
        }
    }

}
