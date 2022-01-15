package com.cht.crm.settings.service;

import com.cht.crm.settings.domain.User;

/**
 * @author Ryuzu
 * @date 2022/1/15 0:39
 */
public interface UserService {
    User login(String loginAct, String loginPwd);
}
