package com.algochap1;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Practice 1.3.33
 * 泛型双向队列，双向链表实现
 * Created by Nathan on 5/5/2015.
 */
public class Deque<Item> implements Iterable<Item> {

    private class BiNode<Item> {
        Item item;
        BiNode<Item> prev;
        BiNode<Item> next;
    }

    private BiNode<Item> head;
    private BiNode<Item> tail;

    private int N;

    public Deque() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void pushLeft(Item item) {
        BiNode<Item> newHead = new BiNode<>();
        newHead.item = item;
        if (isEmpty()) {
            head = newHead;
            tail = head;
        }
        else {
            head.prev = newHead;
            newHead.next = head;
            head = newHead;
        }
        N++;
    }

    public void pushRight(Item item) {
        BiNode<Item> newTail = new BiNode<>();
        newTail.item = item;
        if (isEmpty()) {
            tail = newTail;
            head = tail;
        }
        else {
            tail.next = newTail;
            newTail.prev = tail;
            tail = newTail;
        }
        N++;
    }

    public Item popLeft() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item result = head.item;
        if (N == 1) {
            head = null;
            tail = null;
        }
        else {
            BiNode<Item> newHead = head.next;
            newHead.prev = null;
            head = newHead;
        }
        N--;
        return result;
    }

    public Item popRight() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item result = tail.item;
        if (N == 1) {
            head = null;
            tail = null;
        }
        else {
            BiNode<Item> newTail = tail.prev;
            newTail.next = null;
            tail = newTail;
        }
        N--;
        return result;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator<>(head);
    }

    private class DequeIterator<Item> implements Iterator<Item> {
        private BiNode<Item> current;

        public DequeIterator(BiNode<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> de = new Deque<>();
        System.out.println(de.isEmpty());// assert
        de.pushLeft(1);
        de.pushLeft(2);
        de.pushRight(3);
        de.pushRight(4);
        Iterator<Integer> iter = de.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next().toString());
        }
        System.out.println(de.size() == 4);
        System.out.println(de.popRight() == 4);
        System.out.println(de.popLeft() == 2);
        System.out.println(de.popLeft() == 1);
        System.out.println(de.popLeft() == 3);
        System.out.println(de.isEmpty());
    }
}
