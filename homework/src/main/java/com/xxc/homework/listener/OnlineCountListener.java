package com.xxc.homework.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class OnlineCountListener
 * @Description
 * @createTime 2019-04-29 11:07
 */
@WebListener
public class OnlineCountListener implements HttpSessionListener {

    private static Integer count = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("hhah");
        if(se.getSession().getServletContext().getAttribute("onlineCount") == null)
            se.getSession().getServletContext().setAttribute("count",count);
        count++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        count--;
    }
}
