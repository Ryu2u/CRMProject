package com.cht.crm.settings.dao;

import com.cht.crm.settings.domain.DicValue;

import java.util.List;

/**
 * @author Ryuzu
 * @date 2022/1/21 15:35
 */
public interface DicValueDao {
    List<DicValue> selectValueList(String code);

}
