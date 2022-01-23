package com.cht.crm.clue.web.controller;

import com.cht.crm.clue.domain.Clue;
import com.cht.crm.clue.service.ClueService;
import com.cht.crm.settings.domain.User;
import com.cht.crm.utils.DateTimeUtil;
import com.cht.crm.utils.UUIDUtil;
import com.cht.crm.workbench.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ryuzu
 * @date 2022/1/21 15:26
 */
@Controller
@RequestMapping("/workbench/clue")
public class ClueController {

    @Resource
    private ActivityService activityService = null;

    @Resource
    private ClueService clueService = null;

    @RequestMapping("/userList.do")
    @ResponseBody
    public List<User> doSave() {

        List<User> list = activityService.findUser();

        return list;

    }

    @RequestMapping("/saveClue.do")
    @ResponseBody
    public Map<String, Boolean> doSaveClue(Clue clue, HttpSession session) {
        Map<String, Boolean> map = new HashMap<>();
        String id = UUIDUtil.getUUID();
        String createTime = DateTimeUtil.getSysTime();
        User user = (User) session.getAttribute("user");
        String createBy = user.getName();
        clue.setId(id);
        clue.setCreateBy(createBy);
        clue.setCreateTime(createTime);
        int count = clueService.saveClue(clue);
        if (count == 1) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;

    }

    @RequestMapping("/clueSearch.do")
    public ModelAndView doClueList(String id){
        ModelAndView mv = new ModelAndView();
        Clue clue = clueService.queryClue(id);
        mv.addObject("clue", clue);
        mv.setViewName("forward:/workbench/clue/detail.jsp");
        return mv;
    }
}
