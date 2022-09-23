package com.xxc.homework.servlet;

import com.xxc.homework.entity.User;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * @author Administrator
 */
@WebServlet(name="loginServlet", urlPatterns="/login.do",loadOnStartup=1)
public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException , IOException {
		String username = req.getParameter("username");
		String pwd = req.getParameter("password");
		User u = new User(username,pwd);
		if(u.login())
		{
			req.getSession().setAttribute("user",u);
			req.getRequestDispatcher("page/index.jsp").forward(req, resp);
		} else
		{
			req.setAttribute("warn","登录失败,账号或密码有误");
			req.getRequestDispatcher("page/user/login.jsp").forward(req, resp);
		}
		
	}
}
