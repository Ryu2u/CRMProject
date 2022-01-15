package com.cht.crm.settings.service.impl;

import com.cht.crm.settings.dao.UserDao;
import com.cht.crm.settings.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Ryuzu
 * @date 2022/1/15 0:39
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao dao = null;

}
