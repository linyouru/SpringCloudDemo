package lyr.securityoauth2.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException {

        System.out.println("登录成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(authentication));

    }
}
