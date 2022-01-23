package com.cht.crm.clue.dao;


import com.cht.crm.clue.domain.Clue;

public interface ClueDao {

    int insertClue(Clue clue);

    Clue selectClue(String id);
	

}
