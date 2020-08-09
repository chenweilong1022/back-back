package com.ozygod.model.zdgame.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.ozygod.base.bo.ResponseBO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ozygod.model.zdgame.entity.TblPlayerinfoEntity;
import com.ozygod.model.zdgame.dto.TblPlayerinfoListDto;
import com.ozygod.model.zdgame.service.TblPlayerinfoService;



/**
 * 用户数据表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-20 11:39:15
 */
@RestController
@RequestMapping("zdgame/tblplayerinfo")
public class TblPlayerinfoController {
    @Autowired
    private TblPlayerinfoService tblPlayerinfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResponseBO list(@RequestBody TblPlayerinfoListDto tblPlayerinfo){
        return tblPlayerinfoService.queryPage(tblPlayerinfo);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    public ResponseBO info(@PathVariable("userid") Long userid){
			TblPlayerinfoEntity tblPlayerinfo = tblPlayerinfoService.getById(userid);

        return ResponseBO.data(tblPlayerinfo);
    }

    /**
     * 代理树形图
     */
    @RequestMapping("/agentTree")
    public ResponseBO agentTree(Long saler,Long userid){
        return ResponseBO.data(tblPlayerinfoService.agentTree(saler,userid));
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResponseBO save(@RequestBody TblPlayerinfoEntity tblPlayerinfo){
			tblPlayerinfoService.save(tblPlayerinfo);

        return ResponseBO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseBO update(@RequestBody TblPlayerinfoEntity tblPlayerinfo){
			tblPlayerinfoService.updateById(tblPlayerinfo);

        return ResponseBO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseBO delete(@RequestBody Long[] userids){
			tblPlayerinfoService.removeByIds(Arrays.asList(userids));

        return ResponseBO.ok();
    }

}
