package com.denk.demo.realm;

import com.denk.demo.dao.ShiroRoleDao;
import com.denk.demo.dao.ShiroUserDao;
import com.denk.demo.model.ShiroRole;
import com.denk.demo.model.ShiroUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: denk
 * desc:
 * date: 2018/6/6
 */
public class MongoRealm extends AuthorizingRealm {

    @Autowired
    private ShiroUserDao shiroUserDao;
    @Autowired
    private ShiroRoleDao shiroRoleDao;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();  //得到用户名
        String password = new String((char[]) token.getCredentials()); //得到密码
        ShiroUser shiroUser = shiroUserDao.findByUnameAndUpass(username, password);
        if (shiroUser == null) {//用户名或密码错误，统一抛出IncorrectCredentialsException
            throw new IncorrectCredentialsException();
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username, password, getName());

//        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                user.getUsername(), //用户名
//                user.getPassword(), //密码
//                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
//                getName()  //realm name
//        );
//        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) super.getAvailablePrincipal(principals);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        ShiroUser shiroUser = shiroUserDao.findByUname(username);
        if (shiroUser == null) {
            throw new AuthorizationException();
        }
        Set<String> roles = new HashSet<>(shiroUser.getRoles());//从数据库获取到用户对应的角色
        authorizationInfo.setRoles(roles);
        roles.forEach(role -> {
            ShiroRole shiroRole = shiroRoleDao.findOne(role);
            if (shiroRole == null) {
                throw new AuthorizationException();
            }
            authorizationInfo.addStringPermissions(shiroRole.getPermissions());
        });
        return authorizationInfo;
    }
}
