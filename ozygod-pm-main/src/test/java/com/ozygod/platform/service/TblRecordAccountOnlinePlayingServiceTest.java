package com.ozygod.platform.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.ozygod.main.OzygodPmMainApplication;
import com.ozygod.model.zdlog.entity.TblRecordAccountOnlinePlayingEntity;
import com.ozygod.platform.service.TblRecordAccountOnlinePlayingService;
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
public class TblRecordAccountOnlinePlayingServiceTest {

    @Autowired
    private TblRecordAccountOnlinePlayingService tblRecordAccountOnlinePlayingService;

    @Test
    public void save() {


        DateTime endOfDay = DateUtil.endOfDay(new Date());
        DateTime beginOfDay = DateUtil.beginOfDay(new Date());

        List<Date> list = new ArrayList<>();

        int len = 60 * 24;

        list.add(beginOfDay);

        for (int i = 0; i < len - 1; i++) {
            beginOfDay = DateUtil.offsetMinute(beginOfDay, 1);
            list.add(beginOfDay);
        }

        ArrayList<TblRecordAccountOnlinePlayingEntity> tblRecordAccountOnlinePlayingEntities = new ArrayList<>();


        for (Date date : list) {

            int i = RandomUtil.randomInt(1,500);
            System.out.println(i);
            TblRecordAccountOnlinePlayingEntity tblRecordAccountOnlinePlayingEntity = new TblRecordAccountOnlinePlayingEntity();
//            tblRecordAccountOnlinePlayingEntity.setId(0);
            tblRecordAccountOnlinePlayingEntity.setCurrentMinutes(date);
            tblRecordAccountOnlinePlayingEntity.setOnlineNumber(i);
            tblRecordAccountOnlinePlayingEntity.setPlayingNumber(RandomUtil.randomInt(i));
            tblRecordAccountOnlinePlayingEntity.setCreateTime(new Date());
            tblRecordAccountOnlinePlayingEntity.setIsDel(0);
            tblRecordAccountOnlinePlayingEntities.add(tblRecordAccountOnlinePlayingEntity);
        }

        tblRecordAccountOnlinePlayingService.saveBatch(tblRecordAccountOnlinePlayingEntities);

    }

}