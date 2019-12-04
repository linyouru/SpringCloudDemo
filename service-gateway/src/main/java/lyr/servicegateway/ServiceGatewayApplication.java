package lyr.servicegateway;

import lyr.servicegateway.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class ServiceGatewayApplication {

    public static void main(String[] args) {
        System.out.println("Gateway启动");
        SpringApplication.run(ServiceGatewayApplication.class, args);
        System.out.println("Gateway启动成功");
    }

    @Bean
    public TokenFilter tokenFilter(){
        return new TokenFilter();
    }

}
