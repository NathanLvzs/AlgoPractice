package com.algochap1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayQueue<Item> {

    private Item[] a = (Item[]) new Object[1];
    private int N = 0;
    private int head = 0; // the first item
    private int tail = 0; // the index of last item plus 1

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public int alen() {
        return a.length;
    }

    private void resize(int max) {
        assert max >= N;
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[head + i];
        }
        a = temp;
        head = 0;
        tail = N;
    }

    public void enqueue(Item item) {
        int templen = a.length;
        if (tail == templen) {// a kind of wrap-around
            if (N < templen / 2)
                resize(templen);
            else if (N == templen)
                resize(2 * templen);
        }
        a[tail++] = item;
        N++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("underflow");
        Item item = a[head];
        a[head] = null;
        head++;
        N--;
        int templen = a.length;
        // wrap around
        // if(head == templen) head = 0;
        if (N > 0 && N == templen / 4)
            resize(templen / 2);
        return item;
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int current = head;

        public boolean hasNext() {
            return current < tail;
        }

        public Item next() {
            Item item = a[current];
            current++;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        ResizingArrayQueue<String> que = new ResizingArrayQueue<>();
        // assert que.size() == 0;
        // que.enqueue("to");
        // que.enqueue("be");
        // assert que.size() == 2;
        // que.enqueue("or");
        // assert que.size() < 4;
        // que.enqueue("not");
        // assert que.size() == 4;
        // que.enqueue("to");
        // assert que.size() < 8;
        // System.out.println(que.dequeue());
        // // assert que.size() < 4;
        // que.enqueue("be");
        // System.out.println(que.dequeue());
        // System.out.println(que.dequeue());

        que.enqueue("to");
        que.enqueue("be");
        System.out.println(que.size() + ", " + que.alen());
        System.out.println(que.dequeue());
        System.out.println(que.dequeue());
        System.out.println(que.size() + ", " + que.alen());
        que.enqueue("or");
        System.out.println(que.size() + ", " + que.alen());
        que.enqueue("not");
        que.enqueue("be");
        System.out.println(que.size() + ", " + que.alen());
        System.out.println(que.dequeue());
        System.out.println(que.size() + ", " + que.alen());
        System.out.println(que.dequeue());
        System.out.println(que.size() + ", " + que.alen());
    }
}

