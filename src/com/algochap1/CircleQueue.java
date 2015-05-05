package com.algochap1;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class CircleQueue<Item> {
    private static class Node<Item> {
        public Node() {
        }

        public Node(Item inputItem, Node<Item> nextElement) {
            item = inputItem;
            next = nextElement;
        }

        private Item item;
        private Node<Item> next;
    }

    private Node<Item> last = null;
    private int N = 0;

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<>();
        last.item = item;
        if (oldlast == null) {
            last.next = last;
        }
        else {
            last.next = oldlast.next;
            oldlast.next = last;
        }
        oldlast = null;
        N++;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        Node<Item> first = last.next;
        N--;
        if (N == 0) {
            last = null;
            return first.item;
        }
        else {
            last.next = first.next;
        }
        return first.item;
    }

    public void printAll() {
        Iterator<Item> iter = iterator();
        System.out.print("current queue: ");
        while (iter.hasNext()) {
            System.out.print(iter.next().toString() + " ");
        }
        System.out.println();
    }

    public Iterator<Item> iterator() {
        return new CircleIterator<>(last);
    }

    private class CircleIterator<Item> implements Iterator<Item> {
        private Node<Item> current;
        private int counter;

        public CircleIterator(Node<Item> lastNode) {
            if (lastNode != null)
                current = lastNode.next;
            else
                current = null;
            counter = N;
        }

        public boolean hasNext() {
            return counter > 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            counter--;
            Node<Item> oldCurrent = current;
            current = current.next;
            return oldCurrent.item;
        }
    }

    public static void main(String[] args) {
        CircleQueue<Integer> cq = new CircleQueue<>();
        System.out.println("==length: " + cq.size());
        System.out.println("++enqueue: 1");
        cq.enqueue(1);
        System.out.println("++enqueue: 2");
        cq.enqueue(2);
        System.out.println("++enqueue: 3");
        cq.enqueue(3);
        System.out.println("==length: " + cq.size());
        cq.printAll();
        System.out.println("--dequeue: " + cq.dequeue().toString());
        System.out.println("++enqueue: 4");
        cq.enqueue(4);
        System.out.println("==length: " + cq.size());
        System.out.println("--dequeue: " + cq.dequeue().toString());
        System.out.println("++enqueue: 5");
        cq.enqueue(5);
        System.out.println("--dequeue: " + cq.dequeue().toString());
        System.out.println("--dequeue: " + cq.dequeue().toString());
        System.out.println("--dequeue: " + cq.dequeue().toString());
        cq.printAll();
        // System.out.println("--dequeue: " + cq.dequeue().toString());
        // cq.printAll();
    }
}