package com.bs.union.midware.config;


import com.bs.union.midware.auth.handler.LogoutHandlerImpl;
import com.bs.union.midware.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;



/**
 * @Author:zrt
 * @Date:2021/1/1 22:45
 * @version:1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userDetailsService;
    //登录成功后处理
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    //登录失败处理
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private LogoutHandlerImpl logoutHandler;

    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;
    // 身份验证入口点失败处理
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // 配置跨域资源共享
                .cors()
                .and()
                // 授权请求
                .authorizeRequests()
                // 访问 /book/** 接口的请求必须具备 admin 角色
                .antMatchers("/api/**").hasRole("admin")
                // 访问 /brandlist/** 接口的请求必须具备 dba 角色
                .antMatchers("/brandlist/**").hasRole("dba")
                // 放行其他接口的请求
                .anyRequest().permitAll()
                .and()
                // 登录接口的Url 可通过发起Post 请求进行登录
                .formLogin().loginProcessingUrl("/login")
                // 登录成功处理
                .successHandler(authenticationSuccessHandler)
                // 登录失败处理
                .failureHandler(authenticationFailureHandler)
                .and()
                // 注销接口 默认Url 为/logout 可自定义
                .logout()
                // 注销处理
                .addLogoutHandler(logoutHandler);
    }
    /**
     * 身份验证器
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        });
    }

}
