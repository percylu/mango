package com.weehai.mango.admin.util;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.weehai.mango.core.http.HttpResult;
import com.weehai.mango.core.http.HttpStatus;
@Service
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		 	response.setCharacterEncoding("UTF-8");
	        response.setContentType("application/json; charset=utf-8");
	        PrintWriter out = response.getWriter();
	        int status=response.getStatus();
	        out.write(JSONObject.toJSONString(HttpResult.error(HttpStatus.SC_FORBIDDEN, "权限不足")));

		// TODO Auto-generated method stub
		//response.getWriter().print(JSONObject.toJSONString(HttpResult.error(authException.getMessage())));
		

	}

}
