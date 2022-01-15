package com.cht.crm.settings.web.controller;

import com.cht.crm.exception.LoginException;
import com.cht.crm.settings.domain.User;
import com.cht.crm.settings.service.UserService;
import com.cht.crm.utils.DateTimeUtil;
import com.cht.crm.utils.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ryuzu
 * @date 2022/1/15 0:54
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService service = null;

    @ResponseBody
    @RequestMapping("/login.do")
    public Map<String, Object> login(String loginAct, String loginPwd, HttpServletRequest request) {
        System.out.println("进入到后台验证登录界面");
        Map<String, Object> map = new HashMap<>();
        //将密码的明文形式转换为MD5的密文形式
        loginPwd = MD5Util.getMD5(loginPwd);
        String ip = request.getRemoteAddr();
        System.out.println(ip);//192.168.1.4
        try {
        User login = service.login(loginAct, loginPwd,ip);
            HttpSession session = request.getSession();
            session.setAttribute("user", login);
            //登录成功
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            String msg = e.getMessage();
            map.put("success", false);
            map.put("msg", msg);
        }
        return map;
    }

}
