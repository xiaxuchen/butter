package com.xxc.homework.dao

import com.xxc.better.dao.session.factory.SqlSessionFactory
import com.xxc.homework.entity.Category
import com.xxc.homework.entity.Product
import org.junit.Before
import org.junit.Test

/**
 * @class ProductDaoTest
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-13 0:51
 */
class ProductDaoTest{
    lateinit var productDao:ProductDao

    @Before
    fun before(){
        productDao = SqlSessionFactory.sqlSession.open(ProductDao::class.java)
        DaoInit.init()
    }

    @Test
    fun insert(){
        val product = Product()
        product.c = Category(17)
        product.name = "小刀"
        product.count = 100
        product.des = "非常锋利,削铁如泥,居家必备"
        product.price = 10.0
        product.photo = "/xiaodao/p.jpg"
        productDao.insert(product)
    }

    @Test
    fun selectList() {
        println(Product.getList(19,1))
    }

    @Test
    fun selectCount() {
        println(productDao.selectCount(17))
    }
}