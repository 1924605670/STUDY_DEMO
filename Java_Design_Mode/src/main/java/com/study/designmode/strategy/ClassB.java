package com.study.designmode.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-04-30 15:43
 * @Description
 **/
@Slf4j
@Component
public class ClassB implements InterfaceClass<BeanB> {
    @Override
    public String getObject() {
        return BeanB.class.getName();
    }

    @Override
    public void fun1(BeanB beanB) {
        log.info("ClassB execute fun1");
    }
}
