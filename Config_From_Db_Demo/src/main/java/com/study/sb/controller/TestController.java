package com.study.sb.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-07-02 16:38
 * @Description
 **/
@RestController
@RequestMapping(value = "/t")
@DependsOn(value = "configBeforeInit")
public class TestController {
    @Value("${test.prop}")
    private String testProp;

    @GetMapping(value = "/test")
    public String test() {
        System.out.println(testProp + "---");
        return "aaa";
    }
}
