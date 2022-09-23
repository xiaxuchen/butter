package com.xxc.util.delegate

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy


/**
 * @class DelegateHandler
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-30 20:14
 */
abstract class DelegateHandler : InvocationHandler {
    /**
     * 绑定委托对象并返回一个代理类
     * @param target
     * @return
     */
    fun <T> delegate(clazz: Class<T>): T {
        //取得代理对象
        @Suppress("UNCHECKED_CAST")
        return Proxy.newProxyInstance(clazz.classLoader,
                arrayOf(clazz), this) as T  //要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)
    }

}