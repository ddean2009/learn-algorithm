package com.flydean;

/**
 * @author wayne
 * @version SeperateChainHashMap
 */
public class SeperateChainHashMap {

    //存储数据的节点
    static class HashNode
    {
        public int value;
        public int key;
        public HashNode next;
        HashNode(int key, int value)
        {
            this.value = value;
            this.key = key;
        }
    }

    //hashNode数组，用来存储hashMap的数据
    HashNode[] hashNodes;
    int capacity;
    int size;

    public SeperateChainHashMap(){
        this.capacity=20;
        this.size=0;
        hashNodes= new HashNode[capacity];
    }

    //获取key的hashCode
    int hashCode(int key)
    {
        return key % capacity;
    }

    public int get(int key)
    {
        int index=hashCode(key);
        HashNode head=hashNodes[index];
        while(head!=null)
        {
            if(head.key == key )
            {
                return head.value;
            }
            head=head.next;
        }
        return -1;
    }

    //删除元素
    public int remove(int key)
    {
        int index=hashCode(key);
        HashNode head=hashNodes[index];
        //没有元素
        if(head==null)
        {
            return -1;
        }
        //第一个元素就是要删除的元素
        if(head.key == key )
        {
            int val=head.value;
            head=head.next;
            hashNodes[index]= head;
            size--;
            return val;
        }
        //遍历链表
        else {
            HashNode prev=null;
            while(head!=null)
            {
                if(head.key ==key)
                {
                    prev.next=head.next;
                    size--;
                    return head.value;
                }
                prev=head;
                head=head.next;
            }
            size--;
            return -1;
        }
    }

    //添加元素
    public void add(int key,int value)
    {

        int index=hashCode(key);
        HashNode head=hashNodes[index];
        HashNode toAdd=new HashNode(key,value);
        if(head==null)
        {
            hashNodes[index]= toAdd;
            size++;
        }
        else
        {
            while(head!=null)
            {
                if(head.key == key )
                {
                    head.value=value;
                    size++;
                    break;
                }
                head=head.next;
            }
            if(head==null)
            {
                head=hashNodes[index];
                toAdd.next=head;
                hashNodes[index]= toAdd;
                size++;
            }
        }
        //动态扩容
        if((1.0*size)/capacity>0.7)
        {
            HashNode[] tmp=hashNodes;
            hashNodes=new HashNode[capacity*2];
            capacity=2*capacity;
            for(HashNode headNode:tmp)
            {
                while(headNode!=null)
                {
                    add(headNode.key, headNode.value);
                    headNode=headNode.next;
                }
            }
        }
    }
}
