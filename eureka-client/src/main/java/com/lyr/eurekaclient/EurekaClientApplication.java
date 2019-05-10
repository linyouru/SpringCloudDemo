package com.lyr.eurekaclient;

import com.netflix.discovery.DiscoveryManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaClientApplication {

    public static void main(String[] args) {
        System.out.println("EurekaClien启动");
        SpringApplication.run(EurekaClientApplication.class, args);
        System.out.println("EurekaClien启动完毕");
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }

    //客户端主动通知注册中心下线
    @RequestMapping(value = "/offline", method = RequestMethod.GET)
    public void offLine(){
        DiscoveryManager.getInstance().shutdownComponent();
    }

}
