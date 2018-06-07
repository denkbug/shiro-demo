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
public class RoleController {
    @RequiresPermissions("role:view")
    @RequestMapping("/role")
    public String role() {
        return "role list...";
    }

    @RequiresRoles("admin")
    @RequestMapping("/role/delete")
    public String role_delete() {
        return "role delete...";
    }
}
