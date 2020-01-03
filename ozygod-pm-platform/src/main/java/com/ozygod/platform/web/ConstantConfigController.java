package com.ozygod.platform.web;

import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.AccounChannelClassify;
import com.ozygod.base.enums.AccountRegisterChannel;
import com.ozygod.base.utils.EnumUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台全局枚举配置
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-03 14:34
 */
@RestController
@RequestMapping("platform/constantconfig")
public class ConstantConfigController {


    /**
     * 渠道列表
     */
    @RequestMapping("/accountregisterchannel")
    public ResponseBO accountregisterchannel(){
        return ResponseBO.data(EnumUtil.enumToVo(AccountRegisterChannel.values()));
    }

    /**
     * 渠道分类列表
     */
    @RequestMapping("/accounchannelclassify")
    public ResponseBO accounchannelclassify(){
        return ResponseBO.data(EnumUtil.enumToVo(AccounChannelClassify.values()));
    }


}
