package com.ozygod.task;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.ozygod.base.enums.RedisKeys;
import com.ozygod.base.redis.StringRedisDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 消息中心定时任务
 * 定时查询是否存在发送的消息
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-23 11:44
 */
@Configuration
@EnableScheduling
@Slf4j
public class MessagecenterTask {

    @Autowired
    private StringRedisDao stringRedisDao;

    /**
     * 每五分钟重新发送一次
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void resend() {

        Long size = stringRedisDao.queueSize(RedisKeys.MESSAGECENTERSENDERROR.getValue());

        if (size == 0) {
            return;
        }

        for (Long i = 0L; i < size; i++) {
            /**
             * 从队列弹出头部元素
             */
            String s = stringRedisDao.popFromQueue(RedisKeys.MESSAGECENTERSENDERROR.getValue());

            JSONArray jsonArray = JSONUtil.parseArray(s);
            String link = jsonArray.getStr(0);
            String body = jsonArray.getStr(1);

            log.info("link = {},body = {}",link,body);

            HttpResponse response = null;
            try {
                response = HttpRequest.post(link).timeout(3000).body(body).execute();
            }catch (Exception e) {
                stringRedisDao.push2Queue(RedisKeys.MESSAGECENTERSENDERROR.getValue(),jsonArray.toString());
            }

            /**
             * 如果不成功在压进去
             */
            if (ObjectUtil.isNotNull(response) && response.getStatus() != HttpStatus.HTTP_OK) {
                stringRedisDao.push2Queue(RedisKeys.MESSAGECENTERSENDERROR.getValue(),jsonArray.toString());
            }

        }

    }


}
