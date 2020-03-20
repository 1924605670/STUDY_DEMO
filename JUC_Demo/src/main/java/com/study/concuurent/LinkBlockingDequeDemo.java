package com.study.concuurent;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-03-16 15:39
 * @Description LinkBlockingDeque
 * <p>
 * LinkBlockingDequeDemo 是双向链表实现的双向并发阻塞队列，该队列同时支持FIFO和FILO两种操作方式，
 * 即可以从队列的头和尾同时操作（插入/删除）,是支持线程安全的
 * <p>
 * 同 LinkBlockingQueue结构一样，支持初始化队列容量大小，默认Inter.MAX_VALUE
 **/
public class LinkBlockingDequeDemo {

    public static LinkedBlockingDeque<String> queue = new LinkedBlockingDeque<String>();


    public static class MyThread extends Thread {
        MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            int i = 0;
            while (i++ < 6) {
                queue.add(Thread.currentThread().getName() + "-" + i);
                printAll(queue);
            }
        }
    }


    public static void printAll(LinkedBlockingDeque queue) {
        queue.iterator().forEachRemaining(x -> System.out.print(x+","));
        System.out.println();
    }

    public static void main(String[] args) {
        new MyThread("ta").start();
        new MyThread("tb").start();
    }

}
