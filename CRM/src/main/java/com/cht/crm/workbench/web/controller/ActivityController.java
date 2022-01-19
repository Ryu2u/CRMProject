package com.cht.crm.workbench.web.controller;

import com.cht.crm.settings.domain.User;
import com.cht.crm.utils.DateTimeUtil;
import com.cht.crm.utils.UUIDUtil;
import com.cht.crm.vo.PaginationVo;
import com.cht.crm.workbench.dao.ActivityDao;
import com.cht.crm.workbench.dao.ActivityRemarkDao;
import com.cht.crm.workbench.domain.Activity;
import com.cht.crm.workbench.domain.ActivityRemark;
import com.cht.crm.workbench.service.ActivityService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ryuzu
 * @date 2022/1/16 12:10
 */
@Controller
@RequestMapping("/workbench/activity")
public class ActivityController {

    @Resource
    private ActivityService service = null;

    @Resource
    private ActivityDao dao = null;


    @ResponseBody
    @RequestMapping("/userList.do")
    public List<User> doUserList() {

        List<User> userList = service.findUser();

        return userList;

    }


    @ResponseBody
    @RequestMapping("/save.do")
    public Map<String, Boolean> doSave(Activity activity, HttpSession session) {
        Map<String, Boolean> map = new HashMap<>();
        ActivityRemark remark = new ActivityRemark();
        //id 为UUID
        String id = UUIDUtil.getUUID();
        String remarkId = UUIDUtil.getUUID();
        //创建时间,为系统当前时间
        String createTime = DateTimeUtil.getSysTime();
        //创建人 , 为当前系统用户
        String createBy = ((User) session.getAttribute("user")).getName();

        //添加activity
        activity.setId(id);
        activity.setCreateTime(createTime);
        activity.setCreateBy(createBy);

        //添加remark
        remark.setId(remarkId);
        remark.setActivityId(activity.getOwner());
        remark.setCreateTime(createTime);
        remark.setCreateBy(createBy);


        //添加市场活动数据
        int count = service.saveActivity(activity, remark);
        //添加remark数据


        if (count == 2) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }


    @ResponseBody
    @RequestMapping("/pageList.do")
    public PaginationVo<Activity> doPageList(Activity activity, @RequestParam("pageNo") String pageNoStr,
                                             @RequestParam("pageSize") String pageSizeStr) {
        System.out.println("进入到pageList请求中");
        PaginationVo<Activity> data = new PaginationVo<>();
        Map<String, Object> map = new HashMap<>();
        int pageNo = Integer.parseInt(pageNoStr);
        int pageSize = Integer.parseInt(pageSizeStr);
        int num = (pageNo - 1) * pageSize;
        map.put("activity", activity);
        map.put("num", num);
        map.put("pageSize", pageSize);
        data = service.searchPageList(map);

        return data;

    }


    @RequestMapping("/delete.do")
    @ResponseBody
    public Map<String, Boolean> doDelete(@RequestParam("id") String[] ids) {
        Map<String, Boolean> map = new HashMap<>();
        for (String id : ids) {
            //删除activity一条数据
            //同时删除对应的remark中所有对应的数据
            //首先要查出需要删除的条数
            int count = service.removeActivityById(id);
            if (count == 1) {//返回1,说明删除成功
                map.put("success", true);
            } else {//删除失败
                map.put("success", false);
            }

        }
        return map;

    }


    @RequestMapping("/remarkList.do")
    @ResponseBody
    public List<ActivityRemark> doRemarkList(String activityId) {

        List<ActivityRemark> remark = service.findRemark(activityId);


        return remark;

    }

    @ResponseBody
    @RequestMapping("/findActivityById.do")
    public Map<String, Object> doFindActivityById(String id) {
        Map<String, Object> map = new HashMap<>();
        Activity activity = service.findActivityById(id);
        Activity activity1 = dao.selectActivity(id);
        String owner = activity1.getOwner();
        map.put("owner", owner);
        map.put("activity", activity);
        return map;
    }

    @RequestMapping("/updateActivity.do")
    @ResponseBody
    public Map<String,Boolean> doUpdateActivity(Activity activity,String activityId){
        Map<String,Boolean> map = new HashMap<>();
        int count = service.editActivity(activity,activityId);
        if (count == 1) {
            map.put("success", true);
        }else{
        map.put("success", false);
        }
        return map;

    }
}
