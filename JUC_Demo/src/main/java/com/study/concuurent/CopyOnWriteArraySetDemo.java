package com.study.concuurent;

/**
 * @Author Cheng ZhiHua
 * @Date 2020-03-17 15:49
 * @Description CopyOnWriteArraySet
 *
 * 是线程安全的无须的集合，可以将它即历程线程安全的hashSet，CopOnWriteArraySet是通过动态数组CopyOnWriteArrayList实现的，并不是和hashSet一样是通过hashMap实现的
 * 1.set大小通常保持很小，只读操作远多于可变操作，需要在遍历期间防止线程间的冲突
 * 2.他是线程安全的
 * 3.因为通常需要复制整个基础数组，所以可变操作的开销很大，add（），set（），remove（）
 * 4.迭代器支持hasnext（），next（）等操作，但是不支持remove（）操作
 * 5.使用迭代器进行遍历的速度很快，并且不会与其他线程发生线程冲突，在迭代器的构造时，迭代器依赖于不变的数组快照
 *
 **/
public class CopyOnWriteArraySetDemo {
}
