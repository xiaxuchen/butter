package com.xxc.homework.dao

import com.xxc.better.dao.session.factory.SqlSessionFactory
import org.junit.Before
import org.junit.Test

/**
 * @class CartDaoTest
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-16 15:15
 */
class CartDaoTest {

    lateinit var cartDao:CartDao

    @Before
    fun before(){
        cartDao = SqlSessionFactory.sqlSession.open(CartDao::class.java)
        DaoInit.init()
    }

    @Test
    fun insert() {
        println(cartDao.insert("xxc", 1))
    }

    @Test
    fun selectList() {
        println(cartDao.selectCount("xxc"))
    }
}