package com.study.designmode.proxy.staticproxy;

import com.study.designmode.proxy.noproxy.Humen;
import com.study.designmode.proxy.noproxy.HumenImpl;

/**
 * @author chengzhihua
 * @description 静态代理类
 * @date 2020/10/13
 * 缺点：当需要重复逻辑的时候，代理需要实现一个新的接口，然后在写一个该接口的实现方法。但其实代理类的调用逻辑总是相似的，为了这么一个相似的实现效果，却要写很多的包装代码
 */

public class HumenStaticProxy implements Humen {
    private Humen humen;

    public HumenStaticProxy() {
        humen = new HumenImpl();
    }

    @Override
    public String eat() {
        System.out.println("静态代理吃饭前");
        return humen.eat();
    }

    public static void main(String[] args) {
        HumenStaticProxy humenStaticProxy = new HumenStaticProxy();
        humenStaticProxy.eat();
    }
}
