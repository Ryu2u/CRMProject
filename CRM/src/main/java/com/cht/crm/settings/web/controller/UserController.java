package com.cht.crm.settings.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ryuzu
 * @date 2022/1/15 0:54
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/test.do")
    public ModelAndView test(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();

        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);
        return mv;
    }

}
