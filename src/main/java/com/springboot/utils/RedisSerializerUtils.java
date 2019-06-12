package com.springboot.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * 添加这个组件的原因是：向Redis里面添加数据时 如果没有 序列化那么存入到Redis中的数据不是我们想要看到的，可读性差，
 * 但是序列化之后，就正常了。
 */
@Component
public class RedisSerializerUtils {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    @SuppressWarnings("unchecked")
    public void redisSerializer2String(){
        /**
         * redis是nosql数据库的一种，它是一种key-value型数据库
         * 把 key 值序列化成String类型
         */
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);

        /**
         * 把 value 值序列化成json类型
         * 两种序列化方式：1. FastJsonRedisSerializer 这种方式有点不好的地方是它会把一些value为NULL的key删除掉
         *                2. Jackson2JsonRedisSerializer 这种方式正常
         */
        RedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        redisTemplate.setValueSerializer(serializer);
    }

    @SuppressWarnings("unchecked")
    public void redisSerializer2Map(){
        //把 key值 序列化成 String类型
        // value 其实是map形式的---那么把 hashKey序列化成String类型
        RedisSerializer redisSerializer =new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        //把hashValue 序列化成Json格式
        RedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        redisTemplate.setHashValueSerializer(serializer);
    }

}
