package com.flydean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wayne
 * @version BubbleSort
 */
@Slf4j
public class BubbleSort {

    public void doBubbleSort(int[] array){
        log.info("排序前的数组为:{}",array);
        //外层循环,遍历所有轮数
        for(int i=0; i< array.length-1; i++){
            //内层循环，两两比较，选中较大的数字，进行交换
            for(int j=0; j<array.length-1; j++){
                if(array[j]>array[j+1]){
                    //交换两个数字
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
            log.info("第{}轮排序后的数组为:{}", i+1, array);
        }
    }

    public static void main(String[] args) {
        int[] array= {29,10,14,37,20,25,44,15};
        BubbleSort bubbleSort=new BubbleSort();
        bubbleSort.doBubbleSort(array);
    }

}

