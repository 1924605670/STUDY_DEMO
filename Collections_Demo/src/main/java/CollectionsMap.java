/**
 * @Author Cheng ZhiHua
 * @Date 2020-03-13 14:21
 * @Description Map 集合源码笔记
 **/
public class CollectionsMap {


    /**
     * HashMap 的底层数据结构是 数组+单项链表的数据结构
     *
     * 初始化是一个数组 transient Node<K,V>[] table; 默认大小16
     * 负载加载因子 默认 0.75 当数组大小超过0.75的时候就会扩大数组大小/
     *
     * 当单向链表长度超过8的时候就会变形成红黑树结构
     *
     *
     * put方法是先算出key的hashkey，放到数组当中，如果当前位置已有数据，就会出现链表向下存储
     *
     */
}
