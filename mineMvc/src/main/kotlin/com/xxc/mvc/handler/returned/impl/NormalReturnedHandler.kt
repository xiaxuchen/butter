package com.xxc.mvc.handler.returned.impl

import com.xxc.mvc.annotations.returned.ResponseBody
import com.xxc.mvc.handler.returned.ReturnedHandler
import com.xxc.mvc.model.ModelAndView
import com.xxc.util.parser.json.JSONParser
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.KFunction
import kotlin.reflect.full.findAnnotation

/**
 * @class NormalReturnedHandler
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-28 14:55
 */
class NormalReturnedHandler:ReturnedHandler {
    override fun handleReturned(returned: Any?,func:KFunction<*>,request: HttpServletRequest, response: HttpServletResponse) {
        if(returned == null)
            return
        val responseBody = func.findAnnotation<ResponseBody>()
        if(responseBody != null)
        {
            response.contentType = "application/json;charset=UTF-8"
            response.writer.print(JSONParser.instance.toJson(returned))
            return
        }
        var view:String? = null
        if(returned is ModelAndView)
        {
            returned.handle(request,request.session,request.servletContext)
            view = returned.view
        }
        if(returned is String)
            view = returned

        //处理视图的转发
        if( view != null)
        {
            when {
                view.startsWith("r:") -> response.sendRedirect(view.removePrefix("r:"))
                view.startsWith("f:") -> request.getRequestDispatcher(view.removePrefix("f:")).forward(request,response)
                else -> request.getRequestDispatcher(view).forward(request,response)
            }
        }
    }
}