package com.cht.crm.workbench.dao;

import com.cht.crm.workbench.domain.ActivityRemark;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Ryuzu
 * @date 2022/1/16 13:58
 */
public interface ActivityRemarkDao {

    int insertRemark(ActivityRemark remark);

    List<ActivityRemark> selectRemark(String activityId);

    int deleteRemark(String id);

    Integer countRemarkById(String id);

    Integer updateRemarkById(@Param("activityId") String activityId,@Param("owner") String owner);



}
