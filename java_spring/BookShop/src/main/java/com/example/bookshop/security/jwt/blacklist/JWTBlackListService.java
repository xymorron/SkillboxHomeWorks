package com.example.bookshop.security.jwt.blacklist;

import com.example.bookshop.security.jwt.JWTUtil;
import org.redisson.api.RSetCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Service
public class JWTBlackListService {

    private final JWTUtil jwtUtil;
    private final Logger logger = Logger.getLogger(JWTBlackListService.class.getName());
    private final RedissonClient redissonClient;

    @Autowired
    public JWTBlackListService(JWTUtil jwtUtil, RedissonClient redissonClient) {
        this.jwtUtil = jwtUtil;
        this.redissonClient = redissonClient;
    }

    public boolean isBlackListed(String token) {
        String userName = jwtUtil.extractUserName(token);
        RSetCache<String> tokens = redissonClient.getSetCache(userName);
        return tokens.contains(token);
    }

    public void addToBlackList(String token) {
        String userName = jwtUtil.extractUserName(token);
        Date expiration = jwtUtil.extractExpiration(token);
        long estimate = expiration.getTime() - new Date().getTime();
        doAddToBlackList(userName, token, estimate);
    }

    private void doAddToBlackList(String userName, String token, long estimate) {
        RSetCache<String> tokens = redissonClient.getSetCache(userName);
        tokens.add(token, estimate, TimeUnit.MILLISECONDS);
        tokens.forEach(logger::info);
    }
}
