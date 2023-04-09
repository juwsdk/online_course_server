package com.xhu.onlinecourse.controller;

import com.xhu.onlinecourse.utils.Result;
import com.xhu.onlinecourse.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "这是登录业务逻辑")
@RestController
@RequestMapping("/dataCommit")
public class LoginRegisterController {
    @ApiOperation(value = "登录")
    @RequestMapping("/login")
    public Result<String> login() {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), new String());
    }

    @ApiOperation(value = "注册")
    @RequestMapping("/register")
    public Result<String> register() {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), new String());
    }
}
