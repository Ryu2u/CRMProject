package com.bjpowernode.crm.settings.service.impl;

import com.bjpowernode.crm.settings.dao.UserDao;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.utils.SqlSessionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Ryuzu
 * @date 2022/1/7 19:06
 */
@Service
public class UserServiceImpl implements UserService {
        @Resource
        private UserDao dao = null;
}
