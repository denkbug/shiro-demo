package com.denk.demo.controller;

import com.denk.demo.service.DemoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: denk
 * desc:
 * date: 2018/6/5
 */
@RestController
public class DemoController {

    @Autowired
    private DemoService shiroSampleService;

    @GetMapping("/login")
    public String login(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);//存cookie
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);
        return currentUser.getPrincipals().toString() + "#login success~#" + currentUser.isAuthenticated();
    }

    @GetMapping("/logout")
    public String logout() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser != null && currentUser.getPrincipals() != null) {
            String uname = currentUser.getPrincipals().toString();
            currentUser.logout();
            return uname + "#logout success~#" + currentUser.isAuthenticated();
        }
        return "logout fail, no user online";
    }


    @RequiresRoles("admin")
    @GetMapping("/read")
    public String read() {
        return this.shiroSampleService.read();
    }

    @GetMapping("/write")
    public String write() {
        return this.shiroSampleService.write();
    }
}
