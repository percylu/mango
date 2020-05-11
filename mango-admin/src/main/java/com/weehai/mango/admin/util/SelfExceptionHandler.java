package com.weehai.mango.admin.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;

import com.alibaba.fastjson.JSONObject;
import com.weehai.mango.core.http.HttpResult;

public class SelfExceptionHandler implements HandlerExceptionResolver {

	//private static final Logger log= LoggerFactory.getLogger(SelfExceptionHandler.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();

        if ((ex instanceof AuthenticationException) ) {
            modelAndView.getModel().put("msg","登录失败！");
        } else{
            modelAndView.getModel().put("msg","发生未知异常！");
        }
        return modelAndView;
    }
}
