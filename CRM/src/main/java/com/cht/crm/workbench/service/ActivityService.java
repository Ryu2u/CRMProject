package com.cht.crm.workbench.service;

import com.cht.crm.settings.domain.User;
import com.cht.crm.vo.PaginationVo;
import com.cht.crm.workbench.domain.Activity;
import com.cht.crm.workbench.domain.ActivityRemark;

import java.util.List;
import java.util.Map;

/**
 * @author Ryuzu
 * @date 2022/1/16 12:10
 */
public interface ActivityService {
    List<User> findUser();

    Integer saveActivity(Activity activity, ActivityRemark remark);

    PaginationVo<Activity> searchPageList(Map<String,Object> map);

    List<ActivityRemark> findRemark(String activityId);

    Activity findActivityById(String  id);

    int removeActivityById(String id);

    int editActivity(Activity activity,String activityId);



}
