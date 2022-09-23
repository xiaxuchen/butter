package com.xxc.better.dao.handler.param.impl

import com.xxc.better.dao.handler.param.ParamHandler
import com.xxc.dao.param.Param
import com.xxc.util.java.reflect.getter
import java.util.regex.Pattern
import kotlin.reflect.KFunction
import kotlin.reflect.full.findAnnotation

/**
 * @class DefaultParamHandler
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-03 19:30
 */
class DefaultParamHandler:ParamHandler {
    override fun handleParameter(func: KFunction<*>, sql: String, args: Array<out Any>?): ParamHandler.ReplaceResult {
        var localSql = sql
        localSql = `handle$`(func,localSql,args)
        return `handle#`(func,localSql,args)
    }

    private fun `handle#`(func: KFunction<*>, sql: String, args: Array<out Any>?):ParamHandler.ReplaceResult{
        val params = mutableListOf<Any?>()
        val m = Pattern.compile("""#\{([\w\.]+)\}""").matcher(sql)
        while(m.find())
        {
            val pName = m.group(1)
            if (pName.contains("."))
            {
                val split = pName.split(""".""")
                val index = getParam(func, split[0])
                if(index == -1)
                    throw Exception("找不到参数$pName")
                var temp = args?.get(index)
                if(temp == null)
                {
                    params.add(temp)
                    continue
                }
                for(i in 1 until  split.size)
                {
                    if(temp == null)
                    {
                        break
                    }
                    temp = temp.javaClass.getter(split[i]).invoke(temp)
                }
                params.add(temp)
            }else{
                val index = getParam(func, pName)
                if(index == -1)
                    throw Exception("找不到参数$pName")
                params.add(args?.get(index))
            }

        }
        val replaceAll = m.replaceAll("\\?")
        return ParamHandler.ReplaceResult(replaceAll, params)
    }

    private fun `handle$`(func: KFunction<*>, sql: String, args: Array<out Any>?):String{
        var newSql:String = sql
        val m = Pattern.compile("""\$\{([\w\.]+)\}""").matcher(sql)
        val sb = StringBuffer()
        while(m.find())
        {
            val pName = m.group(1)
            if (pName.contains("."))
            {
                val split = pName.split(""".""")
                val index = getParam(func, split[0])
                if(index == -1)
                    throw Exception("找不到参数$pName")
                var temp = args?.get(index)
                if(temp == null)
                {
                    m.appendReplacement(sb,"null")
                    continue
                }
                for(i in 1 until  split.size)
                {
                    if(temp == null)
                    {
                        break
                    }
                    temp = temp.javaClass.getter(split[i]).invoke(temp)
                }
                m.appendReplacement(sb,temp.toString())
            }else{
                val index = getParam(func, pName)
                if(index == -1)
                    throw Exception("找不到参数$pName")
                m.appendReplacement(sb,args?.get(index).toString())
            }
        }
        if(!sb.toString().isEmpty())
            newSql = sb.toString()
        return newSql
    }

    private fun getParam(func: KFunction<*>,pName:String):Int
    {
        for (it in func.parameters.subList(1,func.parameters.size))
        {
            val param = it.findAnnotation<Param>()
            if(param != null) {
                val name: String = if (!param.value.isEmpty())
                    param.value
                else {
                    it.name!!
                }
                if(name == pName)
                    return it.index-1
            }
        }
        return -1
    }
}
