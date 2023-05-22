package com.xhu.onlinecourse.fliter;


import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xhu.onlinecourse.entity.loginuser.JwtToken;
import com.xhu.onlinecourse.utils.JwtUtils;
import com.xhu.onlinecourse.utils.Result;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.xhu.onlinecourse.utils.ResultCode.UNAUTHORIZED;


@Component
public class JwtFilter extends AuthenticatingFilter {

    @Override//实现登录，生成自定义支持的jwt_token
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //从请求头中获取用户携带的token
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");
        }
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        //作为形参传到自定义realm中的doGetAuthenticationInfo方法
        return new JwtToken(token);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //从请求头中获取用户携带的token
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");
        }
        System.out.println(token);
        //验证token合法性
        boolean isAccess = JwtUtils.validateToken(token);
        System.err.println(isAccess);
        if (!isAccess)
            throw new ExpiredCredentialsException("token已过期，请重新登录");
        boolean b = executeLogin(servletRequest, servletResponse);
        System.out.println("登陆的结果是" + b);
        return b;//执行shiro的登录
    }

    @Override//当登录失败/没有权限时
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse res = (HttpServletResponse) response;

        Throwable throwable = e.getCause() == null ? e : e.getCause();

        //封装错误信息的结果体
        Result resultBody = new Result<>(UNAUTHORIZED.getCode(), UNAUTHORIZED.getMessage());//401

        try {
            String json = new ObjectMapper().writeValueAsString(resultBody);
            //把结果体写入响应流
            res.getWriter().write(json);
            res.setStatus(401);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override//处理跨域
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(org.springframework.http.HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
