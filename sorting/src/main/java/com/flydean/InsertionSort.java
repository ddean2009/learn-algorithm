package com.flydean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wayne
 * @version InsertionSort
 *
 *
 **/
@Slf4j
public class InsertionSort {

    public void doInsertSort(int[] array){
        log.info("排序前的数组为:{}",array);
        int n = array.length;
        //从第二个元素开始插入
        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;
            //j表示的是要插入元素之前的已经排好序的数组
            //已经排好序的数组，从后向前和要插入的数据比较，如果比要插入的数据大，需要右移一位
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                    j = j - 1;
            }
            //最后的j+1的位置就是需要插入新元素的位置
            array[j + 1] = key;
            log.info("第{}轮排序后的数组为:{}", i+1, array);
        }

    }

    public static void main(String[] args) {
        int[] array= {29,10,14,37,20,25,44,15};
        InsertionSort insertionSort=new InsertionSort();
        insertionSort.doInsertSort(array);
    }
}
