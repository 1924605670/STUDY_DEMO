package com.study.anno.anno;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-07-13 19:16
 * @Description
 **/
@Aspect
@Component
@Slf4j
public class DiyMethodAspect {

    @Pointcut("execution(* com.study.anno.controller.*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before() {
        log.info("point before");
    }

    @After("pointCut()")
    public void after() {
        log.info("point after");
    }

    @Around("pointCut()  && @annotation(methodAnno)")
    public Object doing(ProceedingJoinPoint proceedingJoinPoint, MethodAnno methodAnno) {
        String action = methodAnno.action();
        Class clazz = proceedingJoinPoint.getTarget().getClass();
        log.info("class name :{}", clazz.getName());
        Object[] args = proceedingJoinPoint.getArgs();
        log.info("args  :{}", args);
        String name = proceedingJoinPoint.getSignature().getName();
        log.info("signature name :{}", name);

        switch (action) {
            case "fun1":
                log.info("fun1 excute");
                return "fun1";
            case "fun2":
                log.info("fun2 excute");
                return "fun2";
            default:
                return "";
        }

    }

}
