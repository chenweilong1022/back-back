package com.ozygod.api.web;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONArray;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.RedisKeys;
import com.ozygod.base.redis.StringRedisDao;
import com.ozygod.base.validator.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息中心
 * 1.先注册一个地址
 * 2.在将发送消息发送过来
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-23 10:27
 */
@RequestMapping("/api/messagecenter")
@RestController
@Slf4j
@Api(value = "/api/messagecenter", description = "消息中心")
public class MessageCenterController {


    @Autowired
    private StringRedisDao stringRedisDao;


    @PostMapping("register")
    @ApiOperation(value = "注册回调")
    public ResponseBO auth(
            String link,
            String type
    ) {
        Assert.isBlank(link,"回调不能为空");
        Assert.isBlank(type,"消息类型不能为空");
        Boolean b = stringRedisDao.saveAsJSONStr(RedisKeys.MESSAGECENTER.getValue() + type, link);
        return ResponseBO.data(b);
    }


    @PostMapping("send")
    @ApiOperation(value = "发送")
    public ResponseBO send(
            String type,
            @RequestBody String body
    ) {
        String link = stringRedisDao.readFromJSONStr(RedisKeys.MESSAGECENTER.getValue() + type);
        Assert.isBlank(link,"你还没有注册回调");


        HttpResponse response = HttpRequest.post(link).timeout(3000).body(body).execute();

        /**
         * 如果发送失败
         * 存入redis
         */
        if (HttpStatus.HTTP_OK != response.getStatus()) {
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(link);
            jsonArray.add(body);
            /**
             * 添加元素到Queue
             */
            stringRedisDao.push2Queue(RedisKeys.MESSAGECENTERSENDERROR.getValue(),jsonArray.toString());
        }
        return ResponseBO.ok();
    }

}
