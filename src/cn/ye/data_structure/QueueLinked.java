package cn.ye.data_structure;

import java.util.Iterator;

/**
 * 链表队列实现
 *  注意：这里需要注意非空方法判断的方式决定了你出入栈的逻辑
 *      我们这里统一使用：首个元素是否为null来进行链表是否为空
 * @param <Item>
 */
public class QueueLinked<Item> {

    //队首引用
    private Node first;
    //队尾引用
    private Node last;
    private int N = 0;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        //或者N==0，根据习惯来定义
        return first == null;
    }

    public int size() {
        return N;
    }


    /**
     * 进队逻辑：
     *      1.队列为空。更新first和last，不需要额外管理指针，并且队列长度加1。
     *          1.获取新的Node节点
     *          2.将first和last更新为新的Node
     *          3.N++
     *      2.队列不为空。更新last，操作和链表栈类似
     *          1.保存last引用到oldfist
     *          2.last更新为新的Node
     *          3.last更新赋值
     *          4.last指针更新
     *          5.N++
     *
     * @param item
     */
    public void enqueue(Item item) {

        //保留原始节点的指针(引用变量)
        Node oldlast = last;
        last=new Node();
        last.item=item;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next=last;
        }
        N++;
    }

    /**
     * 出队逻辑；first更新(注意：不能包步骤2、3简单的操作为last更新为first的指针，因为在队列长度大于1的情况下，last的变化和first没有直接关系)
     *      1.队列为空。不出队，并且抛出异常
     *      2.队列当前长度=1。first出队，last引用为空，队列长度减1
     *          1.first更新为下一个队列元素
     *          2.last更新为null
     *          3.N--
     *      3.队列当前长度>1
     *          1.first更新为下一个队列元素
     *          2.N--
     *
     * @return
     * @throws Exception
     */
    public Item dequeue() /*throws Exception*/ {

        if (isEmpty()) {
            /*throw new Exception("队列已经空了");*/
            return first.item;
        }
        Item item=first.item;
        first=first.next;
        if (N == 1) {
            //防止last的保留了引用，导致对象游离
            last = null;
        }
        N--;
        return item;
    }

    /**
     * 获取迭代器，采用复合代替继承
     * @return
     */
    public Iterator<Item> iterator(){ return new ListIterator(); }
    private class ListIterator implements Iterator<Item> {
        private Node current=first;
        private Item item;
        @Override
        public void remove() {
            //避免数据修改操作，我们省略了这一步的实现
        }


        @Override
        public boolean hasNext() {
            return current!=null;
        }

        @Override
        public Item next() {
            item=current.item;
            current=current.next;
            return item;
        }
    }
}

