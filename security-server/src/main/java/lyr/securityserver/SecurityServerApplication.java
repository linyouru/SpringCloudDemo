package lyr.securityserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName SecurityOauth2Application
 * @Description TODO
 * @Author LYR
 * @Date 2019/6/12 14:38
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableCaching  //开启缓存功能
public class SecurityServerApplication {

    public static void main(String[] args) {
        System.out.println("Security-Server启动...");
        SpringApplication.run( SecurityServerApplication.class, args );
        System.out.println("Security-Server启动成功");
    }

}
