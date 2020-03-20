package com.study.concuurent;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-03-17 16:09
 * @Description CopyOnWriteArrayList
 * CopyOnWriteArrayList 继承了list 是一个队列，包含lock，每一个CopyOnWriteArrayList都和一个互斥锁绑定，通过lock实现了对copyOnWriteArrayList的互斥访问
 * CopyOnWriteArrayList 是通过数组实现的。
 * 内部含有volatile数组来保存数据，在对数据进行操作的时候，都会新建一个数组，并将更新后的数据拷贝到新建的数组中，最后再将数组复制给volatile数组，就是通过这种方式实现的动态数组
 * 不过正是由于这种实现机制，导致在操作数据时效率比较低，但是在遍历查找的时候，效率比较高。
 * 在操作数据的时候，会先获取互斥锁，然后在修改数据完毕的时候，将数据更新到volatile数组中，然后在释放互斥锁，这样就达到了保护数据的目的。
 *
 **/
public class CopyOnWriteArrayListDemo {
}
