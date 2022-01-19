package com.cht.crm.workbench.dao;

import com.cht.crm.settings.domain.User;
import com.cht.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

/**
 * @author Ryuzu
 * @date 2022/1/16 12:08
 */
public interface ActivityDao {

    List<User> selectUser();

    int insertActivity(Activity activity);

    List<Activity> selectPageList(Map<String ,Object> map);

    Integer selectTotal(Map<String ,Object> map);

    Activity selectActivityById(String  id);

    int deleteActivityById(String id);

    Activity selectActivity(String id);

    Integer updateActivity(Activity activity);


}
