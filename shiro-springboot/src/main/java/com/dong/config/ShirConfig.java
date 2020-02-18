package com.dong.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author dongjian
 * @Date 2020/2/16 15:36
 * @Description:
 * @Version:1.0
 */
@Configuration
public class ShirConfig {

    /**
     * 3.ShiroFilterFactoryBean 工程类
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        // 添加shiro的内置过滤器
        /*
        anno:无需任何认证就可访问
        authc:必须认证才可访问
        user:必须拥有 记住我 才可访问
        perms:拥有某些资源才可访问
        role:拥有某些角色才可访问
         */
        //拦截
        Map<String, String> filterMap = new LinkedHashMap<>();
        //        filterMap.put("/user/add","authc");
        //        filterMap.put("/index","authc");
        //        filterMap.put("/user/update","authc");
        // 授权
        filterMap.put("/user/add","perms[add]");
        filterMap.put("/user/update","perms[update]");
        bean.setFilterChainDefinitionMap(filterMap);
        // 设置登录页面
        bean.setLoginUrl("/user/login");
        // 未授权页面
        bean.setUnauthorizedUrl("/unauth");
        return bean;
    }

    /**
     * 2.DefaultWebSecurityManager
     * @param userRealm
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     *  1.创建Realm对象  自定义类
     * @return
     */
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    /**
     * 4 创建shiroDialect：整合shiro-thymeleaf
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
