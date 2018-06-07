package com.denk.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: denk
 * desc:
 * date: 2018/6/7
 */
@RestController
@RequestMapping("/page")
public class PageController {
    @RequestMapping("/login")
    public String login() {
        return "登录页面";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized() {
        return "未授权页面";
    }
}
