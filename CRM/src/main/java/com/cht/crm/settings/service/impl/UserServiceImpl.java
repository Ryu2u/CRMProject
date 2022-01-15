package com.cht.crm.settings.service.impl;

import com.cht.crm.exception.LoginException;
import com.cht.crm.settings.dao.UserDao;
import com.cht.crm.settings.domain.User;
import com.cht.crm.settings.service.UserService;
import com.cht.crm.utils.DateTimeUtil;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ryuzu
 * @date 2022/1/15 0:39
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao dao = null;

    @Override
    public User login(String loginAct, String loginPwd,String ip) throws LoginException {
        Map<String,String> map = new HashMap<>();
        map.put("loginAct", loginAct);
        map.put("loginPwd", loginPwd);
        User login = dao.login(map);
        if (login == null) {
            //密码不正确
            throw new LoginException("账号密码不存在");
        }
        String expireTime = login.getExpireTime();
        String nowTime = DateTimeUtil.getSysTime();
        if (nowTime.compareTo(expireTime) > 0){
            //失效时间已过
            throw new LoginException("失效时间已过");
        }
        String lockState = login.getLockState();
        if ("0".equals(lockState)) {
            //账号被锁定
            throw new LoginException("账号被锁定");
        }
        String allowIps = login.getAllowIps();
        if (!allowIps.contains(ip)) {
            //IP地址不正确
            throw new LoginException("IP地址错误");
        }
        return login;
    }
}
