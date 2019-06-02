package com.springboot.controller;

import com.springboot.vo.UseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by zp on 2019/4/20.
 * @author zp
 */
@Controller
@RequestMapping(value = "/index/")
@Slf4j
public class IndexController {


    /**
     * Value 这个注解的作用：
     */
    @Value(value = "${zp.secret}")
    private  String value;

    @ResponseBody
    @RequestMapping(value = "test")
    public String indexTest(){
        /**
         * 测试日志
         */
        log.error("error...");
        log.warn("warn...");
        log.info("info...");

        return "hello,world";
    }

    /**
     * 从 前台传来ID 以及 name
     * @param id
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "find/{id}/{name}")
    public UseInfo find(@PathVariable Integer id, @PathVariable String name){
        UseInfo useInfo =new UseInfo();
        useInfo.setId(id);
        useInfo.setName(name);
        return useInfo;
    }

    /**
     *  带参数传值  http://localhost:10086/index/get?name=zhangsan&age=15
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "get")
    public Map<String,Object> getValue(@RequestParam String name,@RequestParam Integer age){
        Map<String,Object> map = new HashMap<>(3);
        map.put("年龄",age);
        map.put("name",name);
        map.put("value",value);
        return  map;
    }

    /**
     * 获取代码
     * @throws InterruptedException
     */
    @ResponseBody
    @RequestMapping("getdm")
    public String getDm() throws InterruptedException {
        for(int i=0 ; i<110;i++){
            Thread.sleep(1000);
            int max = 99999;
            int min = 10000;
            Random random = new Random();
            int result = random.nextInt(max) % (max - min + 1) + min;
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String id = sdFormat.format(new Date());
            id += result;
            System.out.println("GZ"+id);
        }
        return "生成代码,OK";
    }



}