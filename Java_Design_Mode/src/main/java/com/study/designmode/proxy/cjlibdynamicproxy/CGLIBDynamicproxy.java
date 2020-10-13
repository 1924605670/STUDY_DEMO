package com.study.designmode.proxy.cjlibdynamicproxy;

import com.study.designmode.proxy.noproxy.HumenImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author chengzhihua
 * @description CGLIb 动态代理
 * @date 2020/10/13
 */
public class CGLIBDynamicproxy implements MethodInterceptor {
    public <T> T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls,this);
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        before();
        proxy.invokeSuper(obj,args);
        return null;
    }

    private void before(){
        System.out.println("cglib proxy");
    }





    public static void main(String[] args) {
        CGLIBDynamicproxy cglibDynamicproxy = new CGLIBDynamicproxy();
        HumenImpl hu = cglibDynamicproxy.getProxy(HumenImpl.class);
        hu.eat();
    }
}
