package com.denk.demo.advice;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: denk
 * desc:
 * date: 2018/6/5
 */
@ControllerAdvice
public class ShiroAdvice {

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public String handleException(UnauthenticatedException e, Model model) {
        System.out.println("UnauthenticatedException####" + e.getMessage());
        Map<String, Object> map = new HashMap<>();
        map.put("status", 000);
        map.put("message", "未登录");
        return map.toString();
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public String handleException(AuthorizationException e, Model model) {
        // you could return a 404 here instead (this is how github handles 403, so the user does NOT know there is a
        // resource at that location)
        System.out.println("AuthorizationException####" + e.getMessage());
        Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.NOT_FOUND);
        map.put("message", "权限验证失败");
        return map.toString();
    }

    @ExceptionHandler({IncorrectCredentialsException.class, UnknownAccountException.class})
    @ResponseBody
    public String handleIncorrectCredentialsException(IncorrectCredentialsException e, Model model) {
        System.out.println("IncorrectCredentialsException####" + e.getMessage());
        Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.FORBIDDEN);
        map.put("message", "用户名或密码错误");
        return map.toString();
    }
}
