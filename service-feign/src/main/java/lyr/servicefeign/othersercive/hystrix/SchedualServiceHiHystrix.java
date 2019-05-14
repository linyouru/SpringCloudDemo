package lyr.servicefeign.othersercive.hystrix;

import lyr.servicefeign.othersercive.SchedualServiceHi;
import org.springframework.stereotype.Component;

/**
 * @ClassName lyr.servicefeign.othersercive.hystrix.SchedualServiceHiHystric
 * @Description TODO
 * @Author LYR
 * @Date 2019/4/28 14:36
 * @Version 1.0
 **/
@Component
public class SchedualServiceHiHystrix implements SchedualServiceHi {

    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry,"+name;
    }

}
