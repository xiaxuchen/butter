package com.xxc.better.dao.session

/**
 * @class SqlSession
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-05-30 20:10
 */
interface SqlSession {
    /**
     * 动态代理Dao层
     */
    fun <T> open(clazz: Class<T>):T
}