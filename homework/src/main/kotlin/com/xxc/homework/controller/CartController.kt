package com.xxc.homework.controller


import com.xxc.homework.entity.Cart
import com.xxc.homework.entity.User
import com.xxc.mvc.annotations.controller.Controller
import com.xxc.mvc.annotations.mapping.GetMapping
import com.xxc.mvc.annotations.mapping.Mapping
import com.xxc.mvc.annotations.mapping.PostMapping
import com.xxc.mvc.annotations.param.RequestParams
import com.xxc.mvc.annotations.returned.ResponseBody
import com.xxc.mvc.model.ModelAndView
import javax.servlet.http.HttpSession

/**
 * @class CartController
 * @author 夏旭晨
 * @version 1.0.0
 * @Description
 * @createTime 2019-06-16 15:23
 */
@Controller
@Mapping("/cart")
class CartController {

    /**
     * @param productId 产品id
     */
    @PostMapping(url = "/add")
    @ResponseBody
    fun addProduct(session:HttpSession,@RequestParams productId:Int):String
    {
        val username = (session.getAttribute("user") as User).username
        username?.let {
            return Cart.add(username,productId).toString()
        }
        return "false"
    }

    @GetMapping(url = "/show")
    fun showProducts(session: HttpSession,@RequestParams(nullable = true) curPage:Int?):ModelAndView
    {
        var page = curPage
        if(page == null)
            page = 1
        val user = session.getAttribute("user") as User
        val mv = ModelAndView()
        mv.view = "/page/cart/cart_show.jsp"
        val list = Cart.getList(user.username, page)
        mv.addAttribute("products",list)
        println(list)
        return mv
    }
}