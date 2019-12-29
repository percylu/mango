package com.weehai.mango.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 卢水柏
 * @company WeeHai
 * @date 2019/12/29 12:56 下午
 **/
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    @Autowired
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

    }
}
