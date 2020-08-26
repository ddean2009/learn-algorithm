package com.flydean.easy;

/**
 * @author wayne
 * @version No53maxSubArray,  2020/8/25
 *
 * 最大子序和
 *
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class No53maxSubArray {

    /**
     * 动态规划
     * 思路和算法
     *
     * 假设 nums 数组的长度是 n，下标从 0 到 n−1。
     *
     * 我们用 a[i]
     *   代表 nums[i]，用 f(i) 代表以第 i 个数结尾的「连续子数组的最大和」，那么很显然我们要求的答案就是：
     *   max{f(i)}
     *
     *   因此我们只需要求出每个位置的 f(i)，然后返回 f 数组中的最大值即可。那么我们如何求 f(i) 呢？我们可以考虑 a[i]
     *   单独成为一段还是加入 f(i−1) 对应的那一段，这取决于 a[i] 和 f(i - 1) + a[i]
     *   的大小，我们希望获得一个比较大的，于是可以写出这样的动态规划转移方程：
     *   f(i)=max{f(i−1)+a }
     *
     */

    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i- 1] + nums[i], nums[i]);
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        return max;
    }

    /**
     * 方法2
     * 动态规划的是首先对数组进行遍历，当前最大连续子序列和为 sum，结果为 ans
     * 如果 sum > 0，则说明 sum 对结果有增益效果，则 sum 保留并加上当前遍历数字
     * 如果 sum <= 0，则说明 sum 对结果无增益效果，需要舍弃，则 sum 直接更新为当前遍历数字
     * 每次比较 sum 和 ans的大小，将最大值置为ans，遍历结束返回结果
     */

    public int maxSubArray2(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for(int num: nums) {
            if(sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
