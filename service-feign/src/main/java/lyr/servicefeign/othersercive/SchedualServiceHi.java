package lyr.servicefeign.othersercive;

import lyr.servicefeign.othersercive.hystrix.SchedualServiceHiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName SchedualServiceHi
 * @Description TODO
 * @Author LYR
 * @Date 2019/5/14 9:48
 * @Version 1.0
 **/
@FeignClient(value = "service-resttemplate",fallback = SchedualServiceHiHystrix.class) //指定调用的服务
public interface SchedualServiceHi {

    @RequestMapping(value = "/sayhi")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);


}
