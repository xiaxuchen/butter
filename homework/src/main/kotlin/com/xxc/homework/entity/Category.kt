package com.xxc.homework.entity

import com.xxc.bean.AllOpen
import com.xxc.bean.NoArg
import com.xxc.better.dao.session.factory.SqlSessionFactory
import com.xxc.homework.dao.CategoryDao
import com.xxc.homework.exception.entity.InvaidParamException

/**
 * @class Category
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-10 11:33
 */
@NoArg
@AllOpen
class Category(var id:Int? = null) {

    var name:String? = null

    var children: List<Category>? = null

    /**
     * 获取所有的二级分类
     */
    fun getChildrens():List<Category>{
        id?.let {
            this.children = dao.selectChildCategories(it)
            return this.children!!
        }
        throw InvaidParamException("category id can't be null")
    }

    override fun toString(): String {
        return "Category(id=$id, name=$name, children=$children)"
    }

    companion object {

        @JvmStatic
        private val dao:CategoryDao = SqlSessionFactory.sqlSession.open(CategoryDao::class.java)

        /**
         * 获取所有的一级分类
         */
        @JvmStatic
        fun getParents():List<Category>{
            return dao.selectParentCategories()
        }

    }
}