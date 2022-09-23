package com.xxc.mvc.model

import javax.servlet.ServletContext
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

/**
 * @class Model
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-28 16:39
 */
open class Model {
    private var requestAttrs:HashMap<String,Any>? = null

    private var sessionAttrs:HashMap<String,Any>? = null

    private var applicationAttrs:HashMap<String,Any>? = null

    @JvmOverloads
    fun addAttribute(key:String,value:Any,scope: Scope = Scope.REQUEST)
    {
        when(scope)
        {
            Scope.REQUEST ->{
                if(requestAttrs == null)
                {
                    requestAttrs = HashMap()
                }
                requestAttrs!![key] = value
            }
            Scope.SESSION -> {
                if(sessionAttrs == null)
                {
                    sessionAttrs = HashMap()
                }
                sessionAttrs!![key] = value
            }
            Scope.APPLICATION ->{
                if(applicationAttrs == null)
                {
                    applicationAttrs = HashMap()
                }
                applicationAttrs!![key] = value
            }
        }

    }

    fun handle(req:HttpServletRequest,session: HttpSession,application:ServletContext)
    {
        requestAttrs?.forEach { (key,value) ->
            req.setAttribute(key,value)
        }
        sessionAttrs?.forEach{(key,value)->
            session.setAttribute(key,value)
        }
        applicationAttrs?.forEach{(key,value)->
            application.setAttribute(key,value)
        }
    }

    enum class Scope{
        REQUEST,SESSION,APPLICATION;
    }
}