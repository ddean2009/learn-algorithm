package com.flydean;

/**
 * @author wayne
 * @version DoublyLinkedList
 */
public class DoublyLinkedList {

    Node head; // head 节点

    //Node表示的是Linked list中的节点，包含一个data数据，上一个节点和下一个节点的引用
    class Node {
        int data;
        Node next;
        Node prev;
        //Node的构造函数
        Node(int d) {
            data = d;
        }
    }

    //插入到linkedList的头部
    public void push(int newData) {
        //构建要插入的节点
        Node newNode = new Node(newData);
        //新节点的next指向现在的head节点
        //新节点的prev指向null
        newNode.next = head;
        newNode.prev = null;

        if (head != null)
            head.prev = newNode;

        //现有的head节点指向新的节点
        head = newNode;
    }

    //插入在第几个元素之后
    public void insertAfter(int index, int newData) {
        Node prevNode = head;
        for (int i = 1; i < index; i++) {
            if (prevNode == null) {
                System.out.println("输入的index有误，请重新输入");
                return;
            }
            prevNode = prevNode.next;
        }
        //创建新的节点
        Node newNode = new Node(newData);
        //新节点的next指向prevNode的下一个节点
        newNode.next = prevNode.next;
        //将新节点插入在prevNode之后
        prevNode.next = newNode;
        //将新节点的prev指向prevNode
        newNode.prev = prevNode;

        //newNode的下一个节点的prev指向newNode
        if (newNode.next != null)
            newNode.next.prev = newNode;
    }

    //新节点插入到list最后面
    public void append(int newData) {
        //创建新节点
        Node newNode = new Node(newData);
        //如果list是空，则新节点作为head节点
        if (head == null) {
            newNode.prev = null;
            head = newNode;
            return;
        }

        newNode.next = null;
        //找到最后一个节点
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }
        //插入
        last.next = newNode;
        newNode.prev = last;
        return;
    }

    //删除特定位置的节点
    void deleteNode(int index)
    {
        // 如果是空的，直接返回
        if (head == null)
            return;

        // head节点
        Node temp = head;

        // 如果是删除head节点
        if (index == 1)
        {
            head = temp.next;
            return;
        }

        // 找到要删除节点的前一个节点
        for (int i=1; temp!=null && i<index-1; i++)
            temp = temp.next;

        // 如果超出范围
        if (temp == null || temp.next == null)
            return;

        // temp->next 是要删除的节点，删除节点
        Node next = temp.next.next;
        temp.next = next;
        Node prev = temp.next.prev;
        prev.prev = prev;
    }

    public void printList() {
        Node tnode = head;
        while (tnode != null) {
            System.out.print(tnode.data + " ");
            tnode = tnode.next;
        }
        System.out.println(" ");
    }

    public static void main(String[] args) {
        DoublyLinkedList linkedList = new DoublyLinkedList();
        //插入数据
        linkedList.push(13);
        linkedList.push(20);
        linkedList.printList();
        //插入到最后
        linkedList.append(34);
        linkedList.append(21);
        linkedList.printList();
        //在第二个节点之后插入
        linkedList.insertAfter(2, 55);
        linkedList.printList();
        //删除第三个节点
        linkedList.deleteNode(3);
        linkedList.printList();


    }
}
