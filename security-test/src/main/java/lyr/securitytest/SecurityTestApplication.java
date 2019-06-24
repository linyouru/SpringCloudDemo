package lyr.securitytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName SecurityOauth2Application
 * @Description TODO
 * @Author LYR
 * @Date 2019/6/12 14:38
 * @Version 1.0
 **/
@SpringBootApplication
public class SecurityTestApplication {

    public static void main(String[] args) {
        System.out.println("Security-Test启动...");
        SpringApplication.run( SecurityTestApplication.class, args );
        System.out.println("Security-Test启动成功");
    }

}
