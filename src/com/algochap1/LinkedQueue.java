package com.algochap1;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedQueue
 * Created by Nathan on 4/19/2015.
 */
public class LinkedQueue<Item> implements Iterable<Item> {

    private Node<Item> first; // the head of the queue
    private Node<Item> last; // the tail of the queue
    private int N; // number of the elements in the queue

    private class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public LinkedQueue(){
    }

    /**
     * practice 1.3.41    复制队列
     * 注意：复制得到的队列中的元素指向仍跟原来的相同，并没有将元素深层克隆
     *
     * @param q 用来复制的队列
     */
    public LinkedQueue(LinkedQueue<Item> q){
        LinkedQueue<Item> temp = new LinkedQueue<>();
        while(!q.isEmpty()){
            temp.enqueue(q.dequeue());
        }
        while(!temp.isEmpty()){
            Item item = temp.dequeue();
            q.enqueue(item);
            this.enqueue(item);
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        if (item == null) return;
        Node<Item> oldlast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }

    public Item dequeue() {
        Node<Item> oldfirst = first;
        first = first.next;
        if (isEmpty())
            last = null;
        N--;
        return oldfirst.item;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<>(first);
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> n) {
            current = n;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        LinkedQueue<Integer> q = new LinkedQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.dequeue();
        q.dequeue();
        q.dequeue();
        Iterator<Integer> qIter = q.iterator();
        while(qIter.hasNext()){
            System.out.print(qIter.next() + "\t");
        }
        System.out.println();
        LinkedQueue<Integer> cq = new LinkedQueue<>(q);
        Iterator<Integer> cqIter = cq.iterator();
        while(cqIter.hasNext()){
            System.out.print(cqIter.next() + "\t");
        }
        System.out.println();
    }
}
