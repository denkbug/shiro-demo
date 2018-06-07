//package com.denk.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.web.ErrorAttributes;
//import org.springframework.boot.autoconfigure.web.ErrorController;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Map;
//
///**
// * @author: denk
// * desc:
// * date: 2018/6/5
// */
//@Controller
//public class CustomErrorController implements ErrorController {
//    private static final String ERROR_PATH = "/error";
//
//    @Autowired
//    private ErrorAttributes errorAttributes;
//
//    @Override
//    public String getErrorPath() {
//        return ERROR_PATH;
//    }
//
//    @RequestMapping("/error")
//    String error(HttpServletRequest request, Model model) {
//        Map<String, Object> errorMap = errorAttributes.getErrorAttributes(new ServletRequestAttributes(request), false);
//        model.addAttribute("errors", errorMap);
//        return "error";
//    }
//}
