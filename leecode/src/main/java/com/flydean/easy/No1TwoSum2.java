package com.flydean.easy;

import java.util.HashMap;

/**
 * @author wayne
 * @version No1Solution2,  2020/8/19
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 一遍哈希表
 * 在进行迭代并将元素插入到表中的同时，我们还会回过头来检查表中是否已经存在当前元素所对应的目标元素。
 * 如果它存在，那我们已经找到了对应解，并立即将其返回。
 */
public class No1TwoSum2 {
    public int[] twoSum(int[] nums, int target) {
        int[] result=new int[2];
        HashMap<Integer, Integer> map= new HashMap();
        for(int i=0; i< nums.length; i++){
            int number= target-nums[i];
            if(map.containsKey(number)){
                result[1]=i;
                result[0]=map.get(number);
            }
            map.put(nums[i],i);
        }
        return result;
    }
}
