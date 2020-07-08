package com.flydean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wayne
 * @version BubbleSort
 */
@Slf4j
public class BubbleSort2 {

    public void doBubbleSort(int[] array){
        log.info("排序前的数组为:{}",array);
        //外层循环,遍历所有轮数
        for(int i=0; i< array.length-1; i++){
            //添加一个flag，如果这一轮都没有排序，说明排序已经结束，可以提前退出
            boolean flag=false;
            //内层循环，两两比较，选中较大的数字，进行交换, 最后的i个数字已经排完序了，不需要再进行比较
            for(int j=0; j<array.length-i-1; j++){
                if(array[j]>array[j+1]){
                    //交换两个数字
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    flag = true;
                }
            }
            log.info("第{}轮排序后的数组为:{}", i+1, array);
            if(!flag)
            {
                log.info("本轮未发生排序变化，排序结束");
                return;
            }
        }
    }

    public static void main(String[] args) {
        int[] array= {29,10,14,37,20,25,44,15};
        BubbleSort2 bubbleSort=new BubbleSort2();
        bubbleSort.doBubbleSort(array);
    }

}

