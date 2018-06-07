package com.denk.demo.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import java.util.Iterator;

/**
 * @author: denk
 * desc:
 * date: 2018/6/5
 */
@Service
public class DemoService {

    @RequiresPermissions("read")
    public String read() {
        String p = "";
        Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection principalCollection = currentUser.getPrincipals();
        Iterator i = principalCollection.iterator();
        while (i.hasNext()) {
            String temp = i.next().toString();
            System.out.println(temp);
            p += temp;
        }
        return "reading...#" + currentUser.getPrincipal().toString() + "#" + p;
    }

    @RequiresPermissions("write")
    public String write() {
        return "writting...";
    }
}
