package com.foo.redisexpirecallback;

import com.foo.redisexpirecallback.listener.RedisExpireNotifyListener;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class RedisExpireCallbackApplicationTests {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void contextLoads() throws ParseException {
        //redisTemplate.opsForValue().set("redis", "hello");
        redisTemplate.opsForValue().set("wechat2", "过期回调成功啦啦啦");

        // 过 10s 过期
        redisTemplate.expireAt("wechat2", new Date(new Date().getTime() + 10 * 1000L));

        redisTemplate.opsForHash().put("work-wechat", "user1", "原计划于 5/18 进行");
        redisTemplate.expireAt("work-wechat", new Date(new Date().getTime() + 10 * 1000L));
    }

    @Test
    void dateTest() throws ParseException {
        List<RedisClientInfo> clientList = redisTemplate.getClientList();
        clientList.stream().forEach(System.out::println);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = sdf.parse("2021-09-15 12:25:00");
        System.out.println("parse = " + parse);
    }

    @Test
    void dateTest2() {
        Date date = new Date();
        long time = date.getTime();
        System.out.println("time = " + time);
    }

    @Test
    void redistemplateTest() {
        List clientList = redisTemplate.getClientList();
        clientList.stream().forEach(System.out::println);
    }

    @Test
    void expireListenerTest() throws InterruptedException {
        RedisURI uri = RedisURI.builder()
                .withHost("1.117.5.70")
                .withPort(6379)
                .withDatabase(1)
                .build();
        RedisClient redisClient = RedisClient.create(uri);
        StatefulRedisConnection<String, String> connect = redisClient.connect();

        StatefulRedisPubSubConnection<String, String> connection = redisClient.connectPubSub();
        connection.addListener(new RedisExpireNotifyListener());

        RedisPubSubCommands<String, String> commands = connection.sync();
        commands.psubscribe("__keyevent@1__:expired");
        Thread.sleep(10000);
        connect.close();
        connection.close();
        redisClient.shutdown();

    }


}
