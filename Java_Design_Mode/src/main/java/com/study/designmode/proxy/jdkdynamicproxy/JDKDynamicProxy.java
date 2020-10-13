package com.study.designmode.proxy.jdkdynamicproxy;

import com.study.designmode.proxy.noproxy.Humen;
import com.study.designmode.proxy.noproxy.HumenImpl;
import sun.security.krb5.KdcComm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author chengzhihua
 * @description JDK动态代理
 * @date 2020/10/13
 */
public class JDKDynamicProxy implements InvocationHandler {

    private Object target;

    public JDKDynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object res = method.invoke(target, args);
        after();
        return res;
    }

    public void before() {
        System.out.println("jdk 动态代理执行前");

    }

    public void after() {
        System.out.println("jdk 动态代理执行后");

    }


    public static void main(String[] args) {
        Humen hume= new HumenImpl();
        JDKDynamicProxy jdkDynamicProxy = new JDKDynamicProxy(hume);
        Humen humenProxy = (Humen) Proxy.newProxyInstance(hume.getClass().getClassLoader(),hume.getClass().getInterfaces(),jdkDynamicProxy);
        humenProxy.eat();
    }
}
