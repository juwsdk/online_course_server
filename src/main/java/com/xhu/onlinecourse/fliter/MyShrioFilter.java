package com.xhu.onlinecourse.fliter;

import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyShrioFilter extends PassThruAuthenticationFilter {
    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse res= (HttpServletResponse) response;
        //复杂请求会带options请求，直接放行
        if(req.getMethod().equals(RequestMethod.OPTIONS.name()))
            return true;
        return super.onPreHandle(request, response, mappedValue);
    }
}
