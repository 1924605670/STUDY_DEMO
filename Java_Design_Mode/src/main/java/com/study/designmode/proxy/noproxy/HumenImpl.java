package com.study.designmode.proxy.noproxy;

/**
 * 无代理模式，直接实现
 */
public class HumenImpl implements Humen {


    @Override
    public String eat() {
        System.out.println("吃饭实现");
        return "ok";
    }


    public static void main(String[] args) {
        Humen humen = new HumenImpl();
        humen.eat();
    }

}
