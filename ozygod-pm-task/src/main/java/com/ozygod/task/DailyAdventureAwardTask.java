package com.ozygod.task;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ozygod.base.enums.DailyAdventureAwardConfig;
import com.ozygod.base.enums.GameGoldReason;
import com.ozygod.base.redis.IntegerRedisDao;
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
    private TblAccountService tblAccountService;
    @Autowired
    private TblDailyAdventureAwardGetRecordService tblDailyAdventureAwardGetRecordService;

    private final static String format =  "当前总投注:%s,领取的关卡%s,领取时间%s,领取用户id%s,领取金额%s";

    @Autowired
    private TblActiveConfigService tblActiveConfigService;

    @Value("${agent_url}")
    private String agentUrl;


    /**
     * 每小时统计一次
     */
    @Scheduled(cron = "0 */1 * * * ?")
//    @Transactional(rollbackFor = Exception.class)
    public void configureTasks() {
        //判断是否开启 没开启活动不送奖励
        TblActiveConfigEntity tblActiveConfigEntity = tblActiveConfigService.getById(GameGoldReason.SIX.getKey());
        if (!tblActiveConfigEntity.enabled()) {
            return;
        }


        List<String> keys = integerRedisDao.readAllKeys("totalBet:*");
        log.info(JSONUtil.toJsonStr(keys));

        if (CollUtil.isEmpty(keys)) {
            return;
        }

        List<TblDailyAdventureAwardGetRecordEntity> tblDailyAdventureAwardGetRecordEntities = new ArrayList<>();
        for (String key : keys) {

            String[] strings = key.split(":");
            if (strings.length != 2) {
                continue;
            }
            //当前用户id
            long userId = Long.valueOf(strings[1]);
            //当前投注
            Integer currentBetting = integerRedisDao.readFromJSONStr(key);
            /**
             * 已经领取的关卡
             */
            List<TblDailyAdventureAwardGetRecordEntity> list = tblDailyAdventureAwardGetRecordService.list(new QueryWrapper<TblDailyAdventureAwardGetRecordEntity>().lambda()
                    .eq(TblDailyAdventureAwardGetRecordEntity::getUserid, userId)
            );
            /**
             * 已经领取的关卡
             */
            List<Integer> alreadyKeys = list.stream().map(TblDailyAdventureAwardGetRecordEntity::getCheckpoint).collect(Collectors.toList());
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

                String reason = String.format(format, currentBetting,dailyAdventureAwardConfig.getValue(), DateUtil.formatDateTime(new Date()),userId,tblDailyAdventureAwardGetRecordEntity.getReward());

                SendBonusMailDto sendBonusMailDto = new SendBonusMailDto();
                sendBonusMailDto.setUserid(userId);
                sendBonusMailDto.setBonus(tblDailyAdventureAwardGetRecordEntity.getReward());
                sendBonusMailDto.setMsg(dailyAdventureAwardConfig.getValue());
                sendBonusMailDto.setReason(reason);
                String toJsonStr = JSONUtil.toJsonStr(sendBonusMailDto);
//                发送奖励邮件
                HttpUtil.post(agentUrl + "/sendBonusMail", toJsonStr,5000);

                tblDailyAdventureAwardGetRecordEntity.setNote(toJsonStr);
                tblDailyAdventureAwardGetRecordEntities.add(tblDailyAdventureAwardGetRecordEntity);
            }

            tblDailyAdventureAwardGetRecordService.saveBatch(tblDailyAdventureAwardGetRecordEntities);
        }

    }

}
