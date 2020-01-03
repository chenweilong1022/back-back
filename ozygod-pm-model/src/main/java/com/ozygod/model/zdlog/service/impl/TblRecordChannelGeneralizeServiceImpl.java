package com.ozygod.model.zdlog.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ozygod.base.enums.AccounChannelClassify;
import com.ozygod.base.enums.Global;
import com.ozygod.base.enums.RecordChannelGeneralizeButtons;
import com.ozygod.base.utils.OzygodDateUtil;
import com.ozygod.model.zdlog.vo.TblRecordChannelGeneralizeTotalVo;
import com.ozygod.model.zdlog.vo.TblRecordChannelGeneralizeVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdlog.dao.TblRecordChannelGeneralizeDao;
import com.ozygod.model.zdlog.entity.TblRecordChannelGeneralizeEntity;
import com.ozygod.model.zdlog.dto.TblRecordChannelGeneralizeListDto;
import com.ozygod.model.zdlog.service.TblRecordChannelGeneralizeService;


@Service("tblRecordChannelGeneralizeService")
public class TblRecordChannelGeneralizeServiceImpl extends ServiceImpl<TblRecordChannelGeneralizeDao, TblRecordChannelGeneralizeEntity> implements TblRecordChannelGeneralizeService {


    @Override
    public ResponseBO queryPage(TblRecordChannelGeneralizeListDto tblRecordChannelGeneralize) {

        /**
         * 判断前端是否选择渠道分类
         */
        AccounChannelClassify accounChannelClassify = null;
        if (ObjectUtil.isNotNull(tblRecordChannelGeneralize.getAccounchannelClassify())) {
            accounChannelClassify = AccounChannelClassify.getByKey(tblRecordChannelGeneralize.getAccounchannelClassify());
        }
        Integer agentUserId = ObjectUtil.isNotNull(accounChannelClassify) ? accounChannelClassify.getAgentUserId() : null;
        /**
         * 获取当前选中的的类型
         */
        RecordChannelGeneralizeButtons recordChannelGeneralizeButtons = RecordChannelGeneralizeButtons.getByKey(tblRecordChannelGeneralize.getRecordchannelgeneralizebuttons());
        /**
         * 获取选中的时间
         */
        DateTime beginOfDay = OzygodDateUtil.beginOfDay(recordChannelGeneralizeButtons.getDateTime());
        DateTime endOfDay = OzygodDateUtil.endOfDay(recordChannelGeneralizeButtons.getDateTime());
        /**
         * qw筛选
         */
        LambdaQueryWrapper<TblRecordChannelGeneralizeEntity> eq = new QueryWrapper<TblRecordChannelGeneralizeEntity>().lambda()
                .ge(ObjectUtil.isNotNull(beginOfDay), TblRecordChannelGeneralizeEntity::getCurrentDates, beginOfDay)
                .le(ObjectUtil.isNotNull(endOfDay), TblRecordChannelGeneralizeEntity::getCurrentDates, endOfDay)
                .notIn(AccounChannelClassify.ALL_GENERATIONS.equals(accounChannelClassify), TblRecordChannelGeneralizeEntity::getAgentUserId, agentUserId)
                .eq(AccounChannelClassify.SELF_REGISTRATION.equals(accounChannelClassify), TblRecordChannelGeneralizeEntity::getAgentUserId, agentUserId)
                .eq(ObjectUtil.isNotNull(tblRecordChannelGeneralize.getAgentUserId()), TblRecordChannelGeneralizeEntity::getAgentUserId, tblRecordChannelGeneralize.getAgentUserId())
                .ge(ObjectUtil.isNotNull(tblRecordChannelGeneralize.getStartTime()), TblRecordChannelGeneralizeEntity::getCurrentDates, tblRecordChannelGeneralize.getStartTime())
                .le(ObjectUtil.isNotNull(tblRecordChannelGeneralize.getEndTime()), TblRecordChannelGeneralizeEntity::getCurrentDates, tblRecordChannelGeneralize.getEndTime())
                .eq(StrUtil.isNotBlank(tblRecordChannelGeneralize.getChannel()), TblRecordChannelGeneralizeEntity::getChannel, tblRecordChannelGeneralize.getChannel());

        /**
         * 分页查询
         */
        IPage<TblRecordChannelGeneralizeEntity> page = baseMapper.selectPage(tblRecordChannelGeneralize.getPage(), eq);
        /**
         * 全部查询
         */
        List<TblRecordChannelGeneralizeEntity> tblRecordChannelGeneralizeEntities = baseMapper.selectList(eq);


        /**
         * 总统计参数
         */
        int newUser = 0;
        int newUsersRechargeNumber = 0;
        String newUserRechargeRatio = Global.PROPORTION;
        int newUserRecharge = 0;
        int todayRecharge = 0;
        int todayConversion = 0;
        int rechargePoor = 0;
        /**
         * 查询总统计
         */
        if (CollUtil.isNotEmpty(tblRecordChannelGeneralizeEntities)) {
            newUser = tblRecordChannelGeneralizeEntities.stream().mapToInt(TblRecordChannelGeneralizeEntity::getNewUser).sum();
            newUsersRechargeNumber = tblRecordChannelGeneralizeEntities.stream().mapToInt(TblRecordChannelGeneralizeEntity::getNewUsersRechargeNumber).sum();
            if (newUser > 0) {
                double div = NumberUtil.div(newUsersRechargeNumber, newUser);
                newUserRechargeRatio = NumberUtil.decimalFormat(Global.FORMAT_PERCENTAGE, div);
            }
            newUserRecharge = tblRecordChannelGeneralizeEntities.stream().mapToInt(TblRecordChannelGeneralizeEntity::getNewUserRecharge).sum();
            todayRecharge = tblRecordChannelGeneralizeEntities.stream().mapToInt(TblRecordChannelGeneralizeEntity::getTodayRecharge).sum();
            todayConversion = tblRecordChannelGeneralizeEntities.stream().mapToInt(TblRecordChannelGeneralizeEntity::getTodayConversion).sum();
            rechargePoor = tblRecordChannelGeneralizeEntities.stream().mapToInt(TblRecordChannelGeneralizeEntity::getRechargePoor).sum();
        }
        /**
         * 设置总统计
         */
        TblRecordChannelGeneralizeTotalVo tblRecordChannelGeneralizeTotalVo = new TblRecordChannelGeneralizeTotalVo();
        tblRecordChannelGeneralizeTotalVo.setNewUser(newUser);
        tblRecordChannelGeneralizeTotalVo.setNewUsersRechargeNumber(newUsersRechargeNumber);
        tblRecordChannelGeneralizeTotalVo.setNewUserRechargeRatio(newUserRechargeRatio);
        tblRecordChannelGeneralizeTotalVo.setNewUserRecharge(newUserRecharge);
        tblRecordChannelGeneralizeTotalVo.setTodayRecharge(todayRecharge);
        tblRecordChannelGeneralizeTotalVo.setTodayConversion(todayConversion);
        tblRecordChannelGeneralizeTotalVo.setRechargePoor(rechargePoor);
        /**
         * 返回参数
         */
        TblRecordChannelGeneralizeVo tblRecordChannelGeneralizeVo = new TblRecordChannelGeneralizeVo();
        tblRecordChannelGeneralizeVo.setTblRecordChannelGeneralizeTotalVo(tblRecordChannelGeneralizeTotalVo);
        tblRecordChannelGeneralizeVo.setTblRecordChannelGeneralizeEntities(page.getRecords());
        ResponseBO page1 = ResponseBO.page(page);
        page1.setData(tblRecordChannelGeneralizeVo);
        return page1;
    }
}
