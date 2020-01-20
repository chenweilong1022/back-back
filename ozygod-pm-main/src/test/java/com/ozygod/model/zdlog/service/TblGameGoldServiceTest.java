package com.ozygod.model.zdlog.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.ozygod.base.enums.GameGoldReason;
import com.ozygod.main.OzygodPmMainApplication;
import com.ozygod.model.zdgame.entity.TblPlayerinfoEntity;
import com.ozygod.model.zdgame.service.TblPlayerinfoService;
import com.ozygod.model.zdlog.entity.TblGameGoldEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-20 16:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OzygodPmMainApplication.class)
public class TblGameGoldServiceTest {


    @Autowired
    private TblGameGoldService tblGameGoldService;
    @Autowired
    private TblPlayerinfoService tblPlayerinfoService;



    @Test
    public void test() {
        firstCashback();
    }



    /**
     * 签到奖励
     */
    public void signIn() {

        ArrayList<Long> longs1 = CollUtil.newArrayList(14L);

        save(longs1);
    }


    /**
     * 首冲返现
     */
    public void firstCashback() {



        ArrayList<Long> longs1 = CollUtil.newArrayList(500L, 1000L, 2000L);


        save(longs1);

    }

    private void save(ArrayList<Long> longs1) {
        List<TblGameGoldEntity> tblGameGoldEntities = new ArrayList<>();
        List<Long> longs = new ArrayList<>();
        String value = GameGoldReason.TWO.getValue();
        for (int i = 0; i < 200; i++) {
            long l = RandomUtil.randomLong(10000, 20000);
            TblPlayerinfoEntity tblPlayerinfoEntity = tblPlayerinfoService.getById(l);
            if (ObjectUtil.isNull(tblPlayerinfoEntity) && longs.contains(l)) {
                continue;
            }

            TblGameGoldEntity tblGameGoldEntity = new TblGameGoldEntity();
            tblGameGoldEntity.setUserid(l);
            tblGameGoldEntity.setReason(value);
            tblGameGoldEntity.setBeginGold(tblPlayerinfoEntity.getGold());
            tblGameGoldEntity.setChangeGold(RandomUtil.randomEle(longs1));
            tblGameGoldEntity.setEndGold(tblGameGoldEntity.getBeginGold() + tblGameGoldEntity.getChangeGold());

            tblGameGoldEntities.add(tblGameGoldEntity);
            longs.add(l);
        }
        tblGameGoldService.saveBatch(tblGameGoldEntities);
    }


}
