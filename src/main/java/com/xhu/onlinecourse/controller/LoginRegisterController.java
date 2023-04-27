package com.xhu.onlinecourse.controller;

import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.entity.Teacher;
import com.xhu.onlinecourse.entity.loginuser.User;
import com.xhu.onlinecourse.entity.loginuser.UsernamePasswordTypeToken;
import com.xhu.onlinecourse.entity.loginuser.User;
import com.xhu.onlinecourse.service.LoginRegisterService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "这是登录业务逻辑")
@RestController
@RequestMapping("/dataCommit")
public class LoginRegisterController {
    @Autowired
    private LoginRegisterService loginRegisterService;

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody User user) {
        //获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("loginType",user.getLoginType());//从前端拿取的loginType作为角色的凭证
//        subject.getSession().setAttribute("userId",user.getLoginType());
        AuthenticationToken token = new UsernamePasswordTypeToken(String.valueOf(user.getUserId()),user.getPassword(),user.getLoginType());
        subject.login(token);
        if(user.getLoginType().equals("admin")){
            return new Result(ResultCode.LOGIN_SUCCESS.getCode(), ResultCode.LOGIN_SUCCESS.getMessage(),loginRegisterService.getAdminType(user.getUserId()));
        }
        /*Map<Long, String> userInfo = loginRegisterService.getUserInfo(Long.valueOf(user.getUserId()), user.getLoginType());
        if (userInfo.size() == 0) {
            return new Result(ResultCode.USER_ERROR.getCode(), ResultCode.USER_ERROR.getMessage());
        } else if (userInfo.get(user.getUserId()) == null) {
            return new Result(ResultCode.PWD_ERROR.getCode(), ResultCode.USER_ERROR.getMessage());
        }*/
        return new Result(ResultCode.LOGIN_SUCCESS.getCode(), ResultCode.LOGIN_SUCCESS.getMessage());
    }

    @ApiOperation(value = "教师注册")
    @RequestMapping(value = "/register/teacher", method = RequestMethod.POST)
    public Result<Integer> register(@RequestBody Teacher teacher) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), loginRegisterService.register(teacher, "teacher"));
    }

    @ApiOperation(value = "学生注册")
    @RequestMapping(value = "/register/student", method = RequestMethod.POST)
    public Result<Integer> stuRegister(@RequestBody Student student) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), loginRegisterService.register(student, "student"));
    }

    @ApiOperation(value = "注销")
    @RequestMapping("/logout")
    public Result Logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Result(ResultCode.LOGOUT_SUCCESS.getCode(), ResultCode.LOGOUT_SUCCESS.getMessage());
    }
}
