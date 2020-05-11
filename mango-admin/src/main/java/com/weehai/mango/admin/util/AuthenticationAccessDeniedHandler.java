package com.weehai.mango.admin.util;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.weehai.mango.core.http.HttpResult;
/**
 * @author 卢水柏
 * @company WeeHai
 * @date 2020/4/13 12:31 下午
 * @apiNote 权限异常处理返回
 **/
@Service
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.access.AccessDeniedException accessDeniedException)
			throws IOException, ServletException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code",403);
		map.put("msg", accessDeniedException.getMessage());
		String json = JSONObject.toJSONString(map);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().write(json);
		response.getWriter().flush();
		// TODO Auto-generated method stub
		
	}
}
