package com.flydean;

/**
 * @author wayne
 * @version HeapSort,  2020/7/22
 */
public class HeapSort {

    public void sort(int arr[])
    {
        int n = arr.length;

        // 构建二叉堆
        for (int i = n / 2 - 1; i >= 0; i--)
            shiftDown(arr, n, i);

        // 一个一个取出最大的元素，并放置在数组的末尾
        for (int i=n-1; i>0; i--)
        {
            //将最大的元素放在数组末尾
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // 重新构建新的二叉堆
            shiftDown(arr, i, 0);
        }
    }

    // shift down arr, 需要传入arraySize和开始的nodeIndex
    void shiftDown(int[] arr, int arraySize, int nodeIndex)
    {
        int largest = nodeIndex; // 最大值的index
        int left = 2*nodeIndex + 1; // left = 2*i + 1
        int right = 2*nodeIndex + 2; // right = 2*i + 2

        // 如果left子节点大于父节点
        if (left < arraySize && arr[left] > arr[largest])
            largest = left;

        // 如果right子节点大于父节点
        if (right < arraySize && arr[right] > arr[largest])
            largest = right;

        // 需要交换
        if (largest != nodeIndex)
        {
            int swap = arr[nodeIndex];
            arr[nodeIndex] = arr[largest];
            arr[largest] = swap;

            // 递归交换子节点
            shiftDown(arr, arraySize, largest);
        }
    }

    //输出数组
    static void printArray(int[] arr)
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }


    public static void main(String args[])
    {
        int arr[] = {12, 11, 13, 5, 6, 7};

        HeapSort ob = new HeapSort();
        ob.sort(arr);

        System.out.println("Sorted array is");
        printArray(arr);
    }
}
