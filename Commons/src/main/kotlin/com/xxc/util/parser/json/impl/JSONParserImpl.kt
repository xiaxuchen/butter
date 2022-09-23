package com.xxc.util.parser.json.impl

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.xxc.util.parser.json.JSONParser
import com.xxc.util.parser.json.exception.JSONParseException
import java.lang.Exception

/**
 * @class JSONParserImpl
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-28 16:20
 */
class JSONParserImpl:JSONParser {

    private val gson:Gson

    init {
        val builder = GsonBuilder()
        // 支持Map的key为复杂对象的形式
        builder.enableComplexMapKeySerialization()
        // 格式化date型　　
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
        //设置良好的格式
        builder.setPrettyPrinting()
        gson = builder.create()
    }

    override fun <T> fromJson(json: String, clazz: Class<T>): T {
        try {
            return gson.fromJson<T>(json,object :TypeToken<T>(){}.type)
        }catch (e:Exception)
        {
            e.printStackTrace()
            throw JSONParseException("无法转换为对象",e)
        }
    }

    override fun toJson(obj: Any): String {
        try {
            return gson.toJson(obj)
        }catch (e:Exception)
        {
            e.printStackTrace()
            throw JSONParseException("无法转换为JSON",e)
        }
    }
}