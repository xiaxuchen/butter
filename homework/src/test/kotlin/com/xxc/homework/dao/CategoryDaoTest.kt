package com.xxc.homework.dao

import com.xxc.better.dao.session.factory.SqlSessionFactory
import org.junit.Before
import org.junit.Test

/**
 * @class CategoryDaoTest
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-12 22:55
 */
class CategoryDaoTest {

    lateinit var categoryDao:CategoryDao

    @Before
    fun befor(){
        categoryDao = SqlSessionFactory.sqlSession.open(CategoryDao::class.java)
        DaoInit.init()
    }

    @Test
    fun selectParentCategories(){
        println(categoryDao.selectChildCategories(1))
    }
}