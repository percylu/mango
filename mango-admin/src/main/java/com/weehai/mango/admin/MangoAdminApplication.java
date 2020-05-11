package com.weehai.mango.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.weehai.mango.admin"})
@MapperScan("com.weehai.mango.admin.dao")
public class MangoAdminApplication {
    private static final Logger logger = LoggerFactory.getLogger(MangoAdminApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MangoAdminApplication.class, args);
        logger.info("========================启动完毕========================");

    }

}
