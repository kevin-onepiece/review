package com.foo.redisexpirecallback.listener;

import io.lettuce.core.pubsub.RedisPubSubAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisExpireNotifyListener extends RedisPubSubAdapter {
    @Autowired
    private RedisTemplate redisTemplate;

    // redis 过期回调方法，某个键过期时，会回调这个方法。
    @Override
    public void message(Object pattern, Object channel, Object message) {
        String expireKey = (String) message;
        System.out.println("expireKey = " + expireKey);
        /*String value = (String) redisTemplate.opsForValue().get(expireKey);
        System.out.println("value = " + value);*/
        /*System.out.println("channel.toString() = " + channel.toString());
        System.out.println("pattern.toString() = " + pattern.toString());*/
    }


}