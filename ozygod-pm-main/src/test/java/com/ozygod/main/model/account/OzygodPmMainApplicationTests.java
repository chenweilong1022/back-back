package com.ozygod.main.model.account;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ozygod.base.enums.Global;
import com.ozygod.model.zdgame.entity.TblAccountEntity;
import com.ozygod.model.zdgame.entity.TblOrderEntity;
import com.ozygod.model.zdgame.service.TblAccountService;
import com.ozygod.model.zdgame.service.TblOrderService;
import com.ozygod.model.zdmanage.entity.TblAgentRealtimeEntity;
import com.ozygod.model.zdmanage.entity.TblAgentRecordEntity;
import com.ozygod.model.zdmanage.entity.TblWithdrawOrderEntity;
import com.ozygod.model.zdmanage.service.TblAgentRealtimeService;
import com.ozygod.model.zdmanage.service.TblAgentRecordService;
import com.ozygod.model.zdmanage.service.TblWithdrawOrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OzygodPmMainApplicationTests {

	@Autowired
	private TblAccountService tblAccountService;
	@Autowired
	private TblAgentRealtimeService tblAgentRealtimeService;
	@Autowired
	private TblAgentRecordService tblAgentRecordService;
	@Autowired
	private TblOrderService tblOrderService;
	@Autowired
	private TblWithdrawOrderService tblWithdrawOrderService;

//	@Autowired
//	private MallTblOrderService mallTblOrderService;
//
//	@Test
//	public void contextLoads1() {
//		List<MallTblOrderEntity> list = mallTblOrderService.list(null);
//		System.out.println(list);
//	}


	@Test
	public void contextLoads() {


		DateTime date = DateUtil.endOfDay(DateUtil.date());
		/**
		 * 当日第一秒
		 */
		DateTime beginOfDay = DateUtil.beginOfDay(DateUtil.date());
		/**
		 * 今日最后一秒
		 */
		DateTime endOfDay = DateUtil.parseDateTime(DateUtil.formatDateTime(date));
		/**
		 * 今日注册新用户
		 */
		List<TblAccountEntity> tblAccountEntities = tblAccountService.registerList(beginOfDay, endOfDay, null);

		log.info("tblAccountEntities = {}",tblAccountEntities);
		List<Long> newUserIds = tblAccountEntities.stream().map(TblAccountEntity::getUserid).collect(Collectors.toList());


		log.info("newUserIds = {}",newUserIds);


		/**
		 * 无限代
		 */
		List<TblAgentRealtimeEntity> tblAgentRealtimeEntities = new ArrayList<>();
		List<TblAgentRecordEntity> tblAgentRecordEntities = new ArrayList<>();
		/**
		 * 充值记录
		 */
		List<TblOrderEntity> tblOrderEntities = new ArrayList<>();

		/**
		 * 提现记录
		 */
		List<TblWithdrawOrderEntity> tblWithdrawOrderEntities = new ArrayList<>();


		List<TblAccountEntity> list = tblAccountService.list(new QueryWrapper<TblAccountEntity>().lambda()
				.gt(TblAccountEntity::getUserid, Global.REAL_USER_ID)
		);

		List<Long> listUserIdGt20000 = list.stream().map(TblAccountEntity::getUserid).collect(Collectors.toList());


		for (Long newUserId : newUserIds) {

			Random random = new Random();

			Long superId = 0L;

			if (random.nextBoolean()) {

				superId = RandomUtil.randomEle(listUserIdGt20000);
			}

			/**
			 * 绑定用户关系
			 */
			TblAgentRealtimeEntity tblAgentRealtimeEntity = new TblAgentRealtimeEntity();
			tblAgentRealtimeEntity.setUserId(newUserId);
			tblAgentRealtimeEntity.setSuperId(superId);

			TblAgentRecordEntity tblAgentRecordEntity = new TblAgentRecordEntity();
			tblAgentRecordEntity.setUserId(newUserId);
			tblAgentRecordEntity.setSuperId(superId);
			tblAgentRecordEntity.setPeriod(0);








//			alipay，支付宝；weixin，微信；apple，苹果
			List<String> channels = new ArrayList<>(3);
			channels.add("ALIPAY");
			channels.add("WEIXIN");
			channels.add("APPLE");
			List<Integer> moneys = new ArrayList<>(10);
			moneys.add(1000);
			moneys.add(2000);
			moneys.add(3000);
			moneys.add(4000);
			moneys.add(5000);
			moneys.add(6000);
			moneys.add(7000);
			moneys.add(8000);
			moneys.add(9000);
			/**
			 * 消费
			 */
			TblOrderEntity tblOrderEntity = new TblOrderEntity();
			tblOrderEntity.setUserid(newUserId);
			tblOrderEntity.setGoodsId("GOODS_WX_2018012");
			tblOrderEntity.setChannel(RandomUtil.randomEle(channels));
			tblOrderEntity.setOrderId(RandomUtil.simpleUUID().toUpperCase());
			tblOrderEntity.setMoney(RandomUtil.randomEle(moneys));
			tblOrderEntity.setState(3);
			tblOrderEntity.setCreateTime(new Date());
			tblOrderEntity.setInvalidTime(new Date());
			tblOrderEntity.setPayTime(new Date());
			tblOrderEntity.setCompleteTime(new Date());
			if (random.nextBoolean()) {
				tblOrderEntities.add(tblOrderEntity);
			}

			/**
			 * 提现记录
			 */
			TblWithdrawOrderEntity tblWithdrawOrderEntity = new TblWithdrawOrderEntity();
//			tblWithdrawOrderEntity.setId();
			tblWithdrawOrderEntity.setUserid(newUserId.intValue());
			tblWithdrawOrderEntity.setState(0);
			tblWithdrawOrderEntity.setType(0);
			tblWithdrawOrderEntity.setAccount(RandomUtil.randomNumbers(11));
			tblWithdrawOrderEntity.setRealname(RandomUtil.randomString(11));
			tblWithdrawOrderEntity.setAmount(RandomUtil.randomEle(moneys));
			tblWithdrawOrderEntity.setTax(0);
//			tblWithdrawOrderEntity.setNote();
//			tblWithdrawOrderEntity.setApprovalId();
			tblWithdrawOrderEntity.setCompletetime(new Date());
			tblWithdrawOrderEntity.setCreateTime(new Date());
			if (random.nextBoolean()) {
				tblWithdrawOrderEntities.add(tblWithdrawOrderEntity);
			}


			TblAgentRealtimeEntity one = tblAgentRealtimeService.getOne(new QueryWrapper<TblAgentRealtimeEntity>().lambda()
					.eq(TblAgentRealtimeEntity::getUserId, newUserId)
			);

			if (ObjectUtil.isNull(one)) {
				tblAgentRealtimeEntities.add(tblAgentRealtimeEntity);
				tblAgentRecordEntities.add(tblAgentRecordEntity);
			}






		}

		tblAgentRealtimeService.saveBatch(tblAgentRealtimeEntities);
		tblAgentRecordService.saveBatch(tblAgentRecordEntities);
		////////////////////////////////////////////////////####分界线 保存代理关系####////////////////////////////////////////////////////########
		tblOrderService.saveBatch(tblOrderEntities);
		////////////////////////////////////////////////////####分界线 保存购买记录####////////////////////////////////////////////////////########
		tblWithdrawOrderService.saveBatch(tblWithdrawOrderEntities);
		////////////////////////////////////////////////////####分界线 保存提现记录####////////////////////////////////////////////////////########







	}

}
