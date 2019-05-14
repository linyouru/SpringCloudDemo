package lyr.servicefeign.controller;

import lyr.servicefeign.othersercive.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName lyr.servicefeign.controller.HiController
 * @Description TODO
 * @Author LYR
 * @Date 2019/4/28 9:39
 * @Version 1.0
 **/
@RestController
public class HiController {

    @Resource
    SchedualServiceHi schedualServiceHi;

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/gethi")
    public String sayHi(String name){
        return schedualServiceHi.sayHiFromClientOne(name);
    }

    @RequestMapping("/saygoodbye")
    public String saygoodbye(String name){
        return name+" goodbye,你调用了service-feign的saygoodbye接口,端口为："+port;
    }

}
