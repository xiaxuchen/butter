package com.xxc.homework.dao

import com.xxc.dao.mode.Insert
import com.xxc.dao.mode.Select
import com.xxc.dao.param.Param
import com.xxc.dao.result.ID
import com.xxc.dao.result.Result
import com.xxc.homework.entity.Product

/**
 * @class CartDao
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-16 15:13
 */
interface CartDao{

    @Insert("INSERT INTO shop_cart(username,product_id) values(#{username},#{productId})")
    fun insert(@Param username:String,@Param productId:Int):Int

    @Select("SELECT p.* FROM shop_cart c,product p where username=#{username} and c.product_id=p.id LIMIT \${start},\${len}")
    @Result(id = ID(property = "id"),
            autowire = true,javaType = "com.xxc.homework.entity.Product")
    fun selectList(@Param username: String,@Param start:Int,@Param len:Int):List<Product>

    @Select("SELECT COUNT(*) FROM shop_cart c,product p where username=#{username} and c.product_id=p.id")
    fun selectCount(@Param username: String): Int
}