package com.xxc.better.dao.session.factory

import com.xxc.better.dao.delegate.DaoDelegateHandler
import com.xxc.better.dao.session.SqlSession

/**
 * @class SqlSessionFactory
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-03 15:40
 */
class SqlSessionFactory {

    companion object {

        @JvmStatic
        var sqlSession = object : SqlSession {
            override fun <T> open(clazz: Class<T>): T {
                return DaoDelegateHandler().delegate(clazz)
            }
        }
    }

}
