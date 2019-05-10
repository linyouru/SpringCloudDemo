package com.lyr.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class ConfigServerApplication {

    public static void main(String[] args) {
        System.out.println("ConfigServer启动");
        SpringApplication.run(ConfigServerApplication.class, args);
        System.out.println("ConfigServer启动成功");
    }

}
