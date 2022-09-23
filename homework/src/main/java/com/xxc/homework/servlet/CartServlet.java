package com.xxc.homework.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class CartServlet
 * @Description
 * @createTime 2019-03-18 11:26
 */
@WebServlet(name = "cartServlet",urlPatterns = "/cart.do")
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String good = req.getParameter("good");
        System.out.println("good"+good);
        if(good == null || good.isEmpty())
        {
            req.getRequestDispatcher("/page/cart/cart_show.jsp").forward(req,resp);
            return;
        }
        List<String> goods = (List<String>) req.getSession().getAttribute("goods");
        if(goods == null)
        {
            goods = new ArrayList<>();
            req.getSession().setAttribute("goods",goods);
        }
        goods.add(good);
        req.getRequestDispatcher("/page/cart/cart_show.jsp").forward(req,resp);
    }
}
