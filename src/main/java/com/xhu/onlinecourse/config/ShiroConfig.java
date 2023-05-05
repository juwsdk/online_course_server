package com.xhu.onlinecourse.config;

import com.xhu.onlinecourse.fliter.MyShrioFilter;
import com.xhu.onlinecourse.realm.MyRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
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

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        //设置 DefaultWebSecurityManager对象
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm);
        return defaultWebSecurityManager;
    }


    //配置 Shiro 内置过滤器拦截范围
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //配置过滤器
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        MyShrioFilter myShrioFilter = new MyShrioFilter();
        filters.put("authc", myShrioFilter);
        shiroFilterFactoryBean.setFilters(filters);
        // 设置过滤器链
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/dataCommit/login", "anon");
        filterChainDefinitionMap.put("/dataCommit/register/teacher", "anon");
        filterChainDefinitionMap.put("/dataCommit/register/student", "anon");
        filterChainDefinitionMap.put("/dataCommit/logout", "anon");
        filterChainDefinitionMap.put("/swagger-ui/#/**", "anon");
        filterChainDefinitionMap.put("/durid/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }



}
