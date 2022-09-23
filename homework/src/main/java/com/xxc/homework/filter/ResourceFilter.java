package com.xxc.homework.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 夏旭晨
 * @version 1.0.0
 * @class ResourceFilter
 * @Description
 * @createTime 2019-03-17 11:49
 */
public class ResourceFilter implements Filter {

    private static final int NO_LOGIN = 0;

    private static final int LOGINED = 1;

    private HashMap<String,Integer> map ;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Enumeration<String> enumeration =  filterConfig.getInitParameterNames();
        map = new HashMap<>(10);
        while (enumeration.hasMoreElements())
        {
            String name = enumeration.nextElement();
            map.put(handleName(name),Integer.parseInt(filterConfig.getInitParameter(name)));
        }
        System.out.println(map);
    }

    private String handleName(String name) {
        System.out.println(name);
        name = name.replaceAll("\\*","\\\\S+");
        System.out.println(name);
        System.out.println(name.contains("\\S"));
        System.out.println("\\S+");
        if(name.startsWith("/"))
        {
            name = "^"+name;
        }
        name = name+"$";
        return name;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        for(Map.Entry<String,Integer> entry:map.entrySet())
        {
            Pattern pattern = Pattern.compile(entry.getKey());
            if(pattern.matcher(uri).matches())
            {
                if(entry.getValue() == LOGINED)
                {
                    if(request.getSession().getAttribute("user") == null )
                    {
                        servletRequest.setAttribute("warn","请先登录");
                        servletRequest.getRequestDispatcher("/user/login.jsp").forward(servletRequest,servletResponse);
                        return;
                    }
                }
            }
        }
        filterChain.doFilter(request,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
