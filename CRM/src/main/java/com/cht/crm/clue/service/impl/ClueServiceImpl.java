package com.cht.crm.clue.service.impl;

import com.cht.crm.clue.dao.ClueDao;
import com.cht.crm.clue.domain.Clue;
import com.cht.crm.clue.service.ClueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Ryuzu
 * @date 2022/1/21 15:29
 */
@Service
public class ClueServiceImpl implements ClueService {
    @Resource
    private ClueDao clueDao = null;

    @Override
    public int saveClue(Clue clue) {
        int count = clueDao.insertClue(clue);

        return count;
    }

    @Override
    public Clue queryClue(String id) {
        Clue clue = clueDao.selectClue(id);
        return clue;
    }
}
