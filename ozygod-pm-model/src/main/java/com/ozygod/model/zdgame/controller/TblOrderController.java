package com.ozygod.model.zdgame.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.ozygod.base.bo.ResponseBO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ozygod.model.zdgame.entity.TblOrderEntity;
import com.ozygod.model.zdgame.dto.TblOrderListDto;
import com.ozygod.model.zdgame.service.TblOrderService;



/**
 * 
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-19 14:18:44
 */
@RestController
@RequestMapping("zdgame/tblorder")
public class TblOrderController {
    @Autowired
    private TblOrderService tblOrderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResponseBO list(@RequestBody TblOrderListDto tblOrder){
        return tblOrderService.queryPage(tblOrder);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public ResponseBO info(@PathVariable("id") Long id){
			TblOrderEntity tblOrder = tblOrderService.getById(id);

        return ResponseBO.data(tblOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResponseBO save(@RequestBody TblOrderEntity tblOrder){
			tblOrderService.save(tblOrder);

        return ResponseBO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseBO update(@RequestBody TblOrderEntity tblOrder){
			tblOrderService.updateById(tblOrder);

        return ResponseBO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseBO delete(@RequestBody Long[] ids){
			tblOrderService.removeByIds(Arrays.asList(ids));

        return ResponseBO.ok();
    }

}
