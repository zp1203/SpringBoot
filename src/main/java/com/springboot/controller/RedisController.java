package com.springboot.controller;

import com.springboot.model.UserModel;
import com.springboot.service.RedisService;
import com.springboot.utils.ResultUtils;
import com.springboot.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "insertValue")
    @SuppressWarnings("unchecked")
    public Result<List<UserModel>> insertValue(){
        try {
            return ResultUtils.generate(HttpServletResponse.SC_OK,redisService.insertAndFindByString());
        }catch (Exception e){
            return ResultUtils.generate(HttpServletResponse.SC_NOT_FOUND,null);
        }

    }

    @RequestMapping(value = "findByMap")
    public void insertValue2Redis(){
        redisService.insertAndFindByMap();
    }
}
