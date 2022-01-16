package com.cht.crm.workbench.web.controller;

import com.cht.crm.settings.domain.User;
import com.cht.crm.utils.DateTimeUtil;
import com.cht.crm.utils.UUIDUtil;
import com.cht.crm.workbench.domain.Activity;
import com.cht.crm.workbench.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
        //id 为UUID
        String id = UUIDUtil.getUUID();
        //创建时间,为系统当前时间
        String createTime = DateTimeUtil.getSysTime();
        //创建人 , 为当前系统用户
        String createBy = ((User)session.getAttribute("user")).getName();

        activity.setId(id);
        activity.setCreateTime(createTime);
        activity.setCreateBy(createBy);

        int count = service.saveActivity(activity);
        if (count == 1) {
            map.put("success", true);
        }else{
            map.put("success", false);
        }
        return map;
    }


}
