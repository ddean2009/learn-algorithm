package com.flydean;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author wayne
 * @version RadixSort
 */
@Slf4j
public class RadixSort {

    public void doRadixSort(int[] array, int digit){
        int n = array.length;

        // 存储排序过后的数组
        int output[] = new int[n];

        // count数组，用来存储统计各个元素出现的次数
        int count[] = new int[10];
        Arrays.fill(count,0);
        log.info("初始化count值:{}",count);

        // 将原始数组中数据出现次数存入count数组
        for (int i=0; i<n; ++i) {
            count[(array[i]/digit)%10]++;
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
            output[count[(array[i]/digit)%10]-1] = array[i];
            count[(array[i]/digit)%10]--;
        }
        log.info("构建output之后的output值:{}",output);

        //将排序后的数组写回原数组
        for (int i = 0; i<n; ++i)
            array[i] = output[i];
    }

    public int getMax(int[] array)
    {
        int mx = array[0];
        for (int i = 1; i < array.length; i++)
            if (array[i] > mx){
                mx = array[i];
            }
        return mx;
    }

    public static void main(String[] args) {
        int[] array= {1221, 15, 20, 3681, 277, 5420, 71, 1522, 4793};
        RadixSort radixSort=new RadixSort();
        log.info("radixSort之前的数组为:{}",array);
        //拿到数组的最大值，用于计算digit
        int max = radixSort.getMax(array);
        //根据位数，遍历进行count排序
        for (int digit = 1; max/digit > 0; digit *= 10){
            radixSort.doRadixSort(array,digit);
        }
    }
}
