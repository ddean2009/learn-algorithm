package com.flydean;

/**
 * @author wayne
 * @version LinkedListQueue
 */
public class LinkedListQueue {
    //head节点
    private Node headNode;
    //rear节点
    private Node rearNode;

    class Node {
        int data;
        Node next;
        //Node的构造函数
        Node(int d) {
            data = d;
        }
    }

    public boolean isEmpty(){
        return headNode==null;
    }

    public void enQueue(int data){
        Node newNode= new Node(data);
        //将rearNode的next指向新插入的节点
        if(rearNode !=null){
            rearNode.next=newNode;
        }
        rearNode=newNode;
        if(headNode == null){
            headNode=newNode;
        }
    }

    public int deQueue(){
        int data;
        if(isEmpty()){
            System.out.println("Queue is empty");
            return -1;
        }else{
            data=headNode.data;
            headNode=headNode.next;
        }
        return data;
    }

}
