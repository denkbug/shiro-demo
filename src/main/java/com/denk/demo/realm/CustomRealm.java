//package com.denk.demo.realm;
//
//import com.denk.demo.dao.DemoDao;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Set;
//
///**
// * @author: denk
// * desc:
// * date: 2018/6/5
// */
//public class CustomRealm extends AuthorizingRealm {
//
//    @Autowired
//    private DemoDao shiroSampleDao;
//
//    /**
//     * 认证
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        String username = token.getUsername();
//        String password = this.shiroSampleDao.getPasswordByUsername(username);
//        if (password == null)
//            return null;
//        return new SimpleAuthenticationInfo(username, password, getName());
//    }
//
//    /**
//     * 授权
//     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        String username = (String) super.getAvailablePrincipal(principalCollection);
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        Set<String> roles = shiroSampleDao.getRolesByUsername(username);
//        authorizationInfo.setRoles(roles);
//        roles.forEach(role -> {
//            Set<String> permissions = this.shiroSampleDao.getPermissionsByRole(role);
//            authorizationInfo.addStringPermissions(permissions);
//        });
//        return authorizationInfo;
//    }
//}
