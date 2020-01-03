package com.ozygod.task;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ozygod.base.enums.Global;
import com.ozygod.base.enums.WithdrawOrderState;
import com.ozygod.base.redis.StringRedisDao;
import com.ozygod.model.zdgame.entity.TblAccountEntity;
import com.ozygod.model.zdgame.entity.TblOrderEntity;
import com.ozygod.model.zdgame.service.TblAccountService;
import com.ozygod.model.zdgame.service.TblOrderService;
import com.ozygod.model.zdlog.entity.TblRecordDailyRechargeReportEntity;
import com.ozygod.model.zdlog.service.TblRecordDailyRechargeReportService;
import com.ozygod.model.zdmanage.entity.TblWithdrawOrderEntity;
import com.ozygod.model.zdmanage.service.TblWithdrawOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 每日充值报表统计
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-23 11:44
 */
@Configuration
@EnableScheduling
@Slf4j
public class RecordDailyRechargeReportTask {

    @Autowired
    private StringRedisDao stringRedisDao;

    @Autowired
    private TblRecordDailyRechargeReportService tblRecordDailyRechargeReportService;

    @Autowired
    private TblAccountService tblAccountService;

    @Autowired
    private TblOrderService tblOrderService;

    @Autowired
    private TblWithdrawOrderService tblWithdrawOrderService;


    /**
     * 每小时更新一次数据
     */
    @Scheduled(cron = "0 59 0-23 * * ?")
    public void configureTasks() {

        DateTime date = DateUtil.endOfDay(DateUtil.date());
        /**
         * 当日第一秒
         */
        DateTime beginOfDay = DateUtil.beginOfDay(DateUtil.date());
        /**
         * 今日最后一秒
         */
        DateTime endOfDay = DateUtil.parseDateTime(DateUtil.formatDateTime(date));

        DateTime lastTime = DateUtil.offsetDay(date, -1);
        /**
         * 昨日第一秒
         */
        DateTime lastBeginOfDay = DateUtil.beginOfDay(lastTime);
        /**
         * 昨日最后一秒
         */
        DateTime lastEndOfDay = DateUtil.parseDateTime(DateUtil.formatDateTime(lastTime));
        /**
         * 本周
         */
        List<DateTime> dateTimes = DateUtil.rangeToList(DateUtil.offsetWeek(new Date(), -1), DateUtil.offsetDay(new Date(), -1), DateField.DAY_OF_WEEK);



        /**
         * 新用户
         */
        int newUser = tblAccountService.count(new QueryWrapper<TblAccountEntity>().lambda()
                .ge(TblAccountEntity::getCreateTime, beginOfDay)
                .le(TblAccountEntity::getCreateTime, endOfDay)
                .gt(TblAccountEntity::getUserid, Global.REAL_USER_ID)
        );
        /**
         * 总注册数
         */
        int totalRegistered = tblAccountService.count(new QueryWrapper<TblAccountEntity>().lambda()
                .gt(TblAccountEntity::getUserid, Global.REAL_USER_ID)
        );
        /**
         * 今日登录用户
         */
        int loginUser = tblAccountService.loginNumber(beginOfDay,endOfDay,null,null);
        /**
         * 老用户
         */
        int oldUser = tblAccountService.loginNumber(beginOfDay,endOfDay,beginOfDay,null);
        /**
         * 总订单
         */
        int totalOrder = tblOrderService.orderCount(beginOfDay, endOfDay, null);
        /**
         * 有效订单
         */
        int effectiveOrder = tblOrderService.orderCount(beginOfDay, endOfDay, beginOfDay);
        /**
         * 有效订单比例
         */
        String proportion = Global.PROPORTION;
        if (effectiveOrder > 0) {
            double div = NumberUtil.div(effectiveOrder, totalOrder);
            proportion = NumberUtil.decimalFormat(Global.FORMAT_PERCENTAGE, div);
        }
        /**
         * 新用户
         */
        List<TblAccountEntity> newAccountEntitys = tblAccountService.list(new QueryWrapper<TblAccountEntity>().lambda()
                .ge(TblAccountEntity::getCreateTime, beginOfDay)
                .gt(TblAccountEntity::getUserid, Global.REAL_USER_ID)
        );
        List<Long> newUserIds = new ArrayList<>();
        if (CollUtil.isNotEmpty(newAccountEntitys)) {
            newUserIds = newAccountEntitys.stream().map(TblAccountEntity::getUserid).collect(Collectors.toList());
        }
        /**
         * 老用户
         */
        List<TblOrderEntity> oldAccountEntitys = tblOrderService.list(new QueryWrapper<TblOrderEntity>().lambda()
                .notIn(TblOrderEntity::getUserid, newUserIds)
                .ge(ObjectUtil.isNotNull(beginOfDay), TblOrderEntity::getPayTime, beginOfDay)
                .le(ObjectUtil.isNotNull(endOfDay), TblOrderEntity::getPayTime, endOfDay)
        );
        List<Long> oldUserIds = new ArrayList<>();
        if (CollUtil.isNotEmpty(oldAccountEntitys)) {
            oldUserIds = oldAccountEntitys.stream().map(TblOrderEntity::getUserid).distinct().collect(Collectors.toList());
        }
        /**
         * 新用户充值
         */
        int newUserRecharge = tblOrderService.recharge(beginOfDay, endOfDay, newUserIds);
        /**
         * 新用户充值数量
         */
        int newUsersRechargeNumber = newAccountEntitys.size();

        /**
         * 老用户充值
         */
        int oldUsersRecharge = tblOrderService.recharge(beginOfDay, endOfDay, oldUserIds);
        /**
         * 老用户充值数量
         */
        int oldUsersRechargeNumber = oldAccountEntitys.size();
        /**
         * 总用户充值
         */
        int totalRecharge = oldUsersRecharge + newUserRecharge;
        /**
         * 总用户充值数量
         */
        int totalRechargeNumber = newUsersRechargeNumber + oldUsersRechargeNumber;
        /**
         * 昨日新用户
         */
        List<TblAccountEntity> lastDayNewAccountEntitys = tblAccountService.list(new QueryWrapper<TblAccountEntity>().lambda()
                .ge(TblAccountEntity::getCreateTime, lastBeginOfDay)
                .le(TblAccountEntity::getCreateTime, lastEndOfDay)
                .gt(TblAccountEntity::getUserid, Global.REAL_USER_ID)
        );
        List<Long> lastDayNewAccountUserIds = new ArrayList<>();
        int todayUserRetained = 0;
        if (CollUtil.isNotEmpty(lastDayNewAccountEntitys)) {
            lastDayNewAccountUserIds = lastDayNewAccountEntitys.stream().map(TblAccountEntity::getUserid).collect(Collectors.toList());;
            /**
             * 今日留存用户
             */
            todayUserRetained = tblAccountService.loginNumber(beginOfDay, endOfDay, null, lastDayNewAccountUserIds);
        }
        /**
         * 昨日充值新用户
         */
        List<TblOrderEntity> lastDayRechargeUsers = tblOrderService.list(new QueryWrapper<TblOrderEntity>().lambda()
                .in(TblOrderEntity::getUserid, lastDayNewAccountUserIds)
                .ge(ObjectUtil.isNotNull(beginOfDay), TblOrderEntity::getPayTime, lastBeginOfDay)
                .le(ObjectUtil.isNotNull(endOfDay), TblOrderEntity::getPayTime, lastEndOfDay)
        );
        List<Long> lastDayRechargeUserIds = new ArrayList<>();
        int todayUserRechargeRetained = 0;
        if (CollUtil.isNotEmpty(lastDayRechargeUsers)) {
            lastDayRechargeUserIds = lastDayRechargeUsers.stream().map(TblOrderEntity::getUserid).distinct().collect(Collectors.toList());;
            /**
             * 今日留存用户
             */
            todayUserRechargeRetained = tblAccountService.loginNumber(beginOfDay, endOfDay, null, lastDayRechargeUserIds);
        }
        /**
         * 次日留存率
         */
        String morrowRetained = Global.PROPORTION;
        if (lastDayNewAccountEntitys.size() > 0) {
            double div1 = NumberUtil.div(todayUserRetained, lastDayNewAccountEntitys.size());
            morrowRetained = NumberUtil.decimalFormat(Global.FORMAT_PERCENTAGE,div1);
        }
        /**
         * 充值留存率
         */
        String rechargeRetained = Global.PROPORTION;
        if (lastDayRechargeUsers.size() > 0) {
            double div1 = NumberUtil.div(todayUserRechargeRetained, lastDayRechargeUsers.size());
            rechargeRetained = NumberUtil.decimalFormat(Global.FORMAT_PERCENTAGE,div1);
        }
        /**
         * 今日提现
         */
        List<TblWithdrawOrderEntity> tblWithdrawOrderEntitys = tblWithdrawOrderService.haveWithdrawal(beginOfDay, endOfDay,null);
        /**
         * 总提现
         */
        int totalBack = tblWithdrawOrderService.totalBack(tblWithdrawOrderEntitys);
        /**
         * 总体现人数
         */
        int totalBackNumber = tblWithdrawOrderService.totalBackNumber(tblWithdrawOrderEntitys);
        /**
         * 查询往日留存记录
         */
        List<TblRecordDailyRechargeReportEntity> weeksRetainedWeeksRetaineds = tblRecordDailyRechargeReportService.list(new QueryWrapper<TblRecordDailyRechargeReportEntity>().lambda()
                .ge(TblRecordDailyRechargeReportEntity::getCurrentDate, dateTimes.get(0))
                .le(TblRecordDailyRechargeReportEntity::getCurrentDate, dateTimes.get(dateTimes.size() - 1))
        );
        /**
         * 周留存
         */
        String weeksRetained = Global.PROPORTION;
        if (CollUtil.isNotEmpty(weeksRetainedWeeksRetaineds)) {
            int weekNewUserYesterday = weeksRetainedWeeksRetaineds.stream().mapToInt(TblRecordDailyRechargeReportEntity::getNewUserYesterday).sum() + lastDayNewAccountEntitys.size();
            int weekTodayUserRetained =  weeksRetainedWeeksRetaineds.stream().mapToInt(TblRecordDailyRechargeReportEntity::getTodayUserRetained).sum() + todayUserRetained;
            if (weekNewUserYesterday > 0) {
                double div = NumberUtil.div(weekTodayUserRetained, weekNewUserYesterday);
                weeksRetained = NumberUtil.decimalFormat(Global.FORMAT_PERCENTAGE,div);
            }
        }



        /**
         * 填充数据
         */
        TblRecordDailyRechargeReportEntity tblRecordDailyRechargeReportEntity = new TblRecordDailyRechargeReportEntity();
        tblRecordDailyRechargeReportEntity.setCurrentDate(endOfDay);
        tblRecordDailyRechargeReportEntity.setNewUser(newUser);
//        tblRecordDailyRechargeReportEntity.setNewMachine(0);
//        tblRecordDailyRechargeReportEntity.setNewBinding(0);
        tblRecordDailyRechargeReportEntity.setTotalRegistered(totalRegistered);
//        tblRecordDailyRechargeReportEntity.setTotalMachine(0);
        tblRecordDailyRechargeReportEntity.setLoginUser(loginUser);
        tblRecordDailyRechargeReportEntity.setOldUser(oldUser);
//        tblRecordDailyRechargeReportEntity.setLoginMachine(0);
//        tblRecordDailyRechargeReportEntity.setOldLoginMachine(0);
        tblRecordDailyRechargeReportEntity.setTotalOrder(totalOrder);
        tblRecordDailyRechargeReportEntity.setEffectiveOrder(effectiveOrder);
        tblRecordDailyRechargeReportEntity.setProportion(proportion);
        tblRecordDailyRechargeReportEntity.setNewUserRecharge(newUserRecharge);
        tblRecordDailyRechargeReportEntity.setNewUsersRechargeNumber(newUsersRechargeNumber);
        tblRecordDailyRechargeReportEntity.setOldUsersRecharge(oldUsersRecharge);
        tblRecordDailyRechargeReportEntity.setOldUsersRechargeNumber(oldUsersRechargeNumber);
        tblRecordDailyRechargeReportEntity.setTotalRecharge(totalRecharge);
        tblRecordDailyRechargeReportEntity.setTotalRechargeNumber(totalRechargeNumber);
        tblRecordDailyRechargeReportEntity.setTotalBack(totalBack);
        tblRecordDailyRechargeReportEntity.setTotalBackNumber(totalBackNumber);
        tblRecordDailyRechargeReportEntity.setMorrowRetained(morrowRetained);
        tblRecordDailyRechargeReportEntity.setRechargeRetained(rechargeRetained);
        tblRecordDailyRechargeReportEntity.setWeeksRetained(weeksRetained);
        tblRecordDailyRechargeReportEntity.setArpo(new BigDecimal("0"));
        tblRecordDailyRechargeReportEntity.setArpu(new BigDecimal("0"));
        tblRecordDailyRechargeReportEntity.setArppu(new BigDecimal("0"));
        tblRecordDailyRechargeReportEntity.setNewUserYesterday(lastDayNewAccountEntitys.size());
        tblRecordDailyRechargeReportEntity.setTodayUserRetained(todayUserRetained);
        tblRecordDailyRechargeReportEntity.setNewUserYesterdayRecharge(lastDayRechargeUsers.size());
        tblRecordDailyRechargeReportEntity.setTodayUserRechargeRetained(todayUserRechargeRetained);

        TblRecordDailyRechargeReportEntity one = tblRecordDailyRechargeReportService.getOne(new QueryWrapper<TblRecordDailyRechargeReportEntity>().lambda()
                .eq(TblRecordDailyRechargeReportEntity::getCurrentDate, endOfDay)
        );
        if (ObjectUtil.isNotNull(one)) {
            tblRecordDailyRechargeReportEntity.setId(one.getId());
            tblRecordDailyRechargeReportService.updateById(tblRecordDailyRechargeReportEntity);
        }else {
            tblRecordDailyRechargeReportService.save(tblRecordDailyRechargeReportEntity);
        }


    }


}
