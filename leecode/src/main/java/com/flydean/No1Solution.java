package com.flydean;

import java.util.HashMap;

/**
 * @author wayne
 * @version No1Solution,  2020/8/19
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 通过以空间换取速度的方式，我们可以将查找时间从 O(n) 降低到 O(1)。
 * 哈希表正是为此目的而构建的，它支持以 近似 恒定的时间进行快速查找。我用“近似”来描述，是因为一旦出现冲突，查找用时可能会退化到 O(n)。
 * 但只要你仔细地挑选哈希函数，在哈希表中进行查找的用时应当被摊销为 O(1)。
 * 一个简单的实现使用了两次迭代。在第一次迭代中，我们将每个元素的值和它的索引添加到表中。
 * 然后，在第二次迭代中，我们将检查每个元素所对应的目标元素（target - nums[i]）是否存在于表中。
 * 注意，该目标元素不能是 nums[i]本身！
 *
 * 复杂度分析：
 *
 * 时间复杂度：O(n)，
 * 我们把包含有 n 个元素的列表遍历两次。由于哈希表将查找时间缩短到 O(1) ，所以时间复杂度为 O(n)。
 *
 * 空间复杂度：O(n)，
 * 所需的额外空间取决于哈希表中存储的元素数量，该表中存储了 n 个元素。
 *
 */
public class No1Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result=new int[2];
        HashMap<Integer, Integer> map= new HashMap();
        for(int i=0; i< nums.length; i++){
            map.put(nums[i],i);
        }
        for(int i=0; i< nums.length; i++){
            int number= target-nums[i];
            if(map.containsKey(number) && map.get(number) != i){
                result[1]=i;
                result[0]=map.get(number);
            }
        }
        return result;
    }
}
