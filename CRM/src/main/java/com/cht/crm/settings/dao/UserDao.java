package com.cht.crm.settings.dao;

import com.cht.crm.settings.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author Ryuzu
 * @date 2022/1/15 0:39
 */
public interface UserDao {
    //User loginUser(@Param("loginAct") String loginAct , @Param("loginPwd") String loginPwd);
    User login(Map<String, String> userMap);
}
