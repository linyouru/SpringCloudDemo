package lyr.securityoauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
public class SecurityOauth2Application {

    public static void main(String[] args) {
        System.out.println("Security-Oauth2启动...");
        SpringApplication.run( SecurityOauth2Application.class, args );
        System.out.println("Security-Oauth2启动成功");
    }

}
