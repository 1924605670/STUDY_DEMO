package com.study.designmode.singleton;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-07-28 10:25
 * @Description 设计模式之单例
 * 线程安全 - 懒汉式，双重检查，静态内部类
 **/
public class SingletonMode {
    public static void main(String[] args) {
        SingleTone3.SINGLE_TONE_3.fun1();
    }
}

/**
 * 双重检查模式（推荐使用）
 */
class SingleTone {

    private static volatile SingleTone singleTone;

    private SingleTone() {
    }

    public SingleTone getInstance() {
        if (singleTone == null) {
            synchronized (SingleTone.class) {
                if (singleTone == null) {
                    singleTone = new SingleTone();
                }
            }
        }
        return singleTone;
    }

}


/**
 * 静态内部类完成单例模式 (推荐使用)
 */
class SingleTone2{
    private SingleTone2(){}

    private static class InClassSingleTone3{
        private static final SingleTone2 singleTone2 = new SingleTone2();
    }

    public static SingleTone2 getInstance(){
        return InClassSingleTone3.singleTone2;
    }
}

/**
 * 枚举单例模式(jdk1.5推荐使用的单例模式)
 */
enum SingleTone3{
    // 单例对象
    SINGLE_TONE_3;
    public void fun1(){
        System.out.println("hello");
    }
}