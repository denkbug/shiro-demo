//package com.denk.demo.filter;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;
//import org.apache.shiro.web.filter.AccessControlFilter;
//import org.apache.shiro.web.util.WebUtils;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author: denk
// * desc:
// * date: 2018/6/7
// */
//public class AnyRolesFilter extends AccessControlFilter {
//    private String unauthorizedUrl = "/page/unauthorized";
//    private String loginUrl = "/page/login";
//
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
//        String[] roles = (String[]) mappedValue;
//        if (roles == null) {
//            return true;//如果没有设置角色参数，默认成功
//        }
//        for (String role : roles) {
//            Subject subject = SecurityUtils.getSubject();
//            if (subject != null && subject.hasRole(role)) {
//                return true;
//            }
//        }
//        return false;//跳到onAccessDenied处理
//    }
//
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        Subject subject = getSubject(request, response);
//        if (subject.getPrincipal() == null) {//表示没有登录，重定向到登录页面
//            WebUtils.issueRedirect(request, response, loginUrl);
//        } else {
//            if (StringUtils.hasText(unauthorizedUrl)) {//如果有未授权页面跳转过去
//                WebUtils.issueRedirect(request, response, unauthorizedUrl);
//            } else {//否则返回401未授权状态码
//                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            }
//        }
//        return false;
//    }
//}
