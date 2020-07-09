package com.flydean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wayne
 * @version CountingSort
 */
@Slf4j
public class CountingSort1 {

    public void doCountingSort(int[] array){
        int n = array.length;

        // 存储排序过后的数组
        int output[] = new int[n];

        // count数组，用来存储统计各个元素出现的次数
        int count[] = new int[10];
        for (int i=0; i<10; ++i) {
            count[i] = 0;
        }
        log.info("初始化count值:{}",count);

        // 将原始数组中数据出现次数存入count数组
        for (int i=0; i<n; ++i) {
            ++count[array[i]];
        }
        log.info("count之后count值:{}",count);
        // 这里是一个小技巧，我们根据count中元素出现的次数计算对应元素第一次应该出现在output中的下标。
        //这里的下标是从右往左数的
        for (int i=1; i<10; i++) {
            count[i] += count[i - 1];
        }
        log.info("整理count对应的output下标:{}",count);
        // 根据count中的下标，构建排序后的数组
        //插入一个之后，相应的count下标要减一
        for (int i = n-1; i>=0; i--)
        {
            output[count[array[i]]-1] = array[i];
            --count[array[i]];
        }
        log.info("构建output之后的output值:{}",output);

        //将排序后的数组写回原数组
        for (int i = 0; i<n; ++i)
            array[i] = output[i];
    }

    public static void main(String[] args) {
        int[] array= {3,4,2,5,6,2,4,9,1,3,5};
        CountingSort1 countingSort=new CountingSort1();
        log.info("countingSort之前的数组为:{}",array);
        countingSort.doCountingSort(array);
    }
}
