package com.casey.wj.config;
/*
 * @author CaseyL
 * @date 2022/10/1 23:18
 * */

import com.casey.wj.filter.URLPathMatchingFilter;
import com.casey.wj.service.AdminPermissionService;
import com.casey.wj.service.AdminRoleService;
import com.casey.wj.service.UserService;
import com.casey.wj.shiro.WJRealm;
import lombok.AllArgsConstructor;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class ShiroConfigurer {
//    private WJRealm wjRealm;

    public final UserService userService;
    private final AdminPermissionService permissionService;
    private final AdminRoleService roleService;

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    public URLPathMatchingFilter getURLPathMatchingFilter() {
        return new URLPathMatchingFilter();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, String > filterChainDefinitionMap = new LinkedHashMap<String, String>();
        Map<String, Filter> customizedFilter = new HashMap<>();

        URLPathMatchingFilter urlPathMatchingFilter = getURLPathMatchingFilter();
        System.out.println("----------------filter: " + urlPathMatchingFilter);
        // 设置自定义过滤器名称为 url
        customizedFilter.put("url", urlPathMatchingFilter);

        // 对管理接口的访问启用自定义拦截（url 规则），即执行 URLPathMatchingFilter 中定义的过滤方法
        filterChainDefinitionMap.put("/api/admin/**", "url");
        // 启用自定义过滤器
        shiroFilterFactoryBean.setFilters(customizedFilter);
//        filterChainDefinitionMap.put("/api/authentication", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }



//    @Bean
//    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//
//        Map<String, Filter> customizedFilter = new HashMap<>();
//        customizedFilter.put("url", getURLPathMatchingFilter());
//        shiroFilterFactoryBean.setFilters(customizedFilter);
//        System.out.println("customizedFilter: " + customizedFilter);
//
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
////        filterChainDefinitionMap.put("/api/admim/**", "url");
//        filterChainDefinitionMap.put("/api/admim/**", "authc");
////        filterChainDefinitionMap.put("/api/authentication", "authc");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//
//
//        return shiroFilterFactoryBean;
//    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getWJRealm());
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean
    public WJRealm getWJRealm() {
        WJRealm wjRealm = new WJRealm(userService, permissionService, roleService);
        wjRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return wjRealm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey("CASEYLEONG_ADMIN".getBytes());
        //
        return cookieRememberMeManager;
    }

    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }


}
