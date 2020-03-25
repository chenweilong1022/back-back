package com.ozygod.robot.web;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.validator.Assert;
import com.ozygod.model.zdconfig.dto.TblRobotControlAuthDto;
import com.ozygod.model.zdconfig.dto.TblRobotControlListDto;
import com.ozygod.model.zdconfig.entity.TblRobotControlEntity;
import com.ozygod.robot.service.TblRobotControlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 机器人控制
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-12 17:06:41
 */
@RestController
@Slf4j
@RequestMapping(value = {"api/robots","robots"})
@Api(value = "机器人控制端账号",tags = "机器人控制端账号")
public class TblRobotControlController {


    @Autowired
    private TblRobotControlService tblRobotControlService;
    @Value("${game_url}")
    private String gameUrl;

    /**
     * 控制端鉴权
     * @param method
     * @param tblRobotControlAuthDto
     * @param request
     * @return
     */
    @PostMapping("auth/{method}")
    @ApiOperation(value = "控制端鉴权", response = String.class)
    public JSONObject auth(
            @PathVariable("method") String method,
//            TblRobotControlAuthDto tblRobotControlAuthDto,
            @RequestHeader("key") String key,
            @RequestHeader("token") String token,
            @RequestParam Map<String,Object> map,
            HttpServletRequest request
    ) {


        TblRobotControlAuthDto tblRobotControlAuthDto = new TblRobotControlAuthDto();
        tblRobotControlAuthDto.setKey(key);
        tblRobotControlAuthDto.setToken(token);



        Assert.isBlank(tblRobotControlAuthDto.getKey(),"key不能为空");
        Assert.isBlank(tblRobotControlAuthDto.getToken(),"token不能为空");

        TblRobotControlEntity one = tblRobotControlService.getOne(new QueryWrapper<TblRobotControlEntity>().lambda()
                .eq(TblRobotControlEntity::getKey, tblRobotControlAuthDto.getKey())
        );

        Assert.isNull(one,"key错误");

        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(tblRobotControlAuthDto);
        stringObjectMap.remove("token");
        String s1 = HttpUtil.toParams(stringObjectMap);

        if (CollUtil.isNotEmpty(map)) {
            String collect = map.keySet().stream().sorted().map(k -> k + "=" + map.get(k)).collect(Collectors.joining("","",s1 + one.getSecret()));
            log.info("加密字符串 = {}",collect);
            String s = SecureUtil.md5(collect);
            Assert.isTrue(!tblRobotControlAuthDto.getToken().equals(s),"鉴权失败");
        }else {
            Assert.isTrue(!tblRobotControlAuthDto.getToken().equals(SecureUtil.md5(  s1 + one.getSecret())),"鉴权失败");
        }


        String clientIP = ServletUtil.getClientIP(request, "X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP");

        Assert.isTrue(!one.getIp().contains(clientIP),"当前ip不能登录");

        if ("login".equals(method)) {
            return JSONUtil.parseObj(ResponseBO.ok());
        }

        String s = HttpUtil.get(gameUrl + File.separator + method,map,3000);
        Assert.isTrue(!JSONUtil.isJson(s),s);

        return JSONUtil.parseObj(s);
    }

    /**
     * 列表
     */
    @PostMapping("/listRobotControl")
    public ResponseBO list(@RequestBody TblRobotControlListDto tblRobotControlListDto){
        return tblRobotControlService.queryPage(tblRobotControlListDto);
    }

    /**
     * 保存
     */
    @PostMapping("/saveRobotControl")
    public ResponseBO save(@RequestBody TblRobotControlEntity tblRobotControl){
        /**
         * 生成uuid key
         */
        tblRobotControl.setKey(RandomUtil.simpleUUID().toUpperCase());
        tblRobotControl.setSecret(RandomUtil.simpleUUID().toUpperCase());
        tblRobotControlService.save(tblRobotControl);
        return ResponseBO.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/updateRobotControl")
    public ResponseBO updateRobotControl(@RequestBody TblRobotControlEntity tblRobotControl){
        tblRobotControlService.updateById(tblRobotControl);
        return ResponseBO.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/deleteRobotControl/{id}")
    public ResponseBO deleteRobotControl(@PathVariable("id") Integer id){
        tblRobotControlService.removeById(id);
        return ResponseBO.ok();
    }


}
