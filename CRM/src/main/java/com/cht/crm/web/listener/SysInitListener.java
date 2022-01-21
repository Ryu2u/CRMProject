package com.cht.crm.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Ryuzu
 * @date 2022/1/21 18:03
 */
public class SysInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        System.out.println("创建了全局作用域对象");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
