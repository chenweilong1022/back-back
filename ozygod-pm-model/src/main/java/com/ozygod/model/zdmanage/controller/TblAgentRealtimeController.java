package com.ozygod.model.zdmanage.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.ozygod.base.bo.ResponseBO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ozygod.model.zdmanage.entity.TblAgentRealtimeEntity;
import com.ozygod.model.zdmanage.dto.TblAgentRealtimeListDto;
import com.ozygod.model.zdmanage.service.TblAgentRealtimeService;



/**
 * 
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-02 14:17:45
 */
@RestController
@RequestMapping("zdmanage/tblagentrealtime")
public class TblAgentRealtimeController {
    @Autowired
    private TblAgentRealtimeService tblAgentRealtimeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResponseBO list(@RequestBody TblAgentRealtimeListDto tblAgentRealtime){
        return tblAgentRealtimeService.queryPage(tblAgentRealtime);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    public ResponseBO info(@PathVariable("userId") Long userId){
			TblAgentRealtimeEntity tblAgentRealtime = tblAgentRealtimeService.getById(userId);

        return ResponseBO.data(tblAgentRealtime);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResponseBO save(@RequestBody TblAgentRealtimeEntity tblAgentRealtime){
			tblAgentRealtimeService.save(tblAgentRealtime);

        return ResponseBO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseBO update(@RequestBody TblAgentRealtimeEntity tblAgentRealtime){
			tblAgentRealtimeService.updateById(tblAgentRealtime);

        return ResponseBO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseBO delete(@RequestBody Long[] userIds){
			tblAgentRealtimeService.removeByIds(Arrays.asList(userIds));

        return ResponseBO.ok();
    }

}
