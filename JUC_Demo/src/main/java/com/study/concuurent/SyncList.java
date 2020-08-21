package com.study.concuurent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-08-05 16:52
 * @Description 当写操作较多时采用 synchronizedList
 **/
public class SyncList {
    static List<Object> list = Collections.synchronizedList(new ArrayList<Object>());

    public static void main(String[] args) {


        for (int j = 0; j < 10; j++) {
            new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    synchronized (list) {
                        list.add(i);
                    }

                }
            }
            ).start();
        }

        System.out.println(list.size());


    }

    public static synchronized void add(Integer value) {
        list.add(value);
    }

}
