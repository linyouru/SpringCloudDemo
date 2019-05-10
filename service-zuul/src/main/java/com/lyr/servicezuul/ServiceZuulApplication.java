package com.lyr.servicezuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ServiceZuulApplication {

    public static void main(String[] args) {
        System.out.println("Zuul启动");
        SpringApplication.run(ServiceZuulApplication.class, args);
        System.out.println("Zuul启动成功");
    }

}
