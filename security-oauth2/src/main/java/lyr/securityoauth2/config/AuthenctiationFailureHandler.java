package lyr.securityoauth2.config;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录失败处理类
 * @ClassName AuthenctiationFailureHandler
 * @Description TODO
 * @Author LYR
 * @Date 2019/6/24 14:58
 * @Version 1.0
 **/
@Component("authenctiationFailureHandler")
public class AuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        System.out.println("登录失败");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        JSONObject res = new JSONObject();
        res.put("status","error");
        if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            res.put("msg","用户名或密码输入错误，登录失败!");
        } else if (exception instanceof DisabledException) {
            res.put("msg","账户被禁用，登录失败，请联系管理员!");
        } else {
            res.put("msg","登录失败!");
        }
        out.write(res.toJSONString());
        out.flush();
        out.close();
    }
}
