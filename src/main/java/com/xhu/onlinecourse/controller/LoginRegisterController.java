package com.xhu.onlinecourse.controller;

import com.xhu.onlinecourse.entity.loginuser.User;
import com.xhu.onlinecourse.entity.loginuser.UsernamePasswordTypeToken;
import com.xhu.onlinecourse.utils.Result;
import com.xhu.onlinecourse.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "这是登录业务逻辑")
@RestController
@RequestMapping("/dataCommit")
public class LoginRegisterController {
    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestBody User user) {
        //获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("loginType",user.getLoginType());//从前端拿取的loginType作为角色的凭证
        AuthenticationToken token = new UsernamePasswordTypeToken(String.valueOf(user.getUserId()),user.getPassword(),user.getLoginType());
        subject.login(token);
        return new Result(ResultCode.LOGIN_SUCCESS.getCode(), ResultCode.LOGIN_SUCCESS.getMessage());
    }

    @ApiOperation(value = "注册")
    @RequestMapping("/register")
    public Result<String> register() {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), new String());
    }

    @ApiOperation(value = "注销")
    @RequestMapping("/logout")
    public Result Logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Result(ResultCode.LOGOUT_SUCCESS.getCode(), ResultCode.LOGOUT_SUCCESS.getMessage());
    }
}
