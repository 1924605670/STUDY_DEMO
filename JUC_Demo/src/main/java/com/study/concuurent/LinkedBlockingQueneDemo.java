package com.study.concuurent;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-03-16 15:02
 * @Description LinkedBlockingQueue特性
 *
 * LinkedBlockingQueue是一个单向链表实现的阻塞队列。该队列按照FIFO排序元素，新元素插入到队列的尾部，并且队列获取操作会获取位于队列头部的元素。
 * 链表队列的吞吐量通常要高于基于数组的队列，但是大多数的并发应用程序中，其可预知的性能要低。
 * 此外，LinkedBlockingQueue 还是可以选容量的，防止过度膨胀，既可以指定队列的大小容量，如果不指定，默认容量为 Integer.MAX_VALUE.
 *
 * LinkedBlockingQueue 实现了BlockQueue接口，支持多线程并发，当多线程同时竞争同一资源的时候，某线程获取到该资源之后，其他线程需要阻塞等待。
 *
 *
 **/
public class LinkedBlockingQueneDemo {


    private static LinkedBlockingQueue queue = new LinkedBlockingQueue<String>();


    public static void printAll(LinkedBlockingQueue queue) {
        Iterator iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + ",");
        }
        System.out.println();
    }

    public static class MyThread extends Thread {

        MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {

            int i = 0;

            while (i++ < 6) {
                queue.add(Thread.currentThread().getName() + i);
                printAll(queue);
            }

        }
    }

    public static void main(String[] args) {
        new MyThread("threadA-").start();
        new MyThread("threadB-").start();

    }


}
