package com.xxc.better.dao.delegate

import com.xxc.better.dao.handler.param.GenerateKey
import com.xxc.better.dao.handler.param.ParamHandler
import com.xxc.better.dao.handler.param.impl.DefaultParamHandler
import com.xxc.better.dao.handler.result.ResultSetHandler
import com.xxc.better.dao.handler.result.impl.DefaultResultSetHandler
import com.xxc.better.dao.handler.sql.SQLHandler
import com.xxc.better.dao.handler.sql.impl.DefaultSQLHandler
import com.xxc.better.dao.handler.type.TypeHandler
import com.xxc.better.dao.handler.type.impl.DefaultTypeHandler
import com.xxc.better.dao.session.factory.SqlSessionFactory
import com.xxc.better.db.DataBaseHelper
import com.xxc.better.sql.bean.SQLBean
import com.xxc.better.sql.pool.Pool
import com.xxc.dao.mode.Insert
import com.xxc.dao.mode.Select
import com.xxc.dao.param.Param
import com.xxc.dao.result.Column
import com.xxc.dao.result.ID
import com.xxc.dao.result.Result
import com.xxc.util.delegate.DelegateHandler
import java.lang.reflect.Method
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.kotlinFunction

/**
 * @class DaoDelegateHandler
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-30 20:19
 */
class DaoDelegateHandler: DelegateHandler() {

    var paramHandler:ParamHandler = DefaultParamHandler()

    var sqlHandler:SQLHandler = DefaultSQLHandler()

    var resultSetHandler:ResultSetHandler = DefaultResultSetHandler()

    var typeHandler:TypeHandler = DefaultTypeHandler()

    data class ReplaceResult(val sql: String,val params:List<Any?>)

    override fun invoke(proxy: Any?, method: Method, args: Array<out Any>?): Any? {
        var result:Any? = null
        val func = method.kotlinFunction
        func?.let {
            result = handle(func,args)
        }
        return result
    }

    private fun handle(func: KFunction<*>, args: Array<out Any>?):Any?{
        val (isQuery,sql) = sqlHandler.handleSQL(func)
        val (newSql,params) = paramHandler.handleParameter(func,sql,args)

        return if(isQuery)//如果是查询语句则直接查询
        {
            val result = func.findAnnotation<Result>()
            var isMany = false
            var type:Class<*>? = null
            if(func.returnType.classifier == List::class)
            {
                isMany = true
                type = Class.forName(result?.javaType)
            }else if(result == null){
                type = (func.returnType.classifier as KClass<*>).java
            }else if(!result.javaType.isEmpty()){
                type = Class.forName(result.javaType)
            }else{
                type = (func.returnType.classifier as KClass<*>).java
            }
            val data:List<HashMap<String,SQLBean>> = DataBaseHelper.QUERY(newSql, *params.toTypedArray()).map { map ->
                for ((key, sqlBean) in map)
                    map[key] = typeHandler.handleType(sqlBean)
                map
            }
            resultSetHandler.handleRS(data,
                    result,isMany,type)
        }else{//否则更新
            var result = 0
            var gk:GenerateKey? = null
            func.parameters.forEach {
                if (it.type.classifier == GenerateKey::class) {
                    gk = args?.get(it.index-1) as GenerateKey?
                }
            }
            if(gk != null)
            {
                val generateKey = Array(1){-1}
                result = DataBaseHelper.UPDATEWITHGENERATEKEY(newSql,generateKey,*params.toTypedArray())
                gk!!.key = generateKey[0]
            }else {
                result = DataBaseHelper.UPDATE(newSql,*params.toTypedArray())
            }
            result
        }
    }


}
class User{
    var username:String? = null
    var pwd:String? = null
    override fun toString(): String {
        return "User(username=$username, pwd=$pwd)"
    }
}

interface UserDao{

    @Insert("insert into msg(username,content) values(#{msg.user.username},#{msg.content})")
    fun insert(@Param msg:Message,generateKey:GenerateKey):Int

    @Select("select * from user where username=#{username}")
    @Result(ID(property = "username"),
            columns = [
            (Column(property = "pwd"))
            ])
    fun findByUsername(@Param username:String):User

    fun hello()
    {
        println("hello")
    }
}

class Message{
    var content:String? = null

    var user:User? = null

    var id:Int? = null
    override fun toString(): String {
        return "Message(content=$content, user=$user)"
    }


}

fun main(args: Array<String>) {
    Pool.init("F:\\javaweb\\project\\Homework\\src\\main\\webapp\\jdbc.properties")
    DataBaseHelper.init(Pool.getInstance())
    val gk = GenerateKey()
    val message = Message()
    val user = User()
    message.user = user
    user.username = "xxc"
    message.content = "hello world"
    println(SqlSessionFactory.sqlSession.open(UserDao::class.java).hello())
}
