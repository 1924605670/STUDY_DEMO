package com.study.designmode.proxy.cjlibdynamicproxy;

import com.study.designmode.proxy.noproxy.HumenImpl;

/**
 * @author chengzhihua
 * @description 单例模式动态代理
 * @date 2020/10/13
 *
 * 优点：优势明显，可以在没有接口的类的包装前置和后置方法，比jdk动态代理和静态代理灵活的多
 *
 * 缺点：jdk动态代理可以根据接口信息拦截特定的方法，而cglib动态代理乜有接受接口信息下，所以是没有做拦截，而是代理所有的方法
 *
 */
public class CGlibProxy {

    private static CGLIBDynamicproxy cglibDynamicproxy = new CGLIBDynamicproxy();

    public static CGLIBDynamicproxy getInstance(){
        return cglibDynamicproxy;
    }

    public static void main(String[] args) {
        CGlibProxy.getInstance().getProxy(HumenImpl.class).eat();
    }
}
