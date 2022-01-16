package com.cht.crm.workbench.dao;

import com.cht.crm.settings.domain.User;
import com.cht.crm.workbench.domain.Activity;

import java.util.List;

/**
 * @author Ryuzu
 * @date 2022/1/16 12:08
 */
public interface ActivityDao {

    List<User> selectUser();

    int insertActivity(Activity activity);
}
