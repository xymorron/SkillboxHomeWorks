package com.example.bookshop.security.jwt.blacklist;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class RedissonConfig {

    @Value("${redisson.url}")
    private String redisLink;

    @Bean
    public RedissonClient getRedissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress(redisLink);
        return Redisson.create(config);
    }
}
