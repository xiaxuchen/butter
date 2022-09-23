package com.xxc.homework.servlet;

import com.xxc.homework.entity.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 */
@WebServlet(name="registerServlet", urlPatterns="/register.do",loadOnStartup=1)
public class RegisterServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String pwd = req.getParameter("pwd");
		User user = new User(username,pwd);
		if(user.register())
		{
			req.setAttribute("msg","注册成功");
			req.getRequestDispatcher("page/user/login.jsp").forward(req, resp);
		}else {
			req.setAttribute("msg","注册失败");
			req.getRequestDispatcher("page/user/register.jsp").forward(req, resp);
		}
	}
}
