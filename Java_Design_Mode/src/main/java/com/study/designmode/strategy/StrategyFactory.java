package com.study.designmode.strategy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-04-30 15:45
 * @Description
 **/
@Component
public class StrategyFactory implements ApplicationContextAware {

    private static Map<Object, InterfaceClass> ClassMap = new HashMap();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, InterfaceClass> beansOfType = applicationContext.getBeansOfType(InterfaceClass.class);
        beansOfType.forEach((x, y) -> ClassMap.put(y.getObject(), y));
    }


    public static InterfaceClass getRealImpl(String name) {
        return ClassMap.get(name);
    }
}
