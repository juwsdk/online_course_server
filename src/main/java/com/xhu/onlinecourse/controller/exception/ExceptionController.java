package com.xhu.onlinecourse.controller.exception;

import com.xhu.onlinecourse.utils.Result;
import com.xhu.onlinecourse.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.sql.SQLException;

@Api("错误处理接口")
@ControllerAdvice
@ResponseBody
public class ExceptionController {

    @ApiOperation(value = "当没有此用户时")
    @ExceptionHandler(UnknownAccountException.class)
    public Result handleUnknownAccountException(){
        return new Result(ResultCode.USER_ERROR.getCode(), ResultCode.USER_ERROR.getMessage());
    }

    @ApiOperation(value = "用户名取出密码失败时")
    @ExceptionHandler(IncorrectCredentialsException.class)
    public Result handleNumberFormatException(){
        return new Result(ResultCode.PWD_ERROR.getCode(), ResultCode.PWD_ERROR.getMessage());
    }

    @ApiOperation(value = "出现sql错误时")
    @ExceptionHandler(SQLException.class)
    public Result handleSQLException(){
        return new Result(ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage());
    }

    @ApiOperation(value = "前端请求文件不存在时")
    @ExceptionHandler(IOException.class)
    public Result handleIOException(){
        return new Result(ResultCode.NOT_FOUND.getCode(), ResultCode.NOT_FOUND.getMessage());
    }

    @ApiOperation(value = "全局异常，返回服务器内部错误")
    @ExceptionHandler(Exception.class)
    public Result handleException(){
        return new Result(ResultCode.SERVER_ERROR.getCode(), ResultCode.SERVER_ERROR.getMessage());
    }



}
