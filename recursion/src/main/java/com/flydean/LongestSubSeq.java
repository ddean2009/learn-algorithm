package com.flydean;

/**
 * 数组的最长递增子序列
 * @author wayne
 * @version LongestSubSeq,  2020/8/24
 */
public class LongestSubSeq {

    public int f(int i){
        int[] numbers = new int[]{-7,10,9,2,3,8,8,1};
        if (i == 0) return 1; /* base case */
        /* recursive caseS */
        var ans = 1;
        for (var j = 0; j < i; j++)
            if (numbers[j] < numbers[i])
                ans = Math.max(ans, f(j)+1);
        return ans;
    }

    public static void main(String[] args) {
        LongestSubSeq longestSubSeq= new LongestSubSeq();
        System.out.println(longestSubSeq.f(4));
    }

}
