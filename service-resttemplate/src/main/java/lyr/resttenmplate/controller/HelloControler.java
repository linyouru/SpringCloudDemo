package lyr.resttenmplate.controller;

import lyr.resttenmplate.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloControler
 * @Description TODO
 * @Author LYR
 * @Date 2019/5/14 10:55
 * @Version 1.0
 **/
@RestController
public class HelloControler {

    @Autowired
    HelloService helloService;

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/getboodbye")
    public String goodbye(@RequestParam String name) {
        return helloService.hiService( name );
    }

    @RequestMapping("/sayhi")
    public String sayHi(String name){
        return name+" hi,你调用了service-resttemplate的sayHi接口,端口为："+port;
    }


}
