package com.qdc.demoeurekaauth_server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@EnableWebSecurity
@Component
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    //用户详情服务
    @Autowired
    private UserDetailsService userDetailsService;
    //密码编码格式
//    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    //使用BCrypt加密
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //授权方式提供者
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setHideUserNotFoundExceptions(false);
        return authenticationProvider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/css/**","/js/**","/fonts/**","/icon/**","/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.requestMatchers().antMatchers("/login","/login-error",
                "/oauth/authorize","/oauth/token","/api/userinfo").and().
                authorizeRequests().antMatchers("/login").permitAll().
                anyRequest().authenticated();
        //登录页面
        httpSecurity.formLogin().loginPage("/login").failureUrl("/login-error");
        //禁用CSRF
        httpSecurity.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        //设置授权方式
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
    }
}
