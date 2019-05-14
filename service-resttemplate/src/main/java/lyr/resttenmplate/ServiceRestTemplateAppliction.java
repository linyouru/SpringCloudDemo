package lyr.resttenmplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName ServiceRestTemplateAppliction
 * @Description TODO
 * @Author LYR
 * @Date 2019/5/14 10:51
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class ServiceRestTemplateAppliction {
    public static void main(String[] args) {
        SpringApplication.run( ServiceRestTemplateAppliction.class, args );
        System.out.println("service-resttemplate启动成功");
    }

    @Bean
    @LoadBalanced //开启负载均衡
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
