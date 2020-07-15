package com.flydean;

/**
 * 使用动态循环数组来存储数据
 * @author wayne
 * @version DyncArrayDeQueue
 */
public class DyncArrayDeQueue {

    //存储数据的数组
    private int[] array;
    //head索引
    private int head;
    //real索引
    private int rear;
    //数组容量
    private int capacity;

    public DyncArrayDeQueue(int capacity){
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

    //因为是循环数组，这里不能做简单的数组拷贝
    private void extendQueue(){
        int newCapacity= capacity*2;
        int[] newArray= new int[newCapacity];
        //先全部拷贝
        System.arraycopy(array,0,newArray,0,array.length);
        //如果real<head,表示已经进行循环了，需要将0-head之间的数据置空，并将数据拷贝到新数组的相应位置
        if(rear < head){
            for(int i=0; i< head; i++){
                //重置0-head的数据
                newArray[i]= -1;
                //拷贝到新的位置
                newArray[i+capacity]=array[i];
            }
            //重置real的位置
            rear = rear +capacity;
            //重置capacity和array
            capacity=newCapacity;
            array=newArray;
        }
    }

    //从尾部入队列
    public void insertLast(int data){
        if(isFull()){
            System.out.println("Queue扩容");
            extendQueue();
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

    //从头部入队列
    public void insertFront(int data){
        if(isFull()){
            System.out.println("Queue扩容");
            extendQueue();
        }else{
            //从头部插入ArrayDeque
            head = (head + capacity - 1) % capacity;
            array[head]= data;
            //如果插入之前队列为空,将real指向head
            if(rear == -1 ){
                rear = head;
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

    //从尾部取数据
    public int deleteLast(){
        int data;
        if(isEmpty()){
            System.out.println("Queue is empty");
            return -1;
        }else{
            data= array[rear];
            //如果只有一个元素，则重置head和real
            if(head == rear){
                head= -1;
                rear = -1;
            }else{
                rear = (rear + capacity - 1)%capacity;
            }
            return data;
        }
    }

}
