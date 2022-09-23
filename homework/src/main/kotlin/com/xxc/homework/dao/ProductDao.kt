package com.xxc.homework.dao

import com.xxc.dao.mode.Insert
import com.xxc.dao.mode.Select
import com.xxc.dao.mode.Update
import com.xxc.dao.param.Param
import com.xxc.dao.result.ID
import com.xxc.dao.result.Result
import com.xxc.homework.entity.Product

/**
 * @class ProductDao
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-10 11:35
 */
interface ProductDao {

    @Update("REPLACE INTO product(id,name,des,c_id,price) VALUES (#{p.id},#{p.name},#{p.des},#{p.c.id},#{p.price});")
    fun replace(@Param p:Product):Int

    @Insert("INSERT INTO product(name,des,price,c_id,count,photo) values(#{p.name},#{p.des},#{p.price},#{p.c.id},#{p.count},#{p.photo})")
    fun insert(@Param p: Product):Int

    @Select("SELECT * FROM product WHERE c_id=#{cid} LIMIT \${start},\${len} ORDER BY id DESC")
    @Result(id = ID(property = "id"),
            autowire = true,javaType = "com.xxc.homework.entity.Product")
    fun selectList(@Param cid:Int,@Param start:Int,@Param len:Int):List<Product>

    @Select("SELECT COUNT(*) FROM product WHERE c_id=#{cid}")
    fun selectCount(@Param cid: Int):Int
}