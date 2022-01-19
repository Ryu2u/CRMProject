package com.cht.crm.workbench.service.impl;

import com.cht.crm.settings.domain.User;
import com.cht.crm.vo.PaginationVo;
import com.cht.crm.workbench.dao.ActivityDao;
import com.cht.crm.workbench.dao.ActivityRemarkDao;
import com.cht.crm.workbench.domain.Activity;
import com.cht.crm.workbench.domain.ActivityRemark;
import com.cht.crm.workbench.service.ActivityService;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Ryuzu
 * @date 2022/1/16 12:10
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityDao activityDao = null;

    @Resource
    private ActivityRemarkDao remarkDao = null;



    @Override
    public List<User> findUser() {

        List<User> list = activityDao.selectUser();

        return list;
    }

    @Override
    public Integer saveActivity(Activity activity, ActivityRemark remark) {

        int count = activityDao.insertActivity(activity);
        int count2 = remarkDao.insertRemark(remark);

        return count + count2;
    }


    @Override
    public PaginationVo<Activity> searchPageList(Map<String, Object> map) {
        PaginationVo<Activity> vo = new PaginationVo<>();
        List<Activity> actList = activityDao.selectPageList(map);
        Integer total = activityDao.selectTotal(map);
        vo.setTotal(total);
        vo.setDataList(actList);
        return vo;
    }

    @Override
    public List<ActivityRemark> findRemark(String activityId) {

        List<ActivityRemark> remark = remarkDao.selectRemark(activityId);

        return remark;
    }

    @Override
    public Activity findActivityById(String id) {
        Activity activity = activityDao.selectActivityById(id);

        return activity;
    }

    @Override
    public int removeActivityById(String id) {

        //activity删除一条记录
        Activity activity = activityDao.selectActivity(id);
        int count1 = activityDao.deleteActivityById(id);
        String activityId = activity.getOwner();
        //查询remark需要删除的记录个数
        int deleteCount = remarkDao.countRemarkById(activityId);
        //删除对应的remark记录
        int resultCount = remarkDao.deleteRemark(activityId);

        if (deleteCount == resultCount && count1 == 1) {
            return 1;
        } else {
            return 0;
        }

    }

    @Override
    public int editActivity(Activity activity,String activityId) {
        String owner = activity.getOwner();
        int count = activityDao.updateActivity(activity);
        int count1 = remarkDao.updateRemarkById(activityId, owner);


        return count;
    }
}
