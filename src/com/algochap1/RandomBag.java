package com.algochap1;

import com.stdlib.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Practice 1.3.34
 * 泛型随机背包
 * Created by Nathan on 5/5/2015.
 */
public class RandomBag<Item> implements Iterable<Item> {
    private Node<Item> first;
    private int N;

    private class Node<Item> {
        Item item;
        Node<Item> next;
    }

    public RandomBag() {
        first = null;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void add(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomBagIterator<>(first);
    }

    private class RandomBagIterator<Item> implements Iterator<Item> {
        private Item[] array = null;
        private int index = 0;
        private int size;

        // 保存所有元素并随机打乱它们的顺序
        public RandomBagIterator(Node<Item> node) {
            size = size();
            array = (Item[]) new Object[size];
            while (node != null) {
                array[index++] = node.item;
                node = node.next;
            }
            StdRandom.shuffle(array);
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return array[index++];
        }
    }

    public static void main(String[] args) {
        RandomBag<Integer> rb = new RandomBag<>();
        System.out.println("isEmpty:" + rb.isEmpty());
        rb.add(1);
        rb.add(2);
        rb.add(3);
        rb.add(4);
        rb.add(5);
        rb.add(6);
        System.out.println("size: " + rb.size());
        Iterator<Integer> iter = rb.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next().toString() + " ");
        }
        System.out.println();
        iter = rb.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next().toString() + " ");
        }
        System.out.println();
    }
}
