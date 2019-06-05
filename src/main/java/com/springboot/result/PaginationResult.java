package com.springboot.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Setter
@Getter
public class PaginationResult<T> extends Result<T> implements Serializable {

    private int count;

    public PaginationResult(int code, String msg, int count, T data) {
        super(code, msg, data);
        this.count = count;
    }
}
