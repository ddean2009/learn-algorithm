package com.flydean;

/**
 * @author wayne
 * @version BinaryHeap,  2020/7/22
 */
public class BinaryHeap {

    //底层存储数组用来存储数据
    private int[] intArray;
    //heap大小
    private int heapSize;
    //数组容量
    private int capacity;

    //获取某个index的父节点
    private int parent(int i) { return (i-1)>>1; } // (i-1)/2
    //获取某个index的左子节点
    private int leftChild(int i) { return i<<1+1; } // i*2+1
    //获取某个index的右子节点
    private int rightChild(int i) { return (i<<1)+2; } // i*2+2

    public BinaryHeap(int capacity) {
        this.capacity= capacity;
        intArray = new int[capacity];
        intArray[0]=-1;
        heapSize = 0;
    }

    //交换数组中的两个index
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i]=array[j];
        array[j]=temp;
    }

    //递归shift某个节点，将大值移动到最上面
    private void shiftUp(int i) {
        if (i == 0) return; // 如果是root，则不作操作
        if (intArray[i] > intArray[parent(i)]) { // 子节点大于父节点，则需要进行swap操作
            swap(intArray, i, parent(i)); // 将子节点和父节点进行互换
            shiftUp(parent(i)); // 递归shift父节点
        }
    }

    //递归shift某个节点，将小值移动到最下面
    //比较过程是先比较左子节点，再比较右子节点
    private void shiftDown(int i) {
        if (i >= heapSize) return; // 超出了数组存储范围，直接返回
        int swapId = i; //要互换的id
        //如果存在左子节点，并且当前节点小于左子节点，则将要互换的id设置为左子节点
        if (leftChild(i) <= heapSize-1 && intArray[i] < intArray[leftChild(i)]){
            swapId = leftChild(i);
        }
        //如果存在右子节点，并且互换的id小于右子节点，则将互换的id设置为右子节点
        if (rightChild(i) <= heapSize-1 && intArray[swapId] < intArray[rightChild(i)]){
            swapId = rightChild(i);
        }
        if (swapId != i) { // 需要互换
            swap(intArray, i, swapId); // 进行互换
            shiftDown(swapId); // 递归要互换的节点
        }
    }

    public boolean isFull(){
        return heapSize == intArray.length;
    }

    //扩展heap,这里我们采用倍增的方式
    private void expandHeap(){
        int[] expandedArray = new int[capacity* 2];
        System.arraycopy(intArray,0, expandedArray,0, capacity);
        capacity= capacity*2;
        intArray= expandedArray;
    }

    //heap插入，插入到最后的位置，然后进行shift up操作
    public void insert(int x) {
        if(isFull()){
            expandHeap();
        }
        heapSize++;
        intArray[heapSize-1]= x; // 插入最后位置
        shiftUp(heapSize-1); // shift up

    }

    //获取最大的数据，
    public int extractMax() {
        int taken = intArray[0]; //root 就是最大的值
        swap(intArray, 0, heapSize-1); // 将root和最后一个值进行互换
        heapSize--;
        shiftDown(0); // 递归执行shift Down操作
        return taken; // 返回root的值
    }

    public int getMax() {
        return intArray[0];
    }

    public Boolean isEmpty() {
        return heapSize == 0;
    }

    public void printHeap(){
        for(int totalIndex=0;totalIndex<heapSize;totalIndex++){
            System.out.print(intArray[totalIndex]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BinaryHeap pq = new BinaryHeap(20);
        pq.insert(5);
        pq.printHeap();
        pq.insert(2);
        pq.insert(7);
        pq.insert(3);
        pq.printHeap();
        pq.extractMax(); // should be 7
        pq.printHeap();
        pq.insert(1);
        pq.printHeap();
        pq.extractMax(); // should be 5
        pq.printHeap();
        pq.insert(8);
        pq.printHeap();
        pq.extractMax(); // should be 8 now
        pq.printHeap();
    }

}
