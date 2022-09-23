package com.xxc.homework.entity

import com.xxc.bean.AllOpen
import com.xxc.bean.NoArg
import com.xxc.better.dao.delegate.User
import com.xxc.better.dao.session.factory.SqlSessionFactory
import com.xxc.homework.dao.CartDao

/**
 * @class Cart
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-16 15:11
 */
@AllOpen
@NoArg
data class Cart(var user:User? = null,var product: List<Product>? = null){

    companion object {
        @JvmStatic
        val DAO = SqlSessionFactory.sqlSession.open(CartDao::class.java)

        fun add(username:String,productId:Int):Boolean
        {
            return DAO.insert(username, productId) > 0
        }

        fun getList(username: String,curPage:Int,pages:Int = 7,len:Int = 24):PageBean<Product>{
            val list = DAO.selectList(username, (curPage-1)*len, len)
            val count = list.size
            val allCount = DAO.selectCount(username)
            var pageStart = curPage-3
            if(pageStart < 0)
                pageStart = 1
            var pageEnd = pageStart+pages
            var tempEnd = (allCount + 1) / len+1
            if(allCount == 0)
                tempEnd = 0
            if( tempEnd < pageEnd)
                pageEnd = tempEnd
            return PageBean(count,curPage,list,tempEnd,len,0,count-1, (pageStart..pageEnd).toList())
        }
        
    }

}