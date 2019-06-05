package com.springboot.utils.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    //状态
    private int status;
    //信息
    private String message;
    //数据
    private T data;

    public Result(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}
