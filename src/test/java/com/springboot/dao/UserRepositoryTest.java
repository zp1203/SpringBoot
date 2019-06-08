package com.springboot.dao;

import com.springboot.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    //插入
    @Test
    public void insertByMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("name","ZhangSan");
        map.put("name_type","admin");
        map.put("user_password","123456");
        int result = userRepository.insertByMap(map);
        Assert.assertEquals(1,result);
    }

    //插入
    @Test
    public void insertByObject(){
        User user1= new User();
        user1.setName("李四");
        user1.setName_type("administrator");
        user1.setUser_password("123456");
        int result = userRepository.insertByObject(user1);
        Assert.assertEquals(1,result);
    }

    //查询
    @Test
    public void findAllOfOne(){
        User result = userRepository.findAllOfOne("李四");
        Assert.assertNotNull(result);
    }

    @Test
    public void findByName(){
        String result = userRepository.findByName("admin");
        Assert.assertNotNull(result);
    }


    /**
     * 更新
     */
    @Test
    public void updateByName(){
        int result = userRepository.updateByName("张三","zs");
        Assert.assertEquals(1,result);
    }

    /**
     * 删除
     */
    @Test
    public void deleteByName(){
        int result = userRepository.deleteByName("李四");
        Assert.assertEquals(1,result);
    }


}