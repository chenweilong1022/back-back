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

import com.ozygod.model.zdmanage.entity.TblWithdrawOrderEntity;
import com.ozygod.model.zdmanage.dto.TblWithdrawOrderListDto;
import com.ozygod.model.zdmanage.service.TblWithdrawOrderService;



/**
 * 
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-30 18:00:03
 */
@RestController
@RequestMapping("zdmanage/tblwithdraworder")
public class TblWithdrawOrderController {
    @Autowired
    private TblWithdrawOrderService tblWithdrawOrderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResponseBO list(@RequestBody TblWithdrawOrderListDto tblWithdrawOrder){
        return tblWithdrawOrderService.queryPage(tblWithdrawOrder);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public ResponseBO info(@PathVariable("id") Long id){
			TblWithdrawOrderEntity tblWithdrawOrder = tblWithdrawOrderService.getById(id);

        return ResponseBO.data(tblWithdrawOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResponseBO save(@RequestBody TblWithdrawOrderEntity tblWithdrawOrder){
			tblWithdrawOrderService.save(tblWithdrawOrder);

        return ResponseBO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseBO update(@RequestBody TblWithdrawOrderEntity tblWithdrawOrder){
			tblWithdrawOrderService.updateById(tblWithdrawOrder);

        return ResponseBO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseBO delete(@RequestBody Long[] ids){
			tblWithdrawOrderService.removeByIds(Arrays.asList(ids));

        return ResponseBO.ok();
    }

}
