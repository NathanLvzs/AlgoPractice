package com.algochap1;

/**
 * LinkedList practice for section 3 of chapter 1, Algorithm
 * Nathan, 2015-04-23
 * <p>
 * Note: javac -encoding utf-8 filename.java
 */

public class LinkedList<Item> {

    // 编写链表相关的代码时，必须小心处理异常情况（链表为空或者只有一个或两个结点）和边界情况（处理首尾结点）

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }


    // 删除链表的第k个元素（如果存在的话）
    public Node<Item> delete(Node<Item> head, int k) {
        if (k == 1) {
            head = head.next;
            return head;
        }
        int index = 1;
        Node<Item> tempNode = head;
        while (index < (k - 1) && tempNode != null) {
            index++;
            tempNode = tempNode.next;
        }
        if (tempNode != null) {// the k-1 node
            tempNode.next = tempNode.next.next;
        }
        return head;
    }

    // 打印链表中的所有元素
    public void printAll(Node<Item> head) {
        Node<Item> tempNode = head;
        while (tempNode != null) {
            System.out.print(tempNode.item.toString() + " ");
            tempNode = tempNode.next;
        }
        System.out.println();
    }

    // 输出整型链表中的最大值，迭代式
    public static Integer max(Node<Integer> head) {
        Integer subMax = 0;
        if (head == null)
            return 0;
        Node<Integer> tempNode = head;
        while (tempNode != null) {
            if (tempNode.item > subMax)
                subMax = tempNode.item;
            tempNode = tempNode.next;
        }
        return subMax;
    }

    // 输出整型链表中的最大值，递归式
    public static Integer maxRecursive(Node<Integer> head) {
        if (head == null)
            return 0;
        Integer subMax = maxRecursive(head.next);
        if (head.item > subMax)
            return head.item;
        else
            return subMax;
    }

    // 删除输入节点的后续节点
    public void removeAfter(Node<Item> node) {
        if (node != null) {
            removeAfter(node.next);
            node.next = null;
        }
    }

    // 将第二个节点插入链表并使之作为第一个节点的后续节点
    public void insertAfter(Node<Item> node1, Node<Item> node2) {
        if (node1 != null && node2 != null) {
            Node<Item> n1Next = node1.next;
            node1.next = node2;
            Node<Item> tempNode = node2;
            while (tempNode.next != null) {
                tempNode = tempNode.next;
            }
            tempNode.next = n1Next;
        }
    }

    /**
     * 迭代式反转单链表
     * 将链表头节点视为一个新的链表，从旧链表中逐步取出一个节点来添加到新链表的头部，跟书上的实现一致
     * move forward and split one element, use that element as the head of the new list, this method does not need extra node
     *
     * @param node the head node of the list to be reversed
     * @return the head node of the reversed linked list
     */
    public Node<Item> reverse(Node<Item> node) {
        Node<Item> head = node;
        Node<Item> result = null;
        Node<Item> tempNext;
        while (head != null) {
            tempNext = head.next;
            head.next = result;
            result = head;
            head = tempNext;
        }
        return result;
    }

    /**
     * 书上迭代式反转链表的代码
     * 解释：记录三个连续的结点：reverse、first和second，在每轮迭代中，从原链表提取结点first并将它插入到逆链表的开头，需要一直保持first指向原链表中所有剩余结点的首结点，second指向原链表中所有剩余结点的第二个结点，reverse指向结果链表中的首结点
     *
     * @param x 原链表的头结点
     * @return 逆链表的头结点
     */
    public Node<Item> reverseBook(Node<Item> x) {
        Node<Item> first = x;
        Node<Item> reverse = null;
        while (first != null) {
            Node<Item> second = first.next;
            first.next = reverse;
            reverse = first;
            first = second;
        }
        return reverse;
    }

    /**
     * 递归式反转单链表
     * 看了书上的实现，才发现自己执着于将结点A的下一个结点B的递归结果R作为B的父结点，使得返回的递归结果不始终为逆链表的头结点，耗费了较多的代码处理返回的结果
     * 书上的实现反转并不依赖于递归的返回结果
     *
     * @param node the head node of the list to be reversed
     * @param head must be null in the outside call
     * @return the head node of the reversed linked list
     */
    public Node<Item> reverseRecursive(Node<Item> node, Node<Item> head) {
        if (node == null)
            return null;
        boolean flag = (head == null);
        Node<Item> tempNode = node.next;
        node.next = null;
        if (tempNode == null) // means that this node is the end
            return node;
        Node<Item> tempHead = reverseRecursive(tempNode, node);
        if (tempHead.next != null)
            head = tempHead.next;// save the head
        else
            head = tempHead;
        tempHead.next = node;
        node.next = head;
        if (flag) {
            Node<Item> first = node.next;
            node.next = null; // break the circle
            return first;
        }
        else
            return node;
        // 下面这样就是书上的实现了。。。
        //tempNode.next = node;
        //node.next = null;
        //return tempHead;
    }

    /**
     * 书上的递归式反转链表的代码，先递归颠倒最后N-1个结点，然后小心地将原链表中的首结点插入到结果链表的末端
     *
     * @param first 原链表的头结点
     * @return 逆链表的头结点
     */
    public Node<Item> reverseRecursiveBook(Node<Item> first) {
        if (first == null) return null;
        if (first.next == null) return first;
        Node<Item> second = first.next;
        Node<Item> rest = reverseRecursiveBook(second);
        second.next = first;
        first.next = null;
        return rest;
    }

    public static void main(String[] args) {
        LinkedList<Integer> ll = new LinkedList<>();

        Node<Integer> head = new Node<>();
        head.item = 5;
        Node<Integer> second = new Node<>();
        second.item = 1;
        head.next = second;
        Node<Integer> third = new Node<>();
        third.item = 9;
        second.next = third;
        Node<Integer> fourth = new Node<>();
        fourth.item = 6;
        third.next = fourth;
        Node<Integer> fifth = new Node<>();
        fifth.item = 2;
        fourth.next = fifth;

        // print all
        System.out.println("print all ======");
        ll.printAll(head);

        // test max
        System.out.println("test max ======");
        System.out.println(maxRecursive(head));
        System.out.println(max(head));

        // test delete
        //        System.out.println("test delete ======");
        // ll.printAll(head);
        // ll.printAll(ll.delete(head, 4));
        // ll.printAll(ll.delete(head, 1));

        // test remove
        //System.out.println("test remove ======");
        //ll.printAll(head);
        //ll.removeAfter(third);
        //ll.printAll(head);

        // test reverse
        //System.out.println("test reverse ======");
        //System.out.print("Original: ");
        //ll.printAll(head);
        //System.out.print("Reversed: ");
        //ll.printAll(ll.reverse(head));
        //ll.printAll(ll.reverseBook(head));

        // test reverseRecursive
        System.out.println("test reverseRecursive ======");
        System.out.print("Original: ");
        ll.printAll(head);
        System.out.print("Reversed: ");
        ll.printAll(ll.reverseRecursive(head, null));
        //ll.printAll(ll.reverseRecursiveBook(head));
    }
}


