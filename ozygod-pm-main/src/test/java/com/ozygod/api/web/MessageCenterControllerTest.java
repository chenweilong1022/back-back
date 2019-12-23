package com.ozygod.api.web;

import com.ozygod.base.enums.RedisKeys;
import com.ozygod.base.redis.StringRedisDao;
import com.ozygod.main.OzygodPmMainApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-23 14:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OzygodPmMainApplication.class)
public class MessageCenterControllerTest {

    @Autowired
    private StringRedisDao dao;

    @Test
    public void t1() {
        Long size = dao.queueSize(RedisKeys.MESSAGECENTERSENDERROR.getValue());

//        strings.forEach(System.out::println);
        System.out.println(size);
    }

}
