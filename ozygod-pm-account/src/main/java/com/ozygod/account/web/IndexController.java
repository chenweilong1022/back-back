package com.ozygod.account.web;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.*;
import com.ozygod.base.redis.IntegerRedisDao;
import com.ozygod.base.utils.EnumUtil;
import com.ozygod.base.vo.IndexCardVo;
import com.ozygod.model.zdgame.entity.TblAccountEntity;
import com.ozygod.model.zdgame.entity.TblOrderEntity;
import com.ozygod.model.zdgame.service.TblAccountService;
import com.ozygod.model.zdgame.service.TblOrderService;
import com.ozygod.model.zdgame.service.TblPlayerinfoService;
import com.ozygod.model.zdlog.dto.AccountLoginRegisterEverydayStatisticsDto;
import com.ozygod.model.zdlog.dto.PlayerOnlineStatisticsDto;
import com.ozygod.model.zdlog.dto.RechargeAmountStatisticsDto;
import com.ozygod.model.zdlog.dto.TotalGoldEverydayStatisticsDto;
import com.ozygod.model.zdlog.entity.*;
import com.ozygod.model.zdlog.service.*;
import com.ozygod.model.zdlog.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;


/**
 * 首页controller
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-14 21:50:40
 */
@RestController
@RequestMapping("index")
public class IndexController {
    @Autowired
    private TblAccountService tblAccountService;
    @Autowired
    private TblOrderService tblOrderService;
    @Autowired
    private IntegerRedisDao integerRedisDao;
    @Autowired
    private TblRecordConversionEverydayService tblRecordConversionEverydayService;
    @Autowired
    private TblRecordAccountOnlinePlayingService tblRecordAccountOnlinePlayingService;
    @Autowired
    private TblRecordAccountLoginRegisterEverydayService tblRecordAccountLoginRegisterEverydayService;
    @Autowired
    private TblRecordRechargeAmountEverydayService tblRecordRechargeAmountEverydayService;
    @Autowired
    private TblRecordChannelDailyService tblRecordChannelDailyService;
    @Autowired
    private TblPlayerinfoService tblPlayerinfoService;




    /**
     * 首页
     */
    @RequestMapping("/indexCard")
    public ResponseBO indexCard() {

        DateTime beginOfDay = DateUtil.beginOfDay(new Date());
        IndexCard[] values = IndexCard.values();

        int conversion = 0;
        int recharge = 0;

        List<IndexCardVo> indexCardVos = new ArrayList<>();
        for (IndexCard indexCard : values) {
            IndexCardVo indexCardVo = new IndexCardVo();
            Integer key = indexCard.getKey();
            String value = null;

            if (key.equals(IndexCard.ONE.getKey())) {

                /**
                 * 查询今日注册量
                 */
                int count = tblAccountService.count(new QueryWrapper<TblAccountEntity>().lambda()
                        .gt(TblAccountEntity::getCreateTime, beginOfDay)
                );
                value = String.valueOf(count);
            }else if (key.equals(IndexCard.TWO.getKey())) {
                /**
                 * 查询在线数
                 */
                int count = tblAccountService.onlineNumber();
                value = String.valueOf(count);
            }else if (key.equals(IndexCard.THREE.getKey())) {
                /**
                 * 今日登陆数
                 */
                int count = tblAccountService.loginNumber(beginOfDay,null,null,null);

                value = String.valueOf(count);
            }else if (key.equals(IndexCard.FOUR.getKey())) {
                /**
                 * 今日充值数
                 */
                int count = tblOrderService.orderCount(null,null,beginOfDay);

                value = String.valueOf(count);
            }else if (key.equals(IndexCard.FIVE.getKey())) {
                /**
                 * 充值金额
                 */
                int sum = tblOrderService.recharge(beginOfDay, null, null);

                value = NumberUtil.decimalFormatMoney(NumberUtil.div(sum,100));
                /**
                 * 充值金额
                 */
                recharge = sum;
            }else if (key.equals(IndexCard.SIX.getKey())) {
                /**
                 * 充值成功率
                 */
                int totalCount = tblOrderService.count(new QueryWrapper<TblOrderEntity>().lambda()
                        .gt(TblOrderEntity::getCreateTime, beginOfDay)
                );

                int payCount = tblOrderService.count(new QueryWrapper<TblOrderEntity>().lambda()
                        .gt(TblOrderEntity::getPayTime, beginOfDay)
                );
                if (totalCount == 0) {
                    value = Global.PROPORTION;
                }else {
                    value = NumberUtil.decimalFormat(Global.FORMAT_PERCENTAGE, NumberUtil.div(payCount, totalCount));
                }
            }else if (key.equals(IndexCard.SEVEN.getKey())) {
                /**
                 * 昨天
                 */
                String yesterday = DateUtil.formatDateTime(DateUtil.endOfDay(DateUtil.offsetDay(new Date(), -1)));
                /**
                 * 当前兑换数
                 */
                Integer totalCount = integerRedisDao.readFromJSONStr(RedisKeys.ONE.getValue());
                if (ObjectUtil.isNull(totalCount)) {
                    totalCount = 0;
                }
                /**
                 * 昨日兑换记录
                 */
                TblRecordConversionEverydayEntity yesterdayOne = tblRecordConversionEverydayService.getOne(new QueryWrapper<TblRecordConversionEverydayEntity>().lambda()
                        .eq(TblRecordConversionEverydayEntity::getCurrentDate, yesterday)
                );
                value = "0";
                if (ObjectUtil.isNotNull(yesterdayOne)) {
                    value = NumberUtil.decimalFormatMoney(NumberUtil.div(totalCount - yesterdayOne.getTotalConversion(),100));
                }else {
                    yesterdayOne = new TblRecordConversionEverydayEntity();
                    yesterdayOne.setTotalConversion(totalCount);
                }
                /**
                 * 兑换金额
                 */
                conversion = Integer.valueOf(totalCount - yesterdayOne.getTotalConversion());
            }else if (key.equals(IndexCard.EIGHT.getKey())) {
                if (recharge == 0) {
                    value = Global.PROPORTION;
                }else {

                    value = String.valueOf(NumberUtil.decimalFormat(Global.FORMAT_PERCENTAGE, NumberUtil.div(conversion, recharge)));
                }
            }

            indexCardVo.setTitle(indexCard.getValue());
            indexCardVo.setValue(value);
            indexCardVo.setIcon(indexCard.getIcon());
            indexCardVos.add(indexCardVo);
        }
        return ResponseBO.data(indexCardVos);
    }

    /**
     * 玩家在线统计
     */
    @RequestMapping("/playerOnlineStatistics")
    public ResponseBO playerOnlineStatistics(@RequestBody PlayerOnlineStatisticsDto playerOnlineStatisticsDto) {
        System.out.println(playerOnlineStatisticsDto);

        List<DateTime> dates = new ArrayList();
        getDatesByButton(playerOnlineStatisticsDto.getKey(), dates);

        List<PlayerOnlineStatisticsVo> playerOnlineStatisticsVos = new ArrayList<>();


        /**
         * 所有分钟数
         */
        List<DateTime> dateTimes = DateUtil.rangeToList(DateUtil.beginOfDay(DateUtil.date()), DateUtil.offsetMinute(DateUtil.endOfDay(DateUtil.date()), -1), DateField.MINUTE);


        /**
         * 遍历所有需要比对的日期
         */
        for (Date date : dates) {
            /**
             * 需要返回的vo
             */
            PlayerOnlineStatisticsVo playerOnlineStatisticsVo = new PlayerOnlineStatisticsVo();

            /**
             * 查询当前日期所有在线走势
             */
            List<TblRecordAccountOnlinePlayingEntity> list = tblRecordAccountOnlinePlayingService.list(new QueryWrapper<TblRecordAccountOnlinePlayingEntity>().lambda()
                    .gt(TblRecordAccountOnlinePlayingEntity::getCurrentMinutes, DateUtil.beginOfDay(date))
                    .lt(TblRecordAccountOnlinePlayingEntity::getCurrentMinutes, DateUtil.endOfDay(date))
            );
            /**
             * 跟分钟数组比对,
             * 如果list中不存在当前分钟的在线数
             * 将在线数置为0
             */
            List<Integer> integers = new ArrayList<>();
            for (int i = 0,j = 0; i < dateTimes.size();) {
                /**
                 * 当j循环完毕直接结束循环
                 */
                if (j == list.size()) {
                    break;
                }
                DateTime all = dateTimes.get(i);
                TblRecordAccountOnlinePlayingEntity tblRecordAccountOnlinePlayingEntity = list.get(j);

                Date currentMinutes = tblRecordAccountOnlinePlayingEntity.getCurrentMinutes();

                if (DateUtil.format(all,"HH:mm").equals(DateUtil.format(currentMinutes,"HH:mm"))) {
                    integers.add(tblRecordAccountOnlinePlayingEntity.getOnlineNumber());
                    i++;
                    j++;
                }else {
                    integers.add(0);
                    i++;
                }
            }

            playerOnlineStatisticsVo.setList(integers);
            playerOnlineStatisticsVo.setDate(DateUtil.format(date,"yyyyMMdd"));
            playerOnlineStatisticsVos.add(playerOnlineStatisticsVo);
        }
        /**
         * 统一返回
         */
        PlayerOnlineStatisticsAllVo playerOnlineStatisticsAllVo = new PlayerOnlineStatisticsAllVo();
        playerOnlineStatisticsAllVo.setPlayerOnlineStatisticsVos(playerOnlineStatisticsVos);
        playerOnlineStatisticsAllVo.setDateTimes(dateTimes);

        Date startTime = dates.get(0);
        Date endTime = dates.get(dates.size() - 1);
        playerOnlineStatisticsAllVo.setStartTime(startTime);
        playerOnlineStatisticsAllVo.setEndTime(endTime);
        return ResponseBO.data(playerOnlineStatisticsAllVo);
    }

    private void getDatesByButton(Integer key, List<DateTime> dates) {
        if (PlayerOnlineStatisticsButtons.ONE.getKey().equals(key)) {
            List<DateTime> dateTimes = DateUtil.rangeToList(DateUtil.offsetWeek(DateUtil.date(), -1), DateUtil.date(), DateField.DAY_OF_WEEK);
            dates.addAll(dateTimes);
        }else if (PlayerOnlineStatisticsButtons.TWO.getKey().equals(key)) {
            List<DateTime> dateTimes = DateUtil.rangeToList(DateUtil.offsetWeek(DateUtil.date(), -2), DateUtil.date(), DateField.DAY_OF_WEEK);
            dates.addAll(dateTimes);
        }else if (PlayerOnlineStatisticsButtons.THREE.getKey().equals(key)) {
            List<DateTime> dateTimes = DateUtil.rangeToList(DateUtil.offsetMonth(DateUtil.date(), -1), DateUtil.date(), DateField.DAY_OF_MONTH);
            dates.addAll(dateTimes);
        }else {
            /**
             * 今日
             */
            DateTime today = DateUtil.date();
            /**
             * 昨日
             */
            DateTime yesterday = DateUtil.offsetDay(today, -1);
            dates.add(yesterday);
            dates.add(today);
        }
    }

    /**
     * 玩家在线走势统计按钮
     */
    @RequestMapping("/playerOnlineStatisticsButtons")
    public ResponseBO playerOnlineStatisticsButtons() {
        return ResponseBO.data(EnumUtil.enumToVo(PlayerOnlineStatisticsButtons.values()));
    }


    /**
     * 每日登录统计
     */
    @RequestMapping("/accountLoginRegistrEverydayStatistics")
    public ResponseBO accountLoginRegistrEverydayStatistics(@RequestBody AccountLoginRegisterEverydayStatisticsDto accountLoginRegisterEverydayStatisticsDto) {

        List<DateTime> dates = new ArrayList<>();
        getDatesByButton(accountLoginRegisterEverydayStatisticsDto.getKey(),dates);

        AccountLoginRegisterEverydayStatisticsAllVo accountLoginRegisterEverydayStatisticsAllVo = new AccountLoginRegisterEverydayStatisticsAllVo();
        accountLoginRegisterEverydayStatisticsAllVo.setStartTime(DateUtil.parseDateTime(DateUtil.formatDateTime(DateUtil.endOfDay(dates.get(0)))));
        accountLoginRegisterEverydayStatisticsAllVo.setEndTime(DateUtil.parseDateTime(DateUtil.formatDateTime(DateUtil.endOfDay(dates.get(dates.size() - 1)))));
        accountLoginRegisterEverydayStatisticsAllVo.setDateTimes(dates);

        if(accountLoginRegisterEverydayStatisticsDto.getType() == 1) {
            for (AccountLoginWay enumVo : AccountLoginWay.values()) {
                AccountLoginRegisterEverydayStatisticsVo accountLoginEverydayStatisticsVo = getAccountLoginRegisterEverydayStatisticsVo(accountLoginRegisterEverydayStatisticsAllVo, enumVo.getKey());
                accountLoginEverydayStatisticsVo.setAccountLoginWay(enumVo.getLoginWay());
                accountLoginRegisterEverydayStatisticsAllVo.add(accountLoginEverydayStatisticsVo);
            }
        }else if(accountLoginRegisterEverydayStatisticsDto.getType() == 2) {
            for (AccountLoginType enumVo : AccountLoginType.values()) {
                AccountLoginRegisterEverydayStatisticsVo accountLoginEverydayStatisticsVo = getAccountLoginRegisterEverydayStatisticsVo(accountLoginRegisterEverydayStatisticsAllVo, enumVo.getKey());
                accountLoginEverydayStatisticsVo.setAccountLoginWay(enumVo.getLoginType());
                accountLoginRegisterEverydayStatisticsAllVo.add(accountLoginEverydayStatisticsVo);
            }
        }else if(accountLoginRegisterEverydayStatisticsDto.getType() == 3) {
            for (AccountRegisterChannel enumVo : AccountRegisterChannel.values()) {
                AccountLoginRegisterEverydayStatisticsVo accountLoginEverydayStatisticsVo = getAccountLoginRegisterEverydayStatisticsVo(accountLoginRegisterEverydayStatisticsAllVo, enumVo.getKey());
                accountLoginEverydayStatisticsVo.setAccountLoginWay(enumVo.getRegisterChannel());
                accountLoginRegisterEverydayStatisticsAllVo.add(accountLoginEverydayStatisticsVo);
            }
        }else if(accountLoginRegisterEverydayStatisticsDto.getType() == 4) {
            for (AccountRegisterType enumVo : AccountRegisterType.values()) {
                AccountLoginRegisterEverydayStatisticsVo accountLoginEverydayStatisticsVo = getAccountLoginRegisterEverydayStatisticsVo(accountLoginRegisterEverydayStatisticsAllVo, enumVo.getKey());
                accountLoginEverydayStatisticsVo.setAccountLoginWay(enumVo.getRegisterType());
                accountLoginRegisterEverydayStatisticsAllVo.add(accountLoginEverydayStatisticsVo);
            }
        }

        return ResponseBO.data(accountLoginRegisterEverydayStatisticsAllVo);
    }

    private AccountLoginRegisterEverydayStatisticsVo getAccountLoginRegisterEverydayStatisticsVo(AccountLoginRegisterEverydayStatisticsAllVo accountLoginRegisterEverydayStatisticsAllVo, Integer key) {
        AccountLoginRegisterEverydayStatisticsVo accountLoginRegisterEverydayStatisticsVo = new AccountLoginRegisterEverydayStatisticsVo();
        List<TblRecordAccountLoginRegisterEverydayEntity> list = tblRecordAccountLoginRegisterEverydayService.list(new QueryWrapper<TblRecordAccountLoginRegisterEverydayEntity>().lambda()
                .ge(TblRecordAccountLoginRegisterEverydayEntity::getCurrentDates, accountLoginRegisterEverydayStatisticsAllVo.getStartTime())
                .le(TblRecordAccountLoginRegisterEverydayEntity::getCurrentDates, accountLoginRegisterEverydayStatisticsAllVo.getEndTime())
                .eq(TblRecordAccountLoginRegisterEverydayEntity::getType, key)
                .orderByAsc(TblRecordAccountLoginRegisterEverydayEntity::getCurrentDates)
        );
        List<Integer> counts = list.stream().map(TblRecordAccountLoginRegisterEverydayEntity::getCount).collect(Collectors.toList());
        accountLoginRegisterEverydayStatisticsVo.setCounts(counts);
        return accountLoginRegisterEverydayStatisticsVo;
    }

    /**
     * 平台充值金额统计
     */
    @RequestMapping("/rechargeAmountStatistics")
    public ResponseBO rechargeAmountStatistics(@RequestBody RechargeAmountStatisticsDto rechargeAmountStatisticsDto) {


        List<DateTime> dates = new ArrayList<>();
        getDatesByButton(rechargeAmountStatisticsDto.getKey(),dates);

        List<String> xAxis = dates.stream().map(dateTime -> DateUtil.format(dateTime, "yyMMdd")).collect(Collectors.toList());

        Integer totalweixin = 0;
        Integer totalali = 0;
        Integer totalapple = 0;
        Integer totalPrice = 0;

        RechargeAmountStatisticsAllVo rechargeAmountStatisticsAllVo = new RechargeAmountStatisticsAllVo();
        rechargeAmountStatisticsAllVo.setStartTime(DateUtil.parseDateTime(DateUtil.formatDateTime(DateUtil.endOfDay(dates.get(0)))));
        rechargeAmountStatisticsAllVo.setEndTime(DateUtil.parseDateTime(DateUtil.formatDateTime(DateUtil.endOfDay(dates.get(dates.size() - 1)))));
        rechargeAmountStatisticsAllVo.setXAxis(xAxis);

        AccountRegisterChannel[] accountRegisterChannels = AccountRegisterChannel.values();
        AccountLoginType[] accountLoginTypes = AccountLoginType.values();
        AppPayChannel[] appPayChannels = AppPayChannel.values();

        for (AccountRegisterChannel accountRegisterChannel : accountRegisterChannels) {
            //暂时不处理其他渠道
            if (accountRegisterChannel.getKey().equals(AccountRegisterChannel.COTHER.getKey())) {
                continue;
            }
            for (AccountLoginType accountLoginType : accountLoginTypes) {

                RechargeAmountStatisticsVo rechargeAmountStatisticsVo = new RechargeAmountStatisticsVo();

                rechargeAmountStatisticsVo.setRechargeSource(accountLoginType.getValue() + accountRegisterChannel.getValue());
                /**
                 * 查询当前登录渠道当前日期所有的记录
                 */
                List<TblRecordRechargeAmountEverydayEntity> list = tblRecordRechargeAmountEverydayService.list(new QueryWrapper<TblRecordRechargeAmountEverydayEntity>().lambda()
                        .eq(TblRecordRechargeAmountEverydayEntity::getAppChannel, accountRegisterChannel.getValue())
                        .eq(TblRecordRechargeAmountEverydayEntity::getPlatform, accountLoginType.getValue())
                        .orderByAsc(TblRecordRechargeAmountEverydayEntity::getCurrentDates)
                        .eq(StrUtil.isNotBlank(rechargeAmountStatisticsDto.getPayChannel()), TblRecordRechargeAmountEverydayEntity::getPayChannel, rechargeAmountStatisticsDto.getPayChannel())
                        .ge(TblRecordRechargeAmountEverydayEntity::getCurrentDates, rechargeAmountStatisticsAllVo.getStartTime())
                        .le(TblRecordRechargeAmountEverydayEntity::getCurrentDates, rechargeAmountStatisticsAllVo.getEndTime())
                );

                int weixin = list.stream().filter(tblRecordRechargeAmountEverydayEntity -> tblRecordRechargeAmountEverydayEntity.getPayChannel().equals(AppPayChannel.WEIXIN.getValue())).mapToInt(TblRecordRechargeAmountEverydayEntity::getMoney).sum();
                int alipay = list.stream().filter(tblRecordRechargeAmountEverydayEntity -> tblRecordRechargeAmountEverydayEntity.getPayChannel().equals(AppPayChannel.ALIPAY.getValue())).mapToInt(TblRecordRechargeAmountEverydayEntity::getMoney).sum();
                int apple = list.stream().filter(tblRecordRechargeAmountEverydayEntity -> tblRecordRechargeAmountEverydayEntity.getPayChannel().equals(AppPayChannel.APPLE.getValue())).mapToInt(TblRecordRechargeAmountEverydayEntity::getMoney).sum();
                totalweixin = totalweixin + weixin;
                totalali = totalali + alipay;
                totalapple = totalapple + apple;

                /**
                 * 根据日期分组然后将日期金额累加
                 */
                TreeMap<Date, List<TblRecordRechargeAmountEverydayEntity>> listTreeMap = list.stream().collect(Collectors.groupingBy(TblRecordRechargeAmountEverydayEntity::getCurrentDates, TreeMap::new, Collectors.toList()));
                List<Double> moneys = new ArrayList<>();
                listTreeMap.forEach((k,v)-> {
                    int sum = v.stream().mapToInt(TblRecordRechargeAmountEverydayEntity::getMoney).sum();
                    moneys.add(NumberUtil.div(sum,100));
                });
                rechargeAmountStatisticsVo.setMoneys(moneys);
                rechargeAmountStatisticsAllVo.add(rechargeAmountStatisticsVo);
            }

        }

        totalPrice = totalali + totalapple + totalweixin;
        rechargeAmountStatisticsAllVo.setTotalali(totalali);
        rechargeAmountStatisticsAllVo.setTotalweixin(totalweixin);
        rechargeAmountStatisticsAllVo.setTotalapple(totalapple);
        rechargeAmountStatisticsAllVo.setTotalPrice(totalPrice);

        return ResponseBO.data(rechargeAmountStatisticsAllVo);
    }


    /**
     * 支付方式
     */
    @RequestMapping("/appPayChannel")
    public ResponseBO appPayChannel() {
        return ResponseBO.data(EnumUtil.enumToVo(AppPayChannel.values()));
    }

    /**
     * 平台-总金币走势
     */
    @RequestMapping("/totalGoldEverydayStatistics")
    public ResponseBO totalGoldEverydayStatistics(@RequestBody TotalGoldEverydayStatisticsDto totalGoldEverydayStatisticsDto) {

        List<DateTime> dates = new ArrayList<>();
        getDatesByButton(totalGoldEverydayStatisticsDto.getKey(),dates);

        List<String> xAxis = dates.stream().map(dateTime -> DateUtil.format(dateTime, "yyMMdd")).collect(Collectors.toList());

        TotalGoldEverydayStatisticsAllVo totalGoldEverydayStatisticsAllVo = new TotalGoldEverydayStatisticsAllVo();
       totalGoldEverydayStatisticsAllVo.setStartTime(DateUtil.parseDateTime(DateUtil.formatDateTime(DateUtil.endOfDay(dates.get(0)))));
       totalGoldEverydayStatisticsAllVo.setEndTime(DateUtil.parseDateTime(DateUtil.formatDateTime(DateUtil.endOfDay(dates.get(dates.size() - 1)))));
       totalGoldEverydayStatisticsAllVo.setXAxis(xAxis);

        List<TblRecordChannelDailyEntity> newList = tblRecordChannelDailyService.list(new QueryWrapper<TblRecordChannelDailyEntity>().lambda()
                .eq(TblRecordChannelDailyEntity::getCurrentDates,totalGoldEverydayStatisticsAllVo.getEndTime())
        );
        if (CollUtil.isNotEmpty(newList)) {
            long gold = newList.stream().mapToLong(TblRecordChannelDailyEntity::getGold).sum();
            long bankgold = newList.stream().mapToLong(TblRecordChannelDailyEntity::getBankGold).sum();
            long totalgold = newList.stream().mapToLong(TblRecordChannelDailyEntity::getTotalGold).sum();
            totalGoldEverydayStatisticsAllVo.setGold(gold);
            totalGoldEverydayStatisticsAllVo.setBankGold(bankgold);
            totalGoldEverydayStatisticsAllVo.setTotalGold(totalgold);
        }

        AccountRegisterChannel[] accountRegisterChannels = AccountRegisterChannel.values();
        AccountLoginType[] accountLoginTypes = AccountLoginType.values();

        List<TotalGoldEverydayStatisticsVo> totalGoldEverydayStatisticsVos = new ArrayList<>();
        for (AccountRegisterChannel accountRegisterChannel : accountRegisterChannels) {
            //暂时不处理其他渠道
            if(accountRegisterChannel.getKey().equals(AccountRegisterChannel.COTHER.getKey())) {
                continue;
            }
            for (AccountLoginType accountLoginType : accountLoginTypes) {


                /**
                 * 根据渠道和登录端查询当前区间段的总金币统计记录
                 * 排序方式根据日期
                 */
                TotalGoldEverydayStatisticsVo totalGoldEverydayStatisticsVo = new TotalGoldEverydayStatisticsVo();
                List<TblRecordChannelDailyEntity> tblRecordChannelDailyEntities  = tblRecordChannelDailyService.list(new QueryWrapper<TblRecordChannelDailyEntity>().lambda()
                        .eq(TblRecordChannelDailyEntity::getAppChannel, accountRegisterChannel.getValue())
                        .eq(TblRecordChannelDailyEntity::getPlatform, accountLoginType.getValue())
                        .ge(TblRecordChannelDailyEntity::getCurrentDates, totalGoldEverydayStatisticsAllVo.getStartTime())
                        .le(TblRecordChannelDailyEntity::getCurrentDates, totalGoldEverydayStatisticsAllVo.getEndTime())
                        .orderByAsc(TblRecordChannelDailyEntity::getCurrentDates)
                );
                /**
                 * 拿到所有的总金币list
                 */
                List<Long> totalGolds = tblRecordChannelDailyEntities.stream().map(TblRecordChannelDailyEntity::getTotalGold).collect(Collectors.toList());
                /**
                 * 设置
                 */
                totalGoldEverydayStatisticsVo.setGoldSource(accountLoginType.getValue()+accountRegisterChannel.getValue());
                totalGoldEverydayStatisticsVo.setGolds(totalGolds);
                totalGoldEverydayStatisticsAllVo.add(totalGoldEverydayStatisticsVo);
            }
        }
        return ResponseBO.data(totalGoldEverydayStatisticsAllVo);
    }


}
