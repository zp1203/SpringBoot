package com.springboot.service.impl;

import com.springboot.utils.RedisSerializerUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisSerializerUtils redisSerializerUtils;

    @Test
    @SuppressWarnings("unchecked")
    public void insertAndFindByMap() {
        //序列化
        redisSerializerUtils.redisSerializer2Map();
        redisTemplate.opsForHash().put("hashValue","map1","map1-1");
        redisTemplate.opsForHash().put("hashValue","map2","map2-2");

    }
}