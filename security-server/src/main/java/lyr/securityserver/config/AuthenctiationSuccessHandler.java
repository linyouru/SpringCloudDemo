package lyr.securityserver.config;

import com.alibaba.fastjson.JSONObject;
import lyr.securityserver.utils.RedisUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

/**
 * 成功登录处理类
 * @ClassName AuthenctiationSuccessHandler
 * @Description TODO
 * @Author LYR
 * @Date 2019/6/24 14:56
 * @Version 1.0
 **/
@Component("authenctiationSuccessHandler")
public class AuthenctiationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException {

        //用户登陆成功，返回状态码和token，并以token为key，将用户信息存放至rides中
        JSONObject res = new JSONObject();
        PrintWriter out = response.getWriter();
        String token = UUID.randomUUID().toString();
        response.setContentType("application/json;charset=UTF-8");
        System.out.println("登录成功");

        //将用户信息存入token
        redisUtil.set(token,"admin");

        res.put("status",0);
        res.put("token",token);
        out.write(res.toJSONString());
        out.flush();
        out.close();
    }
}
