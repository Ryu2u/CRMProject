package com.cht.crm.workbench.dao;

import com.cht.crm.workbench.domain.Activity;
import com.cht.crm.workbench.domain.ActivityRemark;

import java.util.List;

/**
 * @author Ryuzu
 * @date 2022/1/16 13:58
 */
public interface ActivityRemarkDao {

    int insertRemark(ActivityRemark remark);

    List<ActivityRemark> selectRemark(String activityId);

    //这是activityId,删除多条记录
    int deleteRemark(String activityId);

    Integer countRemarkById(String id);

    Integer updateRemarkById(Activity activity);

    int deleteRemarkById(String id);







}
