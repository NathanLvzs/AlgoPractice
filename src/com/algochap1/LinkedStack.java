package com.algochap1;

import com.stdlib.StdIn;
import com.stdlib.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedStack
 * Created by Nathan on 4/19/2015.
 */
public class LinkedStack<Item> implements Iterable<Item> {

    private Node<Item> first; // the top of the stack
    private int N; // number of the elements in the stack

    private class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public LinkedStack() {
        first = null;
        N = 0;
    }

    /**
     * practice 1.3.42  构造函数，复制栈
     * 注意：复制得到的栈中的元素指向仍跟原来的相同，并没有将元素深层克隆
     *
     * @param s 用来复制的栈
     * @throws Throwable
     */
    public LinkedStack(LinkedStack<Item> s) throws Throwable {
        LinkedStack<Item> temp = new LinkedStack<>();
        while (!s.isEmpty()) {
            temp.push(s.pop());
        }
        while (!temp.isEmpty()) {
            Item item = temp.pop();
            s.push(item);
            this.push(item);
        }
    }

    public boolean isEmpty() {
        return first == null; // N == 0
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        if (item == null) return;
        Node<Item> oldfirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    public Item peek() throws Exception {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return first.item;
    }

    public Item pop() throws Exception {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    /**
     * 使用迭代器复制本身，返回复制的栈
     * 注意：复制得到的栈中的元素指向仍跟原来的相同，并没有将元素深层克隆
     *
     * @return
     */
    public LinkedStack<Item> copy() {
        LinkedStack<Item> tempStack = new LinkedStack<>();
        Iterator<Item> tempIter = this.iterator();
        while (tempIter.hasNext()) {
            tempStack.push(tempIter.next());
        }
        LinkedStack<Item> result = new LinkedStack<>();
        Iterator<Item> iter = tempStack.iterator();
        while (iter.hasNext()) {
            result.push(iter.next());
        }
        return result;
    }

    @Override
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

    public static void main(String[] args) throws Throwable {
        LinkedStack<Integer> s = new LinkedStack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        Iterator<Integer> sIter = s.iterator();
        while (sIter.hasNext()) {
            System.out.print(sIter.next().toString() + "\t");
        }
        System.out.println();
        LinkedStack<Integer> cs = new LinkedStack<>(s);
        Iterator<Integer> csIter = cs.iterator();
        while (csIter.hasNext()) {
            System.out.print(csIter.next().toString() + "\t");
        }
        System.out.println();
        cs = s.copy();
        csIter = cs.iterator();
        while (csIter.hasNext()) {
            System.out.print(csIter.next().toString() + "\t");
        }
        System.out.println();
    }
}
