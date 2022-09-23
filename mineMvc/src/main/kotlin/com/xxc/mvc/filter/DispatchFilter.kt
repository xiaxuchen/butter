package com.xxc.mvc.filter

import com.xxc.mvc.annotations.mapping.Method
import com.xxc.mvc.controller.ControllerManager
import com.xxc.mvc.exception.RequestNotFoundException
import com.xxc.mvc.request.decorate.FileUploadRequest
import com.xxc.util.logger.factory.LoggerFactory
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @class DispatchFilter
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-28 17:43
 */
class DispatchFilter:Filter {

    private val logger = LoggerFactory.getLogger(DispatchFilter::class.java)

    override fun destroy() {
    }

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val req = FileUploadRequest(request as HttpServletRequest)
        val resp = response as HttpServletResponse
        val url = req.requestURI.toString()
        try {
            ControllerManager.handle(req, resp, Method.valueOf(req.method.toUpperCase()), url)
        }catch (e:Exception)
        {
            if(e.message != null)
                logger.info(e.message!!)
            else
                e.printStackTrace()
            if(e is RequestNotFoundException)
            {
                chain.doFilter(req,resp)
                logger.info("${url}未找到,使用系统查找")
            }
        }
    }

    override fun init(filterConfig: FilterConfig) {
        filterConfig.servletContext
    }
}