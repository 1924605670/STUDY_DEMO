package com.study.designmode.strategy;

import org.springframework.web.bind.annotation.*;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-05-07 14:55
 * @Description
 **/
@RestController
@RequestMapping("/s")
public class StrategyController {

    @GetMapping(value = "/test/{classType}")
    public String test(@PathVariable("classType") String classType) {
        if (classType.equals("1")) {
            BeanA beanA = new BeanA();
            StrategyFactory.getRealImpl(beanA.getClass().getName()).fun1(beanA);
        } else {
            BeanB beanB = new BeanB();
            StrategyFactory.getRealImpl(beanB.getClass().getName()).fun1(beanB);
        }
        return "";
    }
}
