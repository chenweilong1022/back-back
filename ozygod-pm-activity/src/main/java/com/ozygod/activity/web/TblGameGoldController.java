package com.ozygod.activity.web;

import com.ozygod.base.bo.ResponseBO;
import com.ozygod.model.zdlog.dto.TblGameGoldListDto;
import com.ozygod.model.zdlog.entity.TblGameGoldEntity;
import com.ozygod.model.zdlog.service.TblGameGoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 金币日志
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-31 13:48:42
 */
@RestController
@RequestMapping("zdlog/tblgamegold")
public class TblGameGoldController {
    @Autowired
    private TblGameGoldService tblGameGoldService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResponseBO list(@RequestBody TblGameGoldListDto tblGameGold){
        return tblGameGoldService.queryPage(tblGameGold);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    public ResponseBO info(@PathVariable("userid") Long userid){
			TblGameGoldEntity tblGameGold = tblGameGoldService.getById(userid);

        return ResponseBO.data(tblGameGold);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResponseBO save(@RequestBody TblGameGoldEntity tblGameGold){
			tblGameGoldService.save(tblGameGold);

        return ResponseBO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseBO update(@RequestBody TblGameGoldEntity tblGameGold){
			tblGameGoldService.updateById(tblGameGold);

        return ResponseBO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseBO delete(@RequestBody Long[] userids){
			tblGameGoldService.removeByIds(Arrays.asList(userids));

        return ResponseBO.ok();
    }

}
