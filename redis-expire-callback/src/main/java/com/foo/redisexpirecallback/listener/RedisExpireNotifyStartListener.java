package com.foo.redisexpirecallback.listener;

import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 开启 redis 过期通知的监听器
 */
@Component
public class RedisExpireNotifyStartListener {
    @Autowired
    private RedisClient redisClient;

    @Bean
    void startListener() {

        StatefulRedisPubSubConnection<String, String> connection = redisClient.connectPubSub();
        connection.addListener(new RedisExpireNotifyListener());

        // 用同步的方式进行过期通知，可选择异步
        RedisPubSubCommands<String, String> commands = connection.sync();
        // 1. redis 服务器端配置 notify-keyspace-events 为 Kx
        // 2. 这种模式会导致所有的键过期时都会触发过期回调
        commands.psubscribe("__keyevent@1__:expired");
    }

}
