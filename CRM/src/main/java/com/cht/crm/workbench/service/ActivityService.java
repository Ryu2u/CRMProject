package com.cht.crm.workbench.service;

import com.cht.crm.settings.domain.User;
import com.cht.crm.workbench.domain.Activity;

import java.util.List;

/**
 * @author Ryuzu
 * @date 2022/1/16 12:10
 */
public interface ActivityService {
    List<User> findUser();

    int saveActivity(Activity activity);


}
