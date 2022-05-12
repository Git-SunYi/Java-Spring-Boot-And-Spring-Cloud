package com.qdc.demoeurekaconsumserfeign.service;

import org.springframework.stereotype.Component;

@Component
public class FeignClientFallback implements UserClient{
    @Override
    public String hello() {
        return null;
    }

    @Override
    public String hello(int sleep_seconds) {
        return "Hi,容错保护机制已经启动了！";
    }
}
