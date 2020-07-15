package com.flydean;

/**
 * @author wayne
 * @version LinkedListDeQueue
 */
public class LinkedListDeQueue {
    //head节点
    private Node headNode;
    //rear节点
    private Node rearNode;

    class Node {
        int data;
        Node next;
        Node prev;
        //Node的构造函数
        Node(int d) {
            data = d;
        }
    }

    public boolean isEmpty(){
        return headNode==null;
    }

    //从队尾插入
    public void insertLast(int data){
        Node newNode= new Node(data);
        //将rearNode的next指向新插入的节点
        if(rearNode !=null){
            rearNode.next=newNode;
            newNode.prev=rearNode;
        }
        rearNode=newNode;
        if(headNode == null){
            headNode=newNode;
        }
    }

    //从队首插入
    public void insertFront(int data){
        if(headNode == null){
            headNode= new Node(data);
        }else{
            Node newNode= new Node(data);
            newNode.next= headNode;
            headNode.prev= newNode;
            headNode= newNode;
        }
    }

    //从队首删除
    public int deleteFront(){
        int data;
        if(isEmpty()){
            System.out.println("Queue is empty");
            return -1;
        }else{
            data=headNode.data;
            headNode=headNode.next;
            headNode.prev=null;
        }
        return data;
    }

    //从队尾删除
    public int deleteLast(){
        int data;
        if(isEmpty()){
            System.out.println("Queue is empty");
            return -1;
        }else{
            data=rearNode.data;
            rearNode=rearNode.prev;
            rearNode.next=null;
        }
        return data;
    }

}
