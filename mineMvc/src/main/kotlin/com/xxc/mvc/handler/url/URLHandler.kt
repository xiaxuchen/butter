package com.xxc.mvc.handler.url

import com.xxc.mvc.annotations.mapping.Method
import com.xxc.mvc.controller.ControllerManager


/**
 * @class URLHandler
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-28 14:38
 */
interface URLHandler {

    /**
     * 处理路径
     */
    fun handleURL(controller:Any):List<URLObject>

    data class URLObject(val method:Method,val url:String,val handlerObject: ControllerManager.HandleObject)
}