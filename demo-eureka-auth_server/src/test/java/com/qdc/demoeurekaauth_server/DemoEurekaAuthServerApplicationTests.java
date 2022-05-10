package com.qdc.demoeurekaauth_server;

import com.alibaba.druid.pool.DruidDataSource;
import com.qdc.demoeurekaauth_server.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootTest
class DemoEurekaAuthServerApplicationTests {

    @Autowired
    private DruidDataSource druidDataSource;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    void contextLoads() {
        System.out.println(druidDataSource);
    }

    @Test
    void contextLoads2() {
        System.out.println(userService.getUser("admin"));
    }

    @Test
    void contextLoads3() {
        System.out.println(userDetailsService.loadUserByUsername("admin"));
    }

}
