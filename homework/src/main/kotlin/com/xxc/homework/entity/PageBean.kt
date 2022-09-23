package com.xxc.homework.entity

import com.xxc.bean.AllOpen
import com.xxc.bean.NoArg

/**
 * @class PageBean
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-13 17:10
 */
@AllOpen
@NoArg
data class PageBean<T> (var count:Int,//记录总数
                        var currentPage:Int,//当前页面
                        var objects:List<T>,//数据
                        var pages:Int,//所有页数
                        var pageSize:Int,//每页记录数
                        var startIndex:Int,//当前页开始记录数
                        var endIndex:Int,//当前页结束记录数
                        var pageNumbers:List<Int>//分页列表
)