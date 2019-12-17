package com.ozygod.account.web;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.IndexCard;
import com.ozygod.base.enums.RedisKeys;
import com.ozygod.base.redis.IntegerRedisDao;
import com.ozygod.base.utils.EnumUtil;
import com.ozygod.base.vo.EnumVo;
import com.ozygod.base.vo.IndexCardVo;
import com.ozygod.model.zdconfig.service.TblIpWhiteListService;
import com.ozygod.model.zdgame.entity.TblAccountEntity;
import com.ozygod.model.zdgame.entity.TblOrderEntity;
import com.ozygod.model.zdgame.service.TblAccountService;
import com.ozygod.model.zdgame.service.TblOrderService;
import com.ozygod.model.zdlog.entity.TblRecordConversionEverydayEntity;
import com.ozygod.model.zdlog.service.TblRecordConversionEverydayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
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


}
