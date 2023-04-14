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
    SERVER_ERROR(500, "服务器内部错误"),
    LOGIN_SUCCESS(1000, "登录成功"),
    USER_ERROR(1001, "用户名不存在"),
    PWD_ERROR(1002,"密码错误"),
    LOGOUT_SUCCESS(1003,"注销成功");
    private final int code;
    private final String message;
}