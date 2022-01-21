package com.cht.crm.settings.dao;

import com.cht.crm.settings.domain.DicType;

import java.util.List;

/**
 * @author Ryuzu
 * @date 2022/1/21 15:34
 */
public interface DicTypeDao {

    List<DicType> selectTypeList();

}
