package com.xxc.better.dao.handler.result.impl

import com.xxc.better.dao.handler.result.ResultSetHandler
import com.xxc.better.sql.bean.SQLBean
import com.xxc.dao.result.Result
import com.xxc.util.java.reflect.setter
import com.xxc.util.logger.factory.LoggerFactory
import kotlin.reflect.KClass

/**
 * @class DefaultResultSetHandler
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-15 20:46
 */
class DefaultResultSetHandler:ResultSetHandler {

    companion object {
        @JvmStatic
        val logger = LoggerFactory.getLogger(DefaultResultSetHandler.javaClass)
    }

    override fun handleRS(data:List<HashMap<String,SQLBean>>,result:Result?,isMany: Boolean,type:Class<*>?):Any?{
            //如果查到的数据为空则直接返回空
            if(data.isEmpty())
            {
                if(isMany)
                    return ArrayList<Any>()
                return null
            }
            if(type == null)
                return null
            if(result == null)
                return handleBaseType(type.kotlin,data[0].values.iterator().next().data.toString())
            try {
                val clazz:Class<*> = if(result.javaType.isEmpty())
                    type
                else
                    Class.forName(result.javaType)

                return handleResult(data, result, clazz, isMany)
            }catch (e:ClassNotFoundException)
            {
                e.printStackTrace()
            }

            return null
    }

    private fun handleBaseType(type:KClass<*>,value:String):Any?
    {
        return when(type)
        {
            String::class -> value
            Int::class -> value.toInt()
            Long::class -> value.toLong()
            Float::class -> value.toFloat()
            Boolean::class -> value.toBoolean()
            Byte::class -> value.toByte()
            Double::class -> value.toDouble()
            Short::class -> value.toShort()
            else ->
                throw Exception("当前RequestParams注解暂不支持此类型:$type")
        }
    }

    private fun handleResult(data: List<Map<String,SQLBean>>,result: Result,clazz: Class<*>,isMany:Boolean):Any?{
        //如果是list
        return if(isMany) {
            //通过主键分组
            val idColumn = if(result.id.column.isEmpty())
                result.id.property
            else
                result.id.column
            val groupedData = data.groupBy { it[idColumn] }
            val many = ArrayList<Any>()
            for(item in groupedData.values){
                many.add(handleCommon(clazz, result, item))
            }
            many
        }else{
            handleCommon(clazz, result, data)
        }
    }

    //处理公共部分
    private fun handleCommon(clazz:Class<*>,result: Result,item:List<Map<String,SQLBean>>):Any
    {
        val instance = clazz.newInstance()
        if(!result.one.isEmpty())
        {
            //当是one时返回hashmap<String,Any>，然后根据map的键来设置给相应的对象
            result.one.forEach {
                val newData = ArrayList<Map<String,SQLBean>>()
                newData.add(item[0])//因为只有一条，直接将第一条记录给他
                clazz.setter(it.property)(instance, handleResult(newData, it, Class.forName(it.javaType), false))
            }
        }
        if(!result.many.isEmpty())
        {
            //当时many时返回list<hashmap<String,any>>
            result.many.forEach {
                clazz.setter(it.property)(instance, handleResult(item, it, Class.forName(it.javaType), true))
            }
        }
        handleColumns(item[0], instance, result)
        return instance
    }

    //处理普通行
    private fun handleColumns(data:Map<String,SQLBean>,instance:Any,result: Result){
        val clazz = instance.javaClass
        val configed = ArrayList<String>()//已配置的属性列表
        val idColumn = if(result.id.column.isEmpty())
            result.id.property
        else
            result.id.column
        if(data.containsKey(idColumn))
        {
            configed.add(result.id.property)//将主键添加进去
            //设置主键行
            clazz.setter(result.id.property)(instance,data[idColumn]!!.data)
        }
        //给结果的属性赋值
        result.columns.forEach {
            val normalColumn = if(it.column.isEmpty())
                it.property
            else
                it.column
            configed.add(it.property)//添加配置了的普通字段
            try {
                clazz.setter(it.property)(instance,data[normalColumn]!!.data)
            }catch (e:Exception)
            {
                throw Exception("${it.property}的set方法未定义")
            }
        }
        if(result.autowire)
        {
            clazz.declaredFields.forEach {field->
                var isConfiged = false
                for(c in configed)
                {
                    if(field.name == c)
                    {
                        isConfiged = true
                        break
                    }
                }
                if(!isConfiged)
                {
                    field.isAccessible = true
                    if(data.containsKey(field.name))
                        field.set(instance,data[field.name]?.data)
                }
            }
        }
    }

}