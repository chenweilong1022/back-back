package com.ozygod.robot.listener;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.ozygod.model.common.dto.WinRateControlDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${game_url}")
    private String gameUrl;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对 redis 数据失效事件，进行数据处理
     *
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String key = message.toString();
        if (key.startsWith("winRateControlRandom")) {
            String[] split = key.split(":");
            Integer roomId = Integer.valueOf(split[1]);
//            获取胜率控制配置
            String winRateControlJson = redisTemplate.opsForValue().get("winRateControl:" + roomId);
            WinRateControlDTO winRateControlDTO = new WinRateControlDTO();
            if (StrUtil.isNotBlank(winRateControlJson) && JSONUtil.isJson(winRateControlJson)) {
                winRateControlDTO = JSONUtil.toBean(winRateControlJson, WinRateControlDTO.class);
                int i = RandomUtil.randomInt(0, 100);
                //随机到了 进行放分
                if (winRateControlDTO.getReleaseProbability().intValue() > i) {
                    String url = gameUrl + "/game_trick?roomid=" + roomId + "&val=" + winRateControlDTO.getReleaseLucky();
                    String resJson = HttpUtil.get(url);
                    log.info(url);
                    log.info(resJson);

                    DateTime dateTime = DateUtil.offsetMinute(DateUtil.date(), winRateControlDTO.getTimeInterval().intValue());
                    long ex = dateTime.getTime() - System.currentTimeMillis();
                    redisTemplate.opsForValue().set("winRateControlRandom:" + roomId,"1",ex, TimeUnit.MILLISECONDS);



                }
            }
        }
    }
}
