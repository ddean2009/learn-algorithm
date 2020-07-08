package com.flydean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wayne
 * @version SelectionSort
 */
@Slf4j
public class SelectionSort1 {

    public void doSelectionSort(int[] array){
        log.info("排序前的数组为:{}",array);
        //外层循环,遍历所有轮数
        for(int i=0; i< array.length-1; i++){
            //内层循环，找出最大的那个数字
            int maxIndex=0;
            for(int j=0;j<array.length-i;j++)
            {
                if(array[j] > array[maxIndex])
                {
                    maxIndex = j;
                }
            }
            //每次选择完成后，将maxIndex所在元素和length-i-1的元素互换
            int temp = array[array.length-i-1];
            array[array.length-i-1] = array[maxIndex];
            array[maxIndex] = temp;
            log.info("第{}轮排序后的数组为:{}", i+1, array);
        }
    }

    public static void main(String[] args) {
        int[] array= {29,10,14,37,20,25,44,15};
        SelectionSort1 selectionSort=new SelectionSort1();
        selectionSort.doSelectionSort(array);
    }
}
