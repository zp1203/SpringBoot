package com.springboot.utils;

import com.google.common.collect.Maps;
import com.springboot.utils.result.PaginationResult;
import com.springboot.utils.result.Result;


import java.util.Map;


public class ResultUtils {
    private static Map<Integer, String> messageMap = Maps.newHashMap();

    public ResultUtils() {
    }
    @SuppressWarnings("unchecked")
    public static <T> Result<T> generate(int status, String message, T data) {
        return new Result(status, message, data);
    }
    @SuppressWarnings("unchecked")
    public static <T> Result<T> generate(int status, T data) {
        return new Result(status, messageMap.get(status), data);
    }
    @SuppressWarnings("unchecked")
    public static <T> Result<T> generate(T data) {
        return new Result(200, messageMap.get(200), data);
    }
    @SuppressWarnings("unchecked")
    public static <T> PaginationResult<T> generate(int status, String message, T data, int count) {
        return new PaginationResult(status, message, count, data);
    }
    @SuppressWarnings("unchecked")
    public static <T> PaginationResult<T> generate(int status, T data, int count) {
        return new PaginationResult(status, messageMap.get(status), count, data);
    }
    @SuppressWarnings("unchecked")
    public static <T> PaginationResult<T> generate(T data, int count) {
        return new PaginationResult(200, messageMap.get(200), count, data);
    }

    static {
        messageMap.put(200, "请求成功");
        messageMap.put(500, "服务器错误");
        messageMap.put(503, "服务不可用");
        messageMap.put(505, "该版本已过时");
        messageMap.put(403, "操作已被禁止");
        messageMap.put(404, "对象不存在");
        messageMap.put(409, "对象冲突");
        messageMap.put(406, "该操作不可接受");
        messageMap.put(401, "权限不足");
        messageMap.put(400, "无效请求");
        messageMap.put(408, "请求超时");
    }
}
