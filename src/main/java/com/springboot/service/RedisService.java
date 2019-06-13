package com.springboot.service;

import com.springboot.dao.UserRepository;
import com.springboot.model.UserModel;
import com.springboot.utils.RedisSerializerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class RedisService {

    @Autowired
    private RedisSerializerUtils redisSerializerUtils;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("unchecked")
    public List<UserModel> insertAndFindByString(){
        //序列化
        redisSerializerUtils.redisSerializer2String();
        /**
         * 高并发情况下 会存在 内存穿透的现象
         *  那么 如何解决呢？
         *  1.很容易想到 在 findValue()方法上 加个synchronized把该方法锁住，一次只允许一个用户进入
         *  这种方法可以解决问题，但是不要友好，也很影响效率。
         *  2. 采用 双重检测锁的形式 解决
         */
        List<UserModel> zhangsan  = (List<UserModel>) redisTemplate.opsForValue().get("ZhangSan");
        if(StringUtils.isEmpty(zhangsan)){
            synchronized(this){
                //查询redis缓存【根据key值来获取value】
                zhangsan = (List<UserModel>) redisTemplate.opsForValue().get("ZhangSan");
                //如果没有缓存,先去查询数据库，再把 查询得到的数据存到redis中去
                if (StringUtils.isEmpty(zhangsan)) {
                    //查询数据库
                    zhangsan = userRepository.findAll("ZhangSan");
                    //存入redis 缓存
                    redisTemplate.opsForValue().set("ZhangSan",zhangsan);

                    return zhangsan;
                }
            }
        }
        return zhangsan;
    }

    public void insertAndFindByMap(){
        //序列化
        redisSerializerUtils.redisSerializer2Map();
        redisTemplate.opsForHash().put("hashValue","map1","map1-1");
        redisTemplate.opsForHash().put("hashValue","map2","map2-2");
        List<Object> hashList = redisTemplate.opsForHash().values("hashValue");
        Map<Object, Object> entries = redisTemplate.opsForHash().entries("hashValue");
        System.out.println("通过values(H key)方法获取变量中的hashMap值:" + hashList);

    }

}
