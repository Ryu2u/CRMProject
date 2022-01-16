package com.cht.crm.workbench.service.impl;

import com.cht.crm.settings.domain.User;
import com.cht.crm.workbench.dao.ActivityDao;
import com.cht.crm.workbench.domain.Activity;
import com.cht.crm.workbench.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ryuzu
 * @date 2022/1/16 12:10
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityDao dao = null;

    @Override
    public List<User> findUser() {

        List<User> list = dao.selectUser();

        return list;
    }

    @Override
    public int saveActivity(Activity activity) {

        int count = dao.insertActivity(activity);

        return count;
    }
}
