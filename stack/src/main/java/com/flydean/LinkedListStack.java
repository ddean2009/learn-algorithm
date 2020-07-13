package com.flydean;

/**
 * @author wayne
 * @version LinkedListStack,  2020/7/12 9:08 下午
 */
public class LinkedListStack {

    private Node headNode;

    class Node {
        int data;
        Node next;
        //Node的构造函数
        Node(int d) {
            data = d;
        }
    }

    public void push(int data){
        if(headNode == null){
            headNode= new Node(data);
        }else{
            Node newNode= new Node(data);
            newNode.next= headNode;
            headNode= newNode;
        }
    }

    public int top(){
        if(headNode ==null){
            return -1;
        }else{
            return headNode.data;
        }
    }

    public int pop(){
        if(headNode ==null){
            System.out.println("Stack是空的");
            return -1;
        }else{
            int data= headNode.data;
            headNode= headNode.next;
            return data;
        }
    }

    public boolean isEmpty(){
        return headNode==null;
    }
}
