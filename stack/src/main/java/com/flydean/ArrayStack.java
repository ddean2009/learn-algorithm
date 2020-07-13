package com.flydean;

/**
 * @author wayne
 * @version ArrayStack
 */
public class ArrayStack {

    //实际存储数据的数组
    private int[] array;
    //stack的容量
    private int capacity;
    //stack头部指针的位置
    private int topIndex;

    public ArrayStack(int capacity){
        this.capacity= capacity;
        array = new int[capacity];
        //默认情况下topIndex是-1，表示stack是空
        topIndex=-1;
    }

    /**
     * stack 是否为空
     * @return
     */
    public boolean isEmpty(){
        return topIndex == -1;
    }

    /**
     * stack 是否满了
     * @return
     */
    public boolean isFull(){
        return topIndex == array.length -1 ;
    }

    public void push(int data){
        if(isFull()){
            System.out.println("Stack已经满了，禁止插入");
        }else{
            array[++topIndex]=data;
        }
    }

    public int pop(){
        if(isEmpty()){
            System.out.println("Stack是空的");
            return -1;
        }else{
            return array[topIndex--];
        }
    }
}
