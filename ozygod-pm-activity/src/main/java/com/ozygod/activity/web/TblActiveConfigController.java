package com.ozygod.activity.web;

import com.ozygod.base.bo.ResponseBO;
import com.ozygod.model.zdconfig.dto.TblActiveConfigListDto;
import com.ozygod.model.zdconfig.entity.TblActiveConfigEntity;
import com.ozygod.model.zdconfig.service.TblActiveConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 活动配置表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-13 11:20:04
 */
@RestController
@RequestMapping("zdconfig/tblactiveconfig")
public class TblActiveConfigController {
    @Autowired
    private TblActiveConfigService tblActiveConfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResponseBO list(@RequestBody TblActiveConfigListDto tblActiveConfig){
        return tblActiveConfigService.queryPage(tblActiveConfig);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public ResponseBO info(@PathVariable("id") Integer id){
			TblActiveConfigEntity tblActiveConfig = tblActiveConfigService.getById(id);

        return ResponseBO.data(tblActiveConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResponseBO save(@RequestBody TblActiveConfigEntity tblActiveConfig){
			tblActiveConfigService.save(tblActiveConfig);

        return ResponseBO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseBO update(@RequestBody TblActiveConfigEntity tblActiveConfig){


			tblActiveConfigService.updateById(tblActiveConfig);

        return ResponseBO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseBO delete(@RequestBody Integer[] ids){
			tblActiveConfigService.removeByIds(Arrays.asList(ids));

        return ResponseBO.ok();
    }

}
