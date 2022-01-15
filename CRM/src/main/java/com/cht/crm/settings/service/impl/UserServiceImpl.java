package com.cht.crm.settings.service.impl;

import com.cht.crm.settings.dao.UserDao;
import com.cht.crm.settings.domain.User;
import com.cht.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User login(String loginAct, String loginPwd) {
        Map<String,String> map = new HashMap<>();
        map.put(loginAct, loginPwd);
        User login = dao.login(map);
        return login;
    }
}
