package com.springboot.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by zp on 2019/4/20.
 * @author zp
 */
@Setter
@Getter
public class UseInfo {

    private Integer id;

    private String name;

//引入Lombok就不需要在写 set、get方法，注解用@Setter @Getter

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }S
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

}
