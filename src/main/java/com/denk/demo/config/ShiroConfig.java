package com.denk.demo.config;

//import com.denk.demo.realm.CustomRealm;

//import com.denk.demo.dao.RedisShiroSessionDao;
//import com.denk.demo.filter.AnyRolesFilter;

import com.denk.demo.realm.MongoRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: denk
 * desc:
 * date: 2018/6/5
 */
@Configuration
public class ShiroConfig {

//    @Bean(name = "customRealm")
//    public CustomRealm customRealm() {
//        return new CustomRealm();
//    }

    @Bean(name = "mongoRealm")
    public MongoRealm mongoRealm() {
        return new MongoRealm();
    }

//    @Bean("sessionManager")
//    public SessionManager sessionManager(RedisShiroSessionDao redisShiroSessionDao) {
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        //设置session过期时间为1小时(单位：毫秒)，默认为30分钟
//        sessionManager.setGlobalSessionTimeout(60 * 60 * 1000);
//        sessionManager.setSessionValidationSchedulerEnabled(true);
//        sessionManager.setSessionIdUrlRewritingEnabled(false);
//
//        //如果开启redis缓存且renren.shiro.redis=true，则shiro session存到redis里
//        if (true && true) {
//            sessionManager.setSessionDAO(redisShiroSessionDao);
//        }
//        return sessionManager;
//    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultSecurityManager() {
        List<Realm> realms = new ArrayList<>();
//        realms.add(customRealm());
        realms.add(mongoRealm());
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealms(realms);
        SecurityUtils.setSecurityManager(defaultWebSecurityManager);
        return defaultWebSecurityManager;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

//    @Bean(name = "anyRolesFilter")
//    public AnyRolesFilter anyRolesFilter() {
//        return new AnyRolesFilter();
//    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

//        Map<String, String> filterMap = new LinkedHashMap<>();
//        filterMap.put("/role/**", "anyRolesFilter[abcde]");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
}
