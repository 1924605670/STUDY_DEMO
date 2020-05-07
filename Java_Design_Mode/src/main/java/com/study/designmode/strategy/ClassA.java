package com.study.designmode.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-04-30 15:42
 * @Description
 **/
@Slf4j
@Component
public class ClassA implements InterfaceClass<BeanA> {
    @Override
    public String getObject() {
        return BeanA.class.getName();
    }

    @Override
    public void fun1(BeanA beanA) {
        log.info("classA execute fun1");
    }
}
