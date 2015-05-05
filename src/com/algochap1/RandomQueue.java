package com.algochap1;

import com.stdlib.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * practice 1.3.35 and 1.3.36
 * 泛型随机队列，可变大小数组实现
 * Created by Nathan on 5/5/2015.
 */
public class RandomQueue<Item> implements Iterable<Item> {

    private int N;
    private Item[] arr;

    public RandomQueue() {
        arr = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        int len = arr.length;
        arr[N++] = item;
        if (N == len)
            resize(2 * N);
    }

    private void resize(int max) {
        assert max >= N;
        Item[] newArr = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)
            newArr[i] = arr[i];
        arr = newArr;
    }

    /**
     * 删除并随机返回一个元素（取样且不放回）
     * 随机交换某个元素（索引在0和N-1之间）和末尾元素（索引为N-1）的位置，然后删除并返回末尾元素
     *
     * @return 退出队列的元素
     */
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        int selectedIndex = (N - 1) > 0 ? StdRandom.uniform(0, N - 1) : 0;
        Item result = arr[selectedIndex];
        arr[selectedIndex] = arr[N - 1];
        arr[N - 1] = null;
        N--;
        if (N == arr.length / 4)
            resize(arr.length / 2);
        return result;
    }

    /**
     * 随机返回一个元素但不删除它（取样且放回）
     *
     * @return 采样的元素
     */
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        int selectedIndex = (N - 1) > 0 ? StdRandom.uniform(0, N - 1) : 0;
        Item result = arr[selectedIndex];
        return result;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomQueueIterator<>(arr);
    }

    private class RandomQueueIterator<Item> implements Iterator<Item> {
        private Item[] inArr;
        private int size;
        private int index;

        public RandomQueueIterator(Item[] source) {
            size = size();
            inArr = (Item[]) new Object[size];
            for (int i = 0; i < size; i++)
                inArr[i] = source[i];
            StdRandom.shuffle(inArr);
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
            return inArr[index++];
        }
    }

    public static void main(String[] args) {
        // 模拟发牌，懒得定义纸牌类，用整型代替
        RandomQueue<Integer> rq = new RandomQueue<>();
        for (int i = 1; i <= 52; i++)
            rq.enqueue(i);
        System.out.println("size: " + rq.size());

        Iterator<Integer> iter = rq.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next() + "\t");
        }
        System.out.println();

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 13; j++) {
                int temp = rq.dequeue();
                if (temp % 13 == 0)
                    System.out.print(13 + "\t");
                else
                    System.out.print(temp % 13 + "\t");
            }
            System.out.println();
        }
        System.out.println("isEmpty: " + rq.isEmpty());
    }
}
