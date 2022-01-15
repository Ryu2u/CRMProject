package com.cht.crm.settings.test;

import com.cht.crm.utils.DateTimeUtil;
import com.cht.crm.utils.MD5Util;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ryuzu
 * @date 2022/1/15 13:46
 */
public class MyApp {

    @Test
    public void testTime() throws ParseException {
        String expireTime = "2020-10-10 10:10:10";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        Date parse1 = sdf.parse(expireTime);
        Date parse2 = sdf.parse(time);
        int i = parse2.compareTo(parse1);
        System.out.println(i);
    }

    @Test
    public void testTime2() throws InterruptedException {
        //DateTimeUtil util = new DateTimeUtil();
        String time = DateTimeUtil.getSysTime();
        Thread.sleep(2000);
        String time1 = DateTimeUtil.getSysTime();
        System.out.println(time);
        System.out.println(time1);
        int i = time.compareTo(time1);
        System.out.println(i);
    }

    @Test
    public void testLockState(){

        String lockState = "0";
        if ("0".equals(lockState)) {
            //锁定
            System.out.println("lock");
        }//没锁定
        System.out.println("unlock");
    }
    @Test
    public void testIp(){
        String ip = "192.168.1.1";
        String ips = "192.168.1.1,192.172.2.2";
        if (ips.indexOf(ip) != -1) {
            System.out.println("ddd");
        }
        if (ips.contains(ip)) {
            System.out.println("ddd");
        }

    }

    @Test
    public void testMD5(){
        String pwd = "CHENhaitao1015";
        String md5Pwd = MD5Util.getMD5(pwd);
        System.out.println(md5Pwd);

    }

}
