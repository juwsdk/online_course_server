package com.xhu.onlinecourse.config;

import com.xhu.onlinecourse.realm.MyRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    @Autowired
    private MyRealm myRealm;

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        //设置 DefaultWebSecurityManager对象
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm);
        return defaultWebSecurityManager;
    }
    //配置 Shiro 内置过滤器拦截范围
    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        //设置不认证可以访问的资源
//        definition.addPathDefinition("/myController/userLogin", "anon");
        definition.addPathDefinition("/dataCommit/login", "anon");
        //配置登出过滤器
        definition.addPathDefinition("/dataCommit/register", "anon");
        //配置登出过滤器
        definition.addPathDefinition("/dataCommit/logout", "anon");
        //设置需要进行登录认证的拦截范围
        definition.addPathDefinition("/**", "authc");

        return definition;
    }
}
