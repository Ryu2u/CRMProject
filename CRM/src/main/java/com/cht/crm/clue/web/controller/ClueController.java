package com.cht.crm.clue.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ryuzu
 * @date 2022/1/21 15:26
 */
@Controller
@RequestMapping("/workbench/clue")
public class ClueController {

    @RequestMapping("/saveClue.do")
    public Map<String,Boolean> doSave(){
        Map<String ,Boolean> map = new HashMap<>();


        return map;

    }
}
