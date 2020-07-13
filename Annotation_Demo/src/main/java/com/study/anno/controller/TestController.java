package com.study.anno.controller;

import com.study.anno.anno.MethodAnno;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-07-13 19:45
 * @Description
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/index")
    @MethodAnno(action = "fun1")
    public String index(String arg1){
        return "";
    }

}
