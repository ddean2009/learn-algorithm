package com.flydean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wayne
 * @version SelectionSort
 */
@Slf4j
public class SelectionSort {

    public void doSelectionSort(int[] array){
        log.info("排序前的数组为:{}",array);
        //外层循环,遍历所有轮数
        for(int i=0; i< array.length-1; i++){
            //内层循环，找出最小的那个数字
            int minIndex=i;
            for(int j=i+1;j<array.length;j++)
            {
                if(array[j] < array[minIndex])
                {
                    minIndex = j;
                }
            }
            //每次选择完成后，将minIndex所在元素和第i个元素互换
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
            log.info("第{}轮排序后的数组为:{}", i+1, array);
        }
    }

    public static void main(String[] args) {
        int[] array= {29,10,14,37,20,25,44,15};
        SelectionSort selectionSort=new SelectionSort();
        selectionSort.doSelectionSort(array);
    }
}
