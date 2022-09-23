package com.xxc.homework.dao

import com.xxc.dao.mode.Select
import com.xxc.dao.param.Param
import com.xxc.dao.result.Column
import com.xxc.dao.result.ID
import com.xxc.dao.result.Result
import com.xxc.homework.entity.Category

/**
 * @class CategoryDao
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-12 22:33
 */
interface CategoryDao {

    @Select("SELECT c1.id pid,c1.name pname,c2.id,c2.name child from category c1 left join category c2 on c2.parent=c1.id")
    @Result(
            id = ID(property = "id",column = "pid"),
            columns = [(Column(property = "name",column = "pname"))],
            many = [Result(
                    id = ID(property = "id"),
                    columns = [Column(property = "name")],
                    property = "children",
                    javaType = "com.xxc.homework.entity.Category"
            )],javaType = "com.xxc.homework.entity.Category"
    )
    fun selectCategories():List<CategoryDao>

    @Select("SELECT id,name from category where parent is null ORDER By id")
    @Result(id = ID(property = "id"),autowire = true,javaType = "com.xxc.homework.entity.Category")
    fun selectParentCategories():List<Category>

    @Select("SELECT id,name from category where parent=#{pid} ORDER By id")
    @Result(id = ID(property = "id"),autowire = true,javaType = "com.xxc.homework.entity.Category")
    fun selectChildCategories(@Param pid:Int):List<Category>
}