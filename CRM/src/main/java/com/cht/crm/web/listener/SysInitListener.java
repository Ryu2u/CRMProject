package com.cht.crm.web.listener;

import com.cht.crm.settings.service.DicService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Ryuzu
 * @date 2022/1/21 18:03
 */
public class SysInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("创建了全局作用域对象");
        ServletContext application = servletContextEvent.getServletContext();
        //添加数据字典使用List集合存储,存放在map集合中
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
        DicService ds =  ctx.getBean(DicService.class);
        Map<String, List> dicTypeMap = ds.getDicTypeList();
        Set<String> set = dicTypeMap.keySet();
        System.out.println("正在载入数据字典!");
        for (String code : set) {
            List list = dicTypeMap.get(code);
            application.setAttribute(code, list);
            System.out.println("全局作用域加入对象["+code+"]!");
        }
        System.out.println("载入数据字典结束!");


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
