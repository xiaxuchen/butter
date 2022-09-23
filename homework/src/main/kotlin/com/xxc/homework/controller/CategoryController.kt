package com.xxc.homework.controller

import com.xxc.homework.entity.Category
import com.xxc.mvc.annotations.controller.Controller
import com.xxc.mvc.annotations.mapping.GetMapping
import com.xxc.mvc.annotations.mapping.Mapping
import com.xxc.mvc.annotations.param.RequestParams
import com.xxc.mvc.annotations.returned.ResponseBody

/**
 * @class CategoryController
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-12 23:26
 */
@Controller
@Mapping("/category")
class CategoryController {

    @GetMapping("/first")
    @ResponseBody
    fun firstGet():List<Category>{
        val parents = Category.getParents()
        if(parents.isNotEmpty())
            parents[0].getChildrens()
        return parents
    }

    @GetMapping("/children")
    @ResponseBody
    fun getChildren(@RequestParams pid:Int):List<Category>{
        return Category(pid).getChildrens()
    }
}
