package com.flydean;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author wayne
 * @version RandomQuickSort
 */
@Slf4j
public class RandomQuickSort {

    /**
     * partition方法的主要作用就是以中心节点为界，将数组分为两部分，
     * 左边部分小于中心节点，右边部分大于中心节点
     * @param array
     * @param i
     * @param j
     * @return
     */
    private int partition(int[] array, int i, int j) {
        //随机选择一个元素作为中心点,middleValue就是中心点的值
        int randomIndex=i+new Random().nextInt(j-i);
        log.info("randomIndex:{}",randomIndex);
        //首先将randomIndex的值和i互换位置,就可以复用QuickSort的逻辑
        swap(array, i , randomIndex);
        int middleValue = array[i];
        int middleIndex = i;
        //从i遍历整个数组
        for (int k = i+1; k <= j; k++) {
            //如果数组元素小于middleValue，表示middleIndex需要右移一位
            //右移之后，我们需要将小于middleValue的array[k]移动到middleIndex的左边，
            // 最简单的办法就是交换k和middleIndex的值
            if (array[k] < middleValue) {
                middleIndex++;
                //交换数组的两个元素
                swap(array, k , middleIndex);
            } //如果数组元素大于等于middleValue,则继续向后遍历,middleIndex值不变
        }
        // 最后将中心点放入middleIndex位置
        swap(array, i, middleIndex);
        return middleIndex;
    }

    /**
     * 交互数组的两个元素
     * @param array
     * @param i
     * @param m
     */
    private void swap(int[] array, int i, int m){
        int temp = array[i];
        array[i] = array[m];
        array[m] = temp;
    }

    public void doQuickSort(int[] array, int low, int high) {
        //递归的结束条件
        if (low < high) {
            //找出中心节点的值
            int middleIndex = partition(array, low, high);
            //数组分成了三部分：
            // a[low..high] ~> a[low..m–1], pivot, a[m+1..high]
            //递归遍历左侧部分
            doQuickSort(array, low, middleIndex-1);
            // a[m] 是中心节点，已经排好序了，不需要继续遍历
            //递归遍历右侧部分
            doQuickSort(array, middleIndex+1, high);
            log.info("QuickSort之后的数组:{}",array);
        }
    }

    public static void main(String[] args) {
        int[] array= {29,10,14,37,20,25,44,15};
        RandomQuickSort quickSort=new RandomQuickSort();
        log.info("QuickSort之前的数组为:{}",array);
        quickSort.doQuickSort(array,0, array.length-1);
    }
}
