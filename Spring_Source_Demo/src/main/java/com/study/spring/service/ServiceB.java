package com.study.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-07-08 09:53
 * @Description
 **/
@Component
public class ServiceB {

    @Autowired
    private ServiceA serviceA;

    public ServiceB() {
    }



}
