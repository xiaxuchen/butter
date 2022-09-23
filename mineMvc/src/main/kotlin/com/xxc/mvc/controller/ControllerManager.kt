package com.xxc.mvc.controller

import com.xxc.mvc.annotations.controller.Controller
import com.xxc.mvc.annotations.controller.RestController
import com.xxc.mvc.annotations.mapping.Method
import com.xxc.mvc.exception.RequestConfilctException
import com.xxc.mvc.exception.RequestNotFoundException
import com.xxc.mvc.handler.param.ParamHandler
import com.xxc.mvc.handler.param.impl.NormalParamHandler
import com.xxc.mvc.handler.returned.ReturnedHandler
import com.xxc.mvc.handler.returned.impl.NormalReturnedHandler
import com.xxc.mvc.handler.url.URLHandler
import com.xxc.mvc.handler.url.impl.NormalURLHandler
import com.xxc.util.logger.factory.LoggerFactory
import com.xxc.util.scan.impl.ClasspathPackageScanner
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.KFunction
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotation

/**
 * @class ControllerManager
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-27 10:56
 */

object ControllerManager {


    private var imported:MutableSet<String> = mutableSetOf()

    fun scan(packages:MutableSet<String>){
        //将package中已导入的包除去
        packages.minus(imported)

        //当packages中的包是已导入的子包则直接移除
        val iterator = packages.iterator()
        iterator.forEach{p->
            imported.forEach{ i->
                if(p.startsWith("$i."))
                    iterator.remove()
            }
        }

        //扫描
        val classes:MutableSet<String> = mutableSetOf()
        packages.forEach{
            println(it)
            val ps = ClasspathPackageScanner(it)
            try {
                ps.getFullyQualifiedClassNameList(true).forEach {
                    println("找到class:$it")
                }
                classes.addAll(ps.getFullyQualifiedClassNameList(true))
            }catch (e:Exception)
            {
                logger.info("扫描出现异常,没有找到此包:$it")
            }
        }
        classes.forEach{
            val kClass = Class.forName(it).kotlin
            val controller = kClass.findAnnotation<Controller>()
            val restController = kClass.findAnnotation<RestController>()
            if(controller != null || restController != null)
            {
                ControllerManager.registerController(kClass.createInstance())
            }
        }
        println(controllers)
        imported.addAll(packages)//将packages导入的包加入到已导入列表
        packages.clear()//清空packages中的数据
    }

    //处理类
    class HandleObject(val controller:Any,val func:KFunction<*>)
    {
        fun handle(request: HttpServletRequest,response: HttpServletResponse):Any?
        {
            return func.call(controller, *paramHandler.handleParams(func,request,response).toArray())
        }
    }

    private val logger = LoggerFactory.getLogger(ControllerManager::class.java)

    //所有的路径的映射
    private val controllers:HashMap<Method,HashMap<String,HandleObject>> = hashMapOf()

    //参数处理器
    var paramHandler:ParamHandler = NormalParamHandler()

    var urlHandler: URLHandler = NormalURLHandler()

    var returnedHandler: ReturnedHandler = NormalReturnedHandler()


    fun registerController(controller:Any){
        //处理url
        val list = urlHandler.handleURL(controller)
        list.forEach { (method,url,handleObj)->
            //判断冲突
            if(ControllerManager.controllers[method] == null)
                ControllerManager.controllers[method] = HashMap()
            val curCon = ControllerManager.controllers[method]?.get(url)
            if(curCon != null)
                throw RequestConfilctException("$controller.${handleObj.func} 与 ${curCon.controller}.${curCon.func} 所映射的URL冲突,都为$url")
            ControllerManager.controllers[method]?.put(url, handleObj)
        }
    }


    /**
     * 处理请求
     */
    fun handle(request : HttpServletRequest,response : HttpServletResponse,
               method: Method,url:String){
        println(controllers)
        var handled = false
        var methodMap = controllers[method]
        if(methodMap == null && method == Method.Any)
            throw RequestNotFoundException("无法找到以${method}请求的${url}路径")
        if(methodMap != null)
        {
            for((u,handler) in methodMap)
            {
                if(u == url)
                {
                    //由返回处理器处理
                    returnedHandler.handleReturned(handler.handle(request,response),handler.func,request,response)
                    return
                }
            }
            //当相应方法请求的map不为空且处理过后，将其标志为已处理
            handled = true
        }
        if(method != Method.Any)
        {
            methodMap = controllers[Method.Any]
            //当相应的方法没有处理成功且其Method不为任意，则标志位未处理
            handled = false
        }
        //当处理的map不为空且未处理，进行处理
        if(methodMap != null && !handled)
        {
            for((u,handler) in methodMap) {
                if (u == url) {
                    //由返回处理器处理
                    returnedHandler.handleReturned(handler.handle(request,response),handler.func,request,response)
                    return
                }
            }
        }
        throw RequestNotFoundException("使用${method}方法请求的${url}无法处理")
    }


}
