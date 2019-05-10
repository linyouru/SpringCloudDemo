package com.lyr.servicefeign;

import org.springframework.stereotype.Component;

/**
 * @ClassName SchedualServiceHiHystric
 * @Description TODO
 * @Author LYR
 * @Date 2019/4/28 14:36
 * @Version 1.0
 **/
@Component
public class SchedualServiceHiHystric implements ServiceFeignApplication.SchedualServiceHi {

    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry,"+name;
    }

}
