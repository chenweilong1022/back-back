package com.ozygod.platform.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.ozygod.base.redis.IntegerRedisDao;
import com.ozygod.main.OzygodPmMainApplication;
import com.ozygod.model.zdlog.entity.TblRecordAccountOnlinePlayingEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-13 14:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OzygodPmMainApplication.class)
public class RedisTest {

    @Autowired
    private IntegerRedisDao integerRedisDao;

    @Test
    public void save() {

        List<String> list = integerRedisDao.readAllKeys("");
        System.out.println(list);

//        Boolean conver = integerRedisDao.saveAsJSONStr("conver", 100);
//        System.out.println(conver);

        Integer integer = integerRedisDao.readFromJSONStr("platform:totalBonus");
        System.out.println(integer);

    }

}
