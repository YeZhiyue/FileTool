package cn.ye.data_structure;

import java.util.Iterator;

/**
     * 链表栈
     *  注意：这里需要注意非空方法判断的方式决定了你出入栈的逻辑
     *      我们这里统一使用：首个元素是否为null来进行链表是否为空
     * @param <Item>
     */
    public class StackLinked<Item> {

        private Node first;
        //整个N我认为从0开始会比较好，0表示链表为空。类似于str.length的逻辑思想，更容易理解。
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
         * 入栈逻辑：
         *      1.如果栈为空。那么添加的就是第一个元素，那么就不需要管理指针的指向，因为栈中仅仅只有一个元素
         *          1.创建新对象，fist引用新对象
         *          2.N++
         *      2.如果栈不为空。那么要做的事就是添加新元素，更新first，first指针指向oldfirst,栈的深度加1
         *          1.更新first(这里需要注意的就是oldfirst的保存，防止oldfirst引用丢失)
         *              1.保存first到oldfirst指针
         *              2.创建新的Node节点对象，first引用为新的Node节点
         *              3.新Node节点赋值
         *              4.新Node节点指针指向oldfirst
         *          2.栈的深度加1
         *              1.N++
         * @param item
         */
        public void push(Item item) {
            //如果栈为空
            if (isEmpty()) {
                first = new Node();
                first.item = item;
            }else{
                Node oldfirst = first;
                first = new Node();
                first.item = item;
                first.next = oldfirst;
            }
            N++;
        }

        /**
         *出栈逻辑：
         *      1.栈为空(栈中没有元素，不需要出栈，抛出空栈异常)
         *      2.栈不为空(更新first，栈的深度减1)
         *          1.获取first指针指向的Node
         *          2.first更新为刚刚取出的Node
         *          3.N--
         * @return
         * @throws Exception
         */
        public Item pop() /*throws Exception*/ {
            //记得保存first中的值用于返回数据，否则等下就改变了
            Item item = first.item;
            if (isEmpty()) {
                //throw new Exception("栈已空");
            }else{

                //这里不需要管理对象的游离状态，因为对出栈对象没有其他额外的引用
                first = first.next;
                N--;
            }
            return item;
        }

        /**
         * 获取迭代器，采用复合代替继承
         *      1.迭代逻辑
         *      2.判断逻辑
         *      3.修改逻辑
         *      这些逻辑可以根据需求来变更
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
                //不要return current.next!=null;这个逻辑会使得我的迭代丢失
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
