package com.foo.redisexpirecallback.config;


import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RedisConfiguration {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.database}")
    private int database;

    @Bean
    RedisClient redisClient() {
        RedisURI uri = RedisURI.builder()
                .withHost(this.host)
                .withPort(this.port)
                .withDatabase(this.database)
                .withPassword(this.password.toCharArray())
                .build();
        RedisClient redisClient = RedisClient.create(uri);
        return redisClient;
    }


}
