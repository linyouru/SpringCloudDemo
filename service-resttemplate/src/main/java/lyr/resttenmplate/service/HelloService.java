package lyr.resttenmplate.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName HelloService
 * @Description TODO
 * @Author LYR
 * @Date 2019/5/14 10:54
 * @Version 1.0
 **/
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getGoodbyeError")
    public String hiService(String name) {
        return restTemplate.getForObject("http://service-feign/saygoodbye?name="+name,String.class);
    }

    public String getGoodbyeError(String name) {
        return name+",调用service-feign的saygoodbye接口失败了";
    }

}
