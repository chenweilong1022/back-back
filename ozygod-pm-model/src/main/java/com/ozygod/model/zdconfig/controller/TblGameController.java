package com.ozygod.model.zdconfig.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.ozygod.base.bo.ResponseBO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ozygod.model.zdconfig.entity.TblGameEntity;
import com.ozygod.model.zdconfig.dto.TblGameListDto;
import com.ozygod.model.zdconfig.service.TblGameService;



/**
 * 
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-03 10:27:36
 */
@RestController
@RequestMapping("zdconfig/tblgame")
public class TblGameController {
    @Autowired
    private TblGameService tblGameService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResponseBO list(@RequestBody TblGameListDto tblGame){
        return tblGameService.queryPage(tblGame);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{gameid}")
    public ResponseBO info(@PathVariable("gameid") Integer gameid){
			TblGameEntity tblGame = tblGameService.getById(gameid);

        return ResponseBO.data(tblGame);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResponseBO save(@RequestBody TblGameEntity tblGame){
			tblGameService.save(tblGame);

        return ResponseBO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseBO update(@RequestBody TblGameEntity tblGame){
			tblGameService.updateById(tblGame);

        return ResponseBO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseBO delete(@RequestBody Integer[] gameids){
			tblGameService.removeByIds(Arrays.asList(gameids));

        return ResponseBO.ok();
    }

}
