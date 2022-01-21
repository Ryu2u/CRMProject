package com.cht.crm.settings.service.impl;

import com.cht.crm.settings.dao.DicTypeDao;
import com.cht.crm.settings.dao.DicValueDao;
import com.cht.crm.settings.domain.DicType;
import com.cht.crm.settings.domain.DicValue;
import com.cht.crm.settings.service.DicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ryuzu
 * @date 2022/1/21 15:36
 */
@Service
public class DicServiceImpl implements DicService {
    @Resource
    private DicTypeDao typeDao = null;
    @Resource
    private DicValueDao valueDao = null;

    @Override
    public Map<String,List> getDicTypeList() {
        Map<String,List> map = new HashMap<>();

        List<DicType> typeDaoList = typeDao.selectTypeList();
        for (DicType dicType : typeDaoList) {
            String code = dicType.getCode();
            List<DicValue> dicValueList = valueDao.selectValueList(code);
            map.put(code, dicValueList);
        }
        return map;
    }
}
