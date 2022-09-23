package com.xxc.homework.controller

import com.xxc.homework.entity.Category
import com.xxc.homework.entity.Product
import com.xxc.mvc.annotations.controller.Controller
import com.xxc.mvc.annotations.mapping.GetMapping
import com.xxc.mvc.annotations.mapping.Mapping
import com.xxc.mvc.annotations.mapping.PostMapping
import com.xxc.mvc.annotations.param.RequestParams
import com.xxc.mvc.annotations.returned.ResponseBody
import com.xxc.mvc.file.MultiFile
import com.xxc.mvc.model.ModelAndView

/**
 * @class ProductController
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-10 10:36
 */
@Controller
@Mapping("/product")
class ProductController {

    private fun parseProducts(data:List<List<String>>):List<Product>
    {
        val list = ArrayList<Product>()
        data.forEach {
            val product = Product()
                product.id = it[0].toIntOrNull()
                product.name = it[1]
                product.des = it[2]
                product.c = Category(it[3].toIntOrNull())
                product.price = it[4].toDoubleOrNull()
            list.add(product)
        }
        return list
    }

    @PostMapping("/add")
    @ResponseBody
    fun addProduct(@RequestParams category: Int,@RequestParams name:String, @RequestParams des:String, @RequestParams price:Double, @RequestParams count:Int, @RequestParams(isFile = true)photo:MultiFile):String{
        val (isSuccess, path) = photo.hashScatterWrite("/product")
        return if(isSuccess)
        {
            val product = Product()
            product.name = name
            product.des = des
            product.c = Category(category)
            product.photo = path
            product.price = price
            product.count = count
            product.add().toString()
        }else{
            "false"
        }
    }

    @GetMapping("/show")
    fun productShow(@RequestParams(nullable = true) pid:Int? = null,
                    @RequestParams(nullable = true) cid:Int? = null,@RequestParams(nullable = true) curPage:Int?):ModelAndView{
        val categories = Category.getParents()
        var nPid = pid
        var nCid = cid
        var currentPage = curPage
        if(currentPage == null)
            currentPage = 1
        val modelAndView = ModelAndView()
        modelAndView.addAttribute("categories",categories)
        if(categories.isNotEmpty())
        {
            if(nPid == null)
                nPid = categories[0].id
            println(nPid)
            if(nPid != null)
                modelAndView.addAttribute("checkedPid",nPid)
                for (category in categories) {
                    if(category.id == nPid)
                    {
                        category.getChildrens()
                        if(nCid == null && category.children?.isNotEmpty() == true)
                        {
                            nCid = category.children!![0].id!!
                        }
                        else if(category.children?.isNotEmpty() == true)
                            modelAndView.addAttribute("products",Product.getList(category.children!![0].id!!,currentPage))
                        modelAndView.addAttribute("products",Product.getList(nCid!!,currentPage))
                        modelAndView.addAttribute("checkedCid",nCid)
                        break
                    }
                }
        }
        modelAndView.view = "/page/product/product_category.jsp"
        return modelAndView
    }

}
