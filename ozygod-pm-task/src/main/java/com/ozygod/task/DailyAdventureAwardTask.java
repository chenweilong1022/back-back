package com.ozygod.task;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ozygod.base.enums.DailyAdventureAwardConfig;
import com.ozygod.base.enums.GameGoldReason;
import com.ozygod.base.redis.IntegerRedisDao;
import com.ozygod.base.redis.ListRedisDao;
import com.ozygod.model.zdconfig.entity.TblActiveConfigEntity;
import com.ozygod.model.zdconfig.service.TblActiveConfigService;
import com.ozygod.model.zdgame.service.TblAccountService;
import com.ozygod.model.zdlog.dto.SendBonusMailDto;
import com.ozygod.model.zdlog.entity.TblDailyAdventureAwardGetRecordEntity;
import com.ozygod.model.zdlog.service.TblDailyAdventureAwardGetRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 每日闯关记录表
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-02 13:56
 */
@Configuration
@EnableScheduling
@Slf4j
public class DailyAdventureAwardTask {

    @Autowired
    private IntegerRedisDao integerRedisDao;
    @Autowired
    private ListRedisDao listRedisDao;
    @Autowired
    private TblAccountService tblAccountService;
    @Autowired
    private TblDailyAdventureAwardGetRecordService tblDailyAdventureAwardGetRecordService;

    private final static String format =  "闯关活动";

    private final static String msg =  "恭喜你，在闯关活动中，通过%s，获得彩金：%s元。";



    @Autowired
    private TblActiveConfigService tblActiveConfigService;

    @Value("${agent_url}")
    private String agentUrl;


    /**
     * 每小时统计一次
     */
    @Scheduled(cron = "0 1-58 0-23 * * ?")
    public void configureTasks() {
        //判断是否开启 没开启活动不送奖励
        TblActiveConfigEntity tblActiveConfigEntity = tblActiveConfigService.getById(GameGoldReason.SIX.getKey());
        if (!tblActiveConfigEntity.enabled()) {
            return;
        }
        /**
         * 当前所有用户
         */
        List<String> keys = integerRedisDao.readAllKeys("totalBet:*");
        log.info(JSONUtil.toJsonStr(keys));
        if (CollUtil.isEmpty(keys)) {
            return;
        }

        ArrayList<TblDailyAdventureAwardGetRecordEntity> tblDailyAdventureAwardGetRecordEntitiesAll = new ArrayList<>();

        for (String key : keys) {
            //当前用户记录
//            ArrayList<TblDailyAdventureAwardGetRecordEntity> tblDailyAdventureAwardGetRecordEntities = new ArrayList<>();

            String[] strings = key.split(":");
            if (strings.length != 2) {
                continue;
            }
            //当前用户id
            long userId = Long.valueOf(strings[1]);
            //当前投注
            Integer currentBetting = integerRedisDao.readFromJSONStr(key);

            /**
             * 当前领取的全部记录
             */

            String readStr = listRedisDao.readStr("totalBetRewardRecord:" + userId);

            ArrayList<TblDailyAdventureAwardGetRecordEntity> redisTblDailyAdventureAwardGetRecordEntitys = new ArrayList<>();

            if (StrUtil.isNotBlank(readStr)) {
                redisTblDailyAdventureAwardGetRecordEntitys = JSONUtil.parseArray(readStr).toList(TblDailyAdventureAwardGetRecordEntity.class);
            }

            redisTblDailyAdventureAwardGetRecordEntitys.forEach(System.out::println);
            /**
             * 已经领取的关卡
             */
            List<Integer> alreadyKeys = redisTblDailyAdventureAwardGetRecordEntitys.stream().map(TblDailyAdventureAwardGetRecordEntity::getCheckpoint).collect(Collectors.toList());
            /**
             * 可以领取的关卡
             */
            List<DailyAdventureAwardConfig> accessibleAward = DailyAdventureAwardConfig.getAccessibleAward(currentBetting, alreadyKeys);
            //如果为空直接跳出循环
            if (CollUtil.isEmpty(accessibleAward)) {
                continue;
            }
            /**
             * 领取奖励
             */
            for (DailyAdventureAwardConfig dailyAdventureAwardConfig : accessibleAward) {

                TblDailyAdventureAwardGetRecordEntity tblDailyAdventureAwardGetRecordEntity = new TblDailyAdventureAwardGetRecordEntity();
                tblDailyAdventureAwardGetRecordEntity.setUserid(userId);
                tblDailyAdventureAwardGetRecordEntity.setCheckpoint(dailyAdventureAwardConfig.getKey());
                tblDailyAdventureAwardGetRecordEntity.setEffectiveBetting(dailyAdventureAwardConfig.getEffectiveBetting().multiply(BigDecimal.valueOf(100)).intValue());
                tblDailyAdventureAwardGetRecordEntity.setReward(dailyAdventureAwardConfig.getReward().multiply(BigDecimal.valueOf(100)).intValue());
                tblDailyAdventureAwardGetRecordEntity.setCurrentBetting(currentBetting);

                SendBonusMailDto sendBonusMailDto = new SendBonusMailDto();
                sendBonusMailDto.setUserid(userId);
                sendBonusMailDto.setBonus(tblDailyAdventureAwardGetRecordEntity.getReward());
                sendBonusMailDto.setMsg(String.format(msg,dailyAdventureAwardConfig.getValue(),dailyAdventureAwardConfig.getReward().toString()));
                sendBonusMailDto.setReason(format);
                String toJsonStr = JSONUtil.toJsonStr(sendBonusMailDto);
//                发送奖励邮件
                HttpUtil.post(agentUrl + "/sendBonusMail", toJsonStr,5000);

                tblDailyAdventureAwardGetRecordEntity.setNote(toJsonStr);
                redisTblDailyAdventureAwardGetRecordEntitys.add(tblDailyAdventureAwardGetRecordEntity);
                tblDailyAdventureAwardGetRecordEntitiesAll.add(tblDailyAdventureAwardGetRecordEntity);
            }

            /**
             * 获取到今日12点的秒数
             */
            long second = (DateUtil.endOfDay(new Date()).getTime() / 1000) - DateUtil.currentSeconds();
            listRedisDao.saveAsJSONStr("totalBetRewardRecord:" + userId,redisTblDailyAdventureAwardGetRecordEntitys,second);
        }
        tblDailyAdventureAwardGetRecordService.saveBatch(tblDailyAdventureAwardGetRecordEntitiesAll);
    }

}
