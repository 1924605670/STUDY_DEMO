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
 * JDK 底层是生成字节码文件，代理对象已经继承了Proxy对象，根据java的单继承多实现的原理，所以jdk动态代理对象必须是接口才能生成代理对象，通过实现该代理类生成代理对象
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
