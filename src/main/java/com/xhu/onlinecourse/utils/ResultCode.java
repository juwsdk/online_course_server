package com.xhu.onlinecourse.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultCode {
    SUCCESS(200, "操作成功"),
    FAIL(400, "操作失败"),
    UNAUTHORIZED(401, "未认证或认证失败"),
    NOT_FOUND(404, "请求资源不存在"),
    SERVER_ERROR(500, "服务器内部错误");

    private final int code;
    private final String message;
}