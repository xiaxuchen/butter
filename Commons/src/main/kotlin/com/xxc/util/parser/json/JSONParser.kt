package com.xxc.util.parser.json

import com.xxc.util.parser.json.exception.JSONParseException
import com.xxc.util.parser.json.impl.JSONParserImpl

/**
 * @class JSONParser
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-28 16:17
 */
interface JSONParser {

    companion object {
        var instance:JSONParser = JSONParserImpl()
    }

    /**
     * @param obj 需要转换的对象
     * @return json
     */
    @Throws(JSONParseException::class)
    fun toJson(obj:Any):String

    /**
     * @param json JSON字符
     * @param clazz 对象的字节码
     * @return 对象
     */
    @Throws(JSONParseException::class)
    fun <T> fromJson(json:String,clazz:Class<T>):T
}