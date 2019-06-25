package lyr.securityserver.config;

import lyr.securityserver.entity.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * @ClassName SecurityConfiguration
 * @Description TODO
 * @Author LYR
 * @Date 2019/6/12 10:28
 * @Version 1.0
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    private AuthenctiationFailureHandler authenctiationFailureHandler;

    @Resource
    private AuthenctiationSuccessHandler authenctiationSuccessHandler;

    @Resource
    private UserDetailServiceImpl userDetailService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()                                // 定义当需要用户登录时候，转到的登录页面。
                .loginPage("/authentication/require")       // 设置登录页面
                .loginProcessingUrl("/login.do")          // 自定义的登录接口
                .successHandler(authenctiationSuccessHandler)     //登陆成功处理
                .failureHandler(authenctiationFailureHandler)     //登陆失败处理
//                .defaultSuccessUrl("/index.html")       // 登录成功之后，默认跳转的页面
                .permitAll()
                .and().authorizeRequests()                  // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/login.html").permitAll()       // 设置所有人都可以访问登录页面
                .anyRequest().authenticated()               // 任何请求,登录后可以访问
                .and().csrf().disable();                    // 关闭csrf防护
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**/*", "/**/*.css", "/**/*.js");
    }

}
