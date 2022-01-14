package com.bjpowernode;

import com.bjpowernode.crm.utils.UUIDUtil;
import org.junit.Test;

/**
 * @author Ryuzu
 * @date 2022/1/7 18:44
 */
public class TestApp {

    @Test
    public void testUUID(){
        String uuid = UUIDUtil.getUUID();
        System.out.println(uuid);
    }
}
