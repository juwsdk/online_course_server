package com.xhu.onlinecourse.controller;

import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.entity.Teacher;
import com.xhu.onlinecourse.entity.loginuser.User;
import com.xhu.onlinecourse.service.LoginRegisterService;
import com.xhu.onlinecourse.utils.JwtUtils;
import com.xhu.onlinecourse.utils.Result;
import com.xhu.onlinecourse.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Api(value = "这是登录业务逻辑")
@RestController
@RequestMapping("/dataCommit")
public class LoginRegisterController {
    @Autowired
    private LoginRegisterService loginRegisterService;

    @ApiOperation(value = "前端获取解析的token信息")
    @RequestMapping(value = "/parseToken", method = RequestMethod.POST)
    public Result<Map<String, Object>> parseToken(@RequestHeader("Authorization") String authorization) {
        // 提取Token信息
        String token = authorization.replace("Bearer ", ""); // 去除Bearer前缀
        Map<String, Object> map = JwtUtils.parseToken(token);
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), map);
    }

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<Map<String, String>> login(@RequestBody User user, HttpServletResponse response) {
        Map<String, String> tokenMap;
        if (loginRegisterService.getUserInfo(user)) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", user.getUserId());
            claims.put("loginType", user.getLoginType());
            if (user.getLoginType().equals("admin")) {
                claims.put("admType", loginRegisterService.getAdminType(user.getUserId()));
            }
            //生成jwt token并登录
            String token = JwtUtils.generateToken(claims);
//            System.out.println(token);
//            System.err.println(JwtUtils.validateToken(token));
            tokenMap = new HashMap<>();
            tokenMap.put("Authorization", token);
        } else
            throw new UnknownAccountException();//无此用户
        return new Result<>(ResultCode.LOGIN_SUCCESS.getCode(), ResultCode.LOGIN_SUCCESS.getMessage(), tokenMap);
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
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Result Logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Result(ResultCode.LOGOUT_SUCCESS.getCode(), ResultCode.LOGOUT_SUCCESS.getMessage());
    }
}
