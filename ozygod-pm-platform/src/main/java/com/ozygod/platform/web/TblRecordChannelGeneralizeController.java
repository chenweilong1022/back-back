package com.ozygod.platform.web;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.RecordChannelGeneralizeButtons;
import com.ozygod.base.utils.OzygodDateUtil;
import com.ozygod.base.vo.EnumVo;
import com.ozygod.model.zdlog.dto.TblRecordChannelGeneralizeListDto;
import com.ozygod.model.zdlog.entity.TblRecordChannelGeneralizeEntity;
import com.ozygod.model.zdlog.service.TblRecordChannelGeneralizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 每日渠道推广统计
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-02 13:54:15
 */
@RestController
@RequestMapping("zdlog/tblrecordchannelgeneralize")
public class TblRecordChannelGeneralizeController {
    @Autowired
    private TblRecordChannelGeneralizeService tblRecordChannelGeneralizeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResponseBO list(@RequestBody TblRecordChannelGeneralizeListDto tblRecordChannelGeneralize){
        return tblRecordChannelGeneralizeService.queryPage(tblRecordChannelGeneralize);
    }


    /**
     * 渠道统计按钮列表
     */
    @RequestMapping("/recordchannelgeneralizebuttons")
    public ResponseBO recordchannelgeneralizebuttons(){

        List<EnumVo> enumVos = new ArrayList<>();

        RecordChannelGeneralizeButtons[] values = RecordChannelGeneralizeButtons.values();
        for (RecordChannelGeneralizeButtons recordChannelGeneralizeButtons : values) {
            /**
             * 返回vo
             */
            EnumVo enumVo = new EnumVo();
            /**
             * 时间开始
             */
            DateTime beginOfDay = OzygodDateUtil.beginOfDay(recordChannelGeneralizeButtons.getDateTime());
            /**
             * 时间结束
             */
            DateTime endOfDay = OzygodDateUtil.endOfDay(recordChannelGeneralizeButtons.getDateTime());
            /**
             * 查询数量
             */
            int count = tblRecordChannelGeneralizeService.count(new QueryWrapper<TblRecordChannelGeneralizeEntity>().lambda()
                    .ge(ObjectUtil.isNotNull(beginOfDay), TblRecordChannelGeneralizeEntity::getCurrentDates, beginOfDay)
                    .le(ObjectUtil.isNotNull(endOfDay), TblRecordChannelGeneralizeEntity::getCurrentDates, endOfDay)
            );
            /**
             * 设置数据返回
             */
            enumVo.setKey(recordChannelGeneralizeButtons.getKey());
            enumVo.setValue(String.format(recordChannelGeneralizeButtons.getValue(),count));
            enumVos.add(enumVo);
        }

        return ResponseBO.data(enumVos);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public ResponseBO info(@PathVariable("id") Long id){
			TblRecordChannelGeneralizeEntity tblRecordChannelGeneralize = tblRecordChannelGeneralizeService.getById(id);

        return ResponseBO.data(tblRecordChannelGeneralize);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResponseBO save(@RequestBody TblRecordChannelGeneralizeEntity tblRecordChannelGeneralize){
			tblRecordChannelGeneralizeService.save(tblRecordChannelGeneralize);

        return ResponseBO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseBO update(@RequestBody TblRecordChannelGeneralizeEntity tblRecordChannelGeneralize){
			tblRecordChannelGeneralizeService.updateById(tblRecordChannelGeneralize);

        return ResponseBO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseBO delete(@RequestBody Long[] ids){
			tblRecordChannelGeneralizeService.removeByIds(Arrays.asList(ids));

        return ResponseBO.ok();
    }

}
