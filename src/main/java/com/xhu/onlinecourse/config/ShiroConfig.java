package com.xhu.onlinecourse.config;

import com.xhu.onlinecourse.fliter.JwtFilter;
import com.xhu.onlinecourse.realm.MyRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/*
shrio配置
 */
@Configuration
public class ShiroConfig {
    @Autowired
    private MyRealm myRealm;

    @Bean//关闭session
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(false); // 关闭会话验证调度器
        sessionManager.setSessionIdUrlRewritingEnabled(false); // 禁用URL重写方式
        sessionManager.setSessionIdCookieEnabled(false); // 禁用Session ID Cookie
        return sessionManager;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        //设置 DefaultWebSecurityManager对象
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm);
        defaultWebSecurityManager.setSessionManager(sessionManager());//设置为自定义的session管理
        return defaultWebSecurityManager;
    }

//    @Bean//受到限制的都要经过jwt的这个映射
//    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition filterChainDefinition = new DefaultShiroFilterChainDefinition();
//        //创建受限资源映射
//        Map<String, String> filterChainMap = new LinkedHashMap<>();
//        //所有资源都要经过名为jwt映射的过滤器
//        filterChainMap.put("/**", "jwt");
//        //把受限资源映射加入过滤器链定义
//        filterChainDefinition.addPathDefinitions(filterChainMap);
//        return filterChainDefinition;
//    }

    @Bean//配置 Shiro 内置过滤器拦截范围
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //配置过滤器
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
//        MyShrioFilter myShrioFilter = new MyShrioFilter();
        JwtFilter jwtFilter = new JwtFilter();
        filters.put("authc", jwtFilter);//创建jwt的拦截器
        shiroFilterFactoryBean.setFilters(filters);
        // 设置过滤器链
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/dataCommit/parseToken", "anon");
        filterChainDefinitionMap.put("/dataCommit/login", "anon");
        filterChainDefinitionMap.put("/dataCommit/register/teacher", "anon");
        filterChainDefinitionMap.put("/dataCommit/register/student", "anon");
        filterChainDefinitionMap.put("/dataCommit/logout", "anon");
        filterChainDefinitionMap.put("/swagger-ui/#/**", "anon");
        filterChainDefinitionMap.put("/durid/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");//authc
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }



}
