package com.xhu.onlinecourse.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
/*
Result泛型类，用来返回前端相应的数据
 */
@Data
@AllArgsConstructor
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
