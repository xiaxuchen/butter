package com.xxc.homework.entity

import com.xxc.bean.AllOpen
import com.xxc.bean.NoArg
import com.xxc.bean.Property
import com.xxc.better.dao.session.factory.SqlSessionFactory
import com.xxc.homework.dao.ProductDao

/**
 * @class Product
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-10 11:15
 */
@NoArg
@AllOpen
class Product {

    @Property
    var id:Int? = null

    @Property
    var name:String? = null

    @Property
    var c:Category? = null

    @Property
    var price:Double? = null

    @Property
    var count:Int? = null

    @Property
    var des:String? = null

    @Property
    var photo:String? = null

    override fun toString(): String {
        return "Product(id=$id, name=$name, c=$c, price=$price, des=$des)"
    }

    fun add():Boolean{
//        if(attrIsEmpty(listOf("id")))
//            return false
        return dao.insert(this) > 0
    }

    companion object {

        private val dao:ProductDao = SqlSessionFactory.sqlSession.open(ProductDao::class.java)

        fun importList(products:List<Product>):Int{
            var count = 0
            products.forEach {
                count += dao.replace(it)
            }
            return count
        }

        /**
         * 获取product列表
         * @param c_id 类型id
         * @param curPage 当前页
         * @param pages 需要展示的页数
         * @param len 每一页的商品数
         */
        fun getList(c_id:Int,curPage:Int,pages:Int = 7,len:Int = 24):PageBean<Product>{
            val list = dao.selectList(c_id, (curPage-1)*len, len)
            val count = list.size
            val allCount = dao.selectCount(c_id)
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