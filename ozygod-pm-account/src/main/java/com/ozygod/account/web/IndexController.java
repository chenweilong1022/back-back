package com.ozygod.account.web;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.IndexCard;
import com.ozygod.base.enums.PlayerOnlineStatisticsButtons;
import com.ozygod.base.enums.RedisKeys;
import com.ozygod.base.redis.IntegerRedisDao;
import com.ozygod.base.utils.EnumUtil;
import com.ozygod.base.vo.IndexCardVo;
import com.ozygod.model.zdgame.entity.TblAccountEntity;
import com.ozygod.model.zdgame.entity.TblOrderEntity;
import com.ozygod.model.zdgame.service.TblAccountService;
import com.ozygod.model.zdgame.service.TblOrderService;
import com.ozygod.model.zdlog.dto.PlayerOnlineStatisticsDto;
import com.ozygod.model.zdlog.entity.TblRecordAccountOnlinePlayingEntity;
import com.ozygod.model.zdlog.entity.TblRecordConversionEverydayEntity;
import com.ozygod.model.zdlog.service.TblRecordAccountOnlinePlayingService;
import com.ozygod.model.zdlog.service.TblRecordConversionEverydayService;
import com.ozygod.model.zdlog.vo.PlayerOnlineStatisticsAllVo;
import com.ozygod.model.zdlog.vo.PlayerOnlineStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
                int count = tblAccountService.count(new QueryWrapper<TblAccountEntity>().lambda()
                        .gt(TblAccountEntity::getLoginTime, beginOfDay)
                );
                value = String.valueOf(count);
            }else if (key.equals(IndexCard.FOUR.getKey())) {
                /**
                 * 今日充值数
                 */
                int count = tblOrderService.count(new QueryWrapper<TblOrderEntity>().lambda()
                        .gt(TblOrderEntity::getPayTime, beginOfDay)
                        .groupBy(TblOrderEntity::getUserid)
                );
                value = String.valueOf(count);
            }else if (key.equals(IndexCard.FIVE.getKey())) {
                /**
                 * 充值金额
                 */
                List<TblOrderEntity> list = tblOrderService.list(new QueryWrapper<TblOrderEntity>().lambda()
                        .gt(TblOrderEntity::getPayTime, beginOfDay)
                );
                int sum = list.stream().mapToInt(TblOrderEntity::getMoney).sum();
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
                    value = "0.00%";
                }else {
                    value = NumberUtil.decimalFormat("#.##%", NumberUtil.div(payCount, totalCount));
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
                }
                /**
                 * 兑换金额
                 */
                conversion = Integer.valueOf(totalCount - yesterdayOne.getTotalConversion());
            }else if (key.equals(IndexCard.EIGHT.getKey())) {
                if (recharge == 0) {
                    value = "0.00%";
                }else {

                    value = String.valueOf(NumberUtil.decimalFormat("#.##%", NumberUtil.div(conversion, recharge)));
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

        List<Date> dates = new ArrayList();
        if (PlayerOnlineStatisticsButtons.ONE.getKey().equals(playerOnlineStatisticsDto.getKey())) {
            List<DateTime> dateTimes = DateUtil.rangeToList(DateUtil.offsetWeek(DateUtil.date(), -1), DateUtil.date(), DateField.DAY_OF_WEEK);
            dates.addAll(dateTimes);
        }else if (PlayerOnlineStatisticsButtons.TWO.getKey().equals(playerOnlineStatisticsDto.getKey())) {
            List<DateTime> dateTimes = DateUtil.rangeToList(DateUtil.offsetWeek(DateUtil.date(), -2), DateUtil.date(), DateField.DAY_OF_WEEK);
            dates.addAll(dateTimes);
        }else if (PlayerOnlineStatisticsButtons.THREE.getKey().equals(playerOnlineStatisticsDto.getKey())) {
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

    /**
     * 玩家在线走势统计按钮
     */
    @RequestMapping("/playerOnlineStatisticsButtons")
    public ResponseBO playerOnlineStatisticsButtons() {
        return ResponseBO.data(EnumUtil.enumToVo(PlayerOnlineStatisticsButtons.values()));
    }


}
