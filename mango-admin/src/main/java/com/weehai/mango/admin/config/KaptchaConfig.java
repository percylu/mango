package com.weehai.mango.admin.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 验证码配置
 * @author 卢水柏
 * @company WeeHai
 * @date 2019/12/27 11:30 下午
 **/
@Configuration
public class KaptchaConfig {
    @Bean
    public DefaultKaptcha producer(){
        Properties properties=new Properties();
        properties.put("kaptcha.border","no");
        properties.put("kaptcha.textproducer.font.color","black");
        properties.put("kaptch.textproducer.char.space","5");
        Config config=new Config(properties);
        DefaultKaptcha defaultKaptcha=new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;

    }
}
