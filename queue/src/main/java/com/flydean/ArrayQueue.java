package com.flydean;

/**
 * 使用循环数组来存储数据
 * @author wayne
 * @version ArrayQueue
 */
public class ArrayQueue {

    //存储数据的数组
    private int[] array;
    //head索引
    private int head;
    //real索引
    private int rear;
    //数组容量
    private int capacity;

    public ArrayQueue (int capacity){
        this.capacity=capacity;
        this.head=-1;
        this.rear =-1;
        this.array= new int[capacity];
    }

    public boolean isEmpty(){
        return head == -1;
    }

    public boolean isFull(){
        return (rear +1)%capacity==head;
    }

    public int getQueueSize(){
        if(head == -1){
            return 0;
        }
        return (rear +1-head+capacity)%capacity;
    }

    //从尾部入队列
    public void enQueue(int data){
        if(isFull()){
            System.out.println("Queue is full");
        }else{
            //从尾部插入
            rear = (rear +1)%capacity;
            array[rear]= data;
            //如果插入之前队列为空,将head指向real
            if(head == -1 ){
                head = rear;
            }
        }
    }

    //从头部取数据
    public int deQueue(){
        int data;
        if(isEmpty()){
            System.out.println("Queue is empty");
            return -1;
        }else{
            data= array[head];
            //如果只有一个元素，则重置head和real
            if(head == rear){
                head= -1;
                rear = -1;
            }else{
                head = (head+1)%capacity;
            }
            return data;
        }
    }
}
