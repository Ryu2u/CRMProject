package com.cht.crm.clue.service;

import com.cht.crm.clue.domain.Clue;

/**
 * @author Ryuzu
 * @date 2022/1/21 15:28
 */
public interface ClueService {

    int saveClue(Clue clue);

    Clue queryClue(String id);


}
