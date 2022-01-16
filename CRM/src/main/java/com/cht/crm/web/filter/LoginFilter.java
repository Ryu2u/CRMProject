package com.cht.crm.web.filter;

import com.cht.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Ryuzu
 * @date 2022/1/16 10:45
 */
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("LoginFilter过滤器");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();
        if ("/crm".equals(uri) || uri.lastIndexOf("login.jsp") != -1 || uri.lastIndexOf("login.do") != -1) {
            //放行
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else if (session.getAttribute("user") != null) {
            //说明有用户登录过了,放行
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/login.jsp");
        //request.getRequestDispatcher("/login.jsp").forward(servletRequest, servletResponse);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
