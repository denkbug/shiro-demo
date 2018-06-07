package com.denk.demo.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: denk
 * desc:
 * date: 2018/6/6
 */
@RestController
public class UserController {
    @RequiresPermissions("user:view")
    @RequestMapping("/user")
    public String user() {
        return "user list...";
    }

    @RequiresRoles("root")
    @RequestMapping("/user/delete")
    public String user_delete() {
        return "user delete...";
    }
}
