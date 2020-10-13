package com.study.designmode.proxy.jdkdynamicproxy;

import com.study.designmode.proxy.noproxy.Humen;
import com.study.designmode.proxy.noproxy.HumenImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author chengzhihua
 * @description JDK动态代理2
 * @date 2020/10/13
 * 缺点：无论是静态代理还是jdk动态代理都需要一个接口，那如果想要包装的类没有实现接口，就无法实现代理模式，这时候就需要cglib动态代理了
 * */
public class JDKDynamicProxy2 implements InvocationHandler {

    private Object target;

    public JDKDynamicProxy2(Object target) {
        this.target = target;
    }

    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object res = method.invoke(target, args);
        after();
        return res;
    }

    public void before() {
        System.out.println("jdk 动态代理执行前2");

    }

    public void after() {
        System.out.println("jdk 动态代理执行后2");

    }


    public static void main(String[] args) {
        JDKDynamicProxy2 jdkDynamicProxy2 = new JDKDynamicProxy2(new HumenImpl());
        Humen proxyHumen = jdkDynamicProxy2.getProxy();
        proxyHumen.eat();
    }
}
