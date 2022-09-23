package com.xxc.mvc.web.util

import com.xxc.mvc.exception.InvalidOperateException
import javax.servlet.ServletContext

/**
 * @class PathUtil
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-13 2:12
 */
object PathUtil {

    private var context:ServletContext? = null

    fun init(context:ServletContext){
        PathUtil.context = context
    }

    /**
     * 获取相对资源下的路径
     */
    fun getPath(path:String):String{
        if(context == null)
            throw InvalidOperateException("Invalid operation,never init PathUtil before use it")
        return context!!.getRealPath(path)
    }
}