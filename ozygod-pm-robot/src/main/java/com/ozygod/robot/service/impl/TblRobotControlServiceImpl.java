package com.ozygod.robot.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.model.common.dto.WinRateControlDTO;
import com.ozygod.model.zdconfig.dao.TblRobotControlDao;
import com.ozygod.model.zdconfig.dto.TblRobotControlListDto;
import com.ozygod.model.zdconfig.entity.TblRobotControlEntity;
import com.ozygod.robot.service.TblRobotControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Service("tblRobotControlService")
public class TblRobotControlServiceImpl extends ServiceImpl<TblRobotControlDao, TblRobotControlEntity> implements TblRobotControlService {


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public ResponseBO queryPage(TblRobotControlListDto tblRobotControlListDto) {
        IPage<TblRobotControlEntity> page = baseMapper.selectPage(
                tblRobotControlListDto.getPage(),
                new QueryWrapper<TblRobotControlEntity>().lambda()
                .like(StrUtil.isNotBlank(tblRobotControlListDto.getName()),TblRobotControlEntity::getName,tblRobotControlListDto.getName())
        );
        return ResponseBO.page(page);
    }


    @EventListener
    @Async
    @Order(value = 100)
    public void handlerApplicationReadyEvent(ApplicationReadyEvent event) {


        Set<String> keys = redisTemplate.keys("winRateControl*");


        if (CollUtil.isNotEmpty(keys)) {


            for (String key : keys) {

                String[] split = key.split(":");

                Integer roomId = Integer.valueOf(split[1]);

                //            获取胜率控制配置
                String winRateControlJson = redisTemplate.opsForValue().get("winRateControl:" + roomId);
                WinRateControlDTO winRateControlDTO = new WinRateControlDTO();
                if (StrUtil.isNotBlank(winRateControlJson) && JSONUtil.isJson(winRateControlJson)) {
                    winRateControlDTO = JSONUtil.toBean(winRateControlJson, WinRateControlDTO.class);

                    DateTime dateTime = DateUtil.offsetSecond(DateUtil.date(), winRateControlDTO.getTimeInterval().intValue());
                    long ex = dateTime.getTime() - System.currentTimeMillis();
                    redisTemplate.opsForValue().set("winRateControlRandom:" + roomId,"1",ex, TimeUnit.MILLISECONDS);
                }
            }


        }






    }

}
