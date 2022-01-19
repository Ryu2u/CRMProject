package com.cht.crm.settings.test;

import com.cht.crm.utils.DateTimeUtil;
import com.cht.crm.utils.MD5Util;
import com.cht.crm.utils.UUIDUtil;
import com.cht.crm.vo.PaginationVo;
import com.cht.crm.workbench.dao.ActivityDao;
import com.cht.crm.workbench.domain.Activity;
import com.cht.crm.workbench.service.ActivityService;
import com.cht.crm.workbench.service.impl.ActivityServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void testCookie() throws JsonProcessingException {
        String cookie ="{cookie: __gads=ID=5c86260045ff1e70-22fc25c680cf0099:T=1640009629:RT=1640009629:S=ALNI_MZ62Kl" +
                "-Mg7hBhkt26t9SIqgLKgiHA; _ga=GA1.2.1127609402.1641274718; PHPSESSID=9982s5dab0t5lb6a9u0gd511mv; " +
                "jieqiUserInfo=jieqiUserId%3D335499%2CjieqiUserUname%3Dryuzu_%2CjieqiUserName%3Dryuzu_%2CjieqiUserGroup%3D3%2CjieqiUserGroupName%3D%E6%99%AE%E9%80%9A%E4%BC%9A%E5%91%98%2CjieqiUserVip%3D0%2CjieqiUserHonorId%3D1%2CjieqiUserHonor%3D%E5%A4%A9%E7%84%B6%2CjieqiUserToken%3De0195da489ed9a1c9dc5d8d0ee6ee690%2CjieqiCodeLogin%3D0%2CjieqiCodePost%3D0%2CjieqiUserPassword%3D22708f7515c68cbc3e453dddd1968d54%2CjieqiUserLogin%3D1642260152; jieqiVisitInfo=jieqiUserLogin%3D1642260152%2CjieqiUserId%3D335499; Hm_lvt_d29ecd95ff28d58324c09b9dc0bee919=1641197387,1641274718,1641275095,1642260123; Hm_lpvt_d29ecd95ff28d58324c09b9dc0bee919=1642260123}";
        ObjectMapper om = new ObjectMapper();
        String useragent = "user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36";
        String json = om.writeValueAsString(cookie);
        System.out.println(json);

    }

    @Test
    public void testSql() throws IOException {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ActivityService service = (ActivityService) applicationContext.getBean("activityServiceImpl");

        Map<String,Object> map = new HashMap<>();
        Activity activity = new Activity();
        activity.setName("发传单");
        map.put("activity", activity);
        map.put("num", 0);
        map.put("pageSize", 10);
        service.searchPageList(map);

    }

    @Test
    public void testUUId(){
        String u1 = UUIDUtil.getUUID();
        String u2 = UUIDUtil.getUUID();
        System.out.println(u1);
        System.out.println(u2);

    }

    @Test
    public void testId(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ActivityService service = (ActivityService) applicationContext.getBean("activityServiceImpl");
        Activity activityById = service.findActivityById("98229ee305b4410e84c76c361e80fa7c");
        System.out.println(activityById);


    }

}
