package cn.mmooo.adt.impl;

import java.util.Iterator;

public class QueueAndStackByJava<AnyType> implements Iterable<AnyType> {

    private int size;
    private Node<AnyType> head;
    private Node<AnyType> tail;

    private void init() {
        size = 0;
        head = new Node<>();
        tail = new Node<>();
        head.next = tail;
        tail.pre = head;
    }

    public void makeEmpty() {
        init();
    }

    QueueAndStackByJava() {
        this.init();
    }


    public AnyType poll() {
        checkSize();
        final AnyType t = head.next.ele;
        head.next.next.pre = head;
        head.next = head.next.next;
        this.size--;
        return t;

    }

    private void checkSize() {
        if (isEmpty()) {
            throw new IllegalArgumentException("");
        }
    }

    public void push(AnyType anyType) {
        Node<AnyType> node = new Node<>(anyType);
        tail.pre.next = node;
        node.pre = tail.pre;

        node.next = tail;
        tail.pre = node;
        this.size++;
    }


    public AnyType pop() {
        checkSize();
        final AnyType t = tail.pre.ele;
        tail.pre.pre.next = tail;
        tail.pre = tail.pre.pre;
        this.size--;
        return t;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public AnyType peekFirst() {
        checkSize();
        return head.next.ele;
    }


    public AnyType peekLast() {
        checkSize();
        return tail.pre.ele;
    }


    public Iterator<AnyType> iterator() {
        return new Iterator<AnyType>() {

            private Node<AnyType> currentIterNode = head;


            public boolean hasNext() {
                return currentIterNode.next != tail;
            }


            public AnyType next() {
                currentIterNode = currentIterNode.next;
                return currentIterNode.ele;
            }
        };
    }


    public int getSize() {
        return this.size;
    }


    private static class Node<T> {
        private Node<T> next;
        private Node<T> pre;
        private T ele;

        Node() {
        }

        Node(T ele) {
            this.ele = ele;
        }
    }
}
