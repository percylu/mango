package com.weehai.mango.admin.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.weehai.mango.admin.util.SelfExceptionHandler;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        //和页面有关的静态目录都放在项目的static目录下
	        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	        //上传的图片在D盘下的OTA目录下，访问路径如：http://localhost:8081/OTA/d3cf0281-bb7f-40e0-ab77-406db95ccf2c.jpg
	        //其中OTA表示访问的前缀。"file:D:/OTA/"是文件真实的存储路径
	        registry.addResourceHandler("/upload/**").addResourceLocations("file:/users/percylu/upload/");
	        registry.addResourceHandler("/swagger-ui.html")
            	.addResourceLocations("classpath:/META-INF/resources/","/static", "/public");

	        registry.addResourceHandler("/webjars/**")
            	.addResourceLocations("classpath:/META-INF/resources/webjars/");
	        
	        

	    }
	 
	 @Override
		public void addCorsMappings(CorsRegistry registry) {
			
			registry.addMapping("/*").allowedOrigins("*");

		}
//
//	 @Override
//     public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers){
//         exceptionResolvers.add(new SelfExceptionHandler());
//     }


}
