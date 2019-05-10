package com.lyr.servicegateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceGatewayApplication {

    public static void main(String[] args) {
        System.out.println("Gateway启动");
        SpringApplication.run(ServiceGatewayApplication.class, args);
        System.out.println("Gateway启动成功");
    }

}
