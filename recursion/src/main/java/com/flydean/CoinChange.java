package com.flydean;

/**
 * 硬币兑换问题
 * @author wayne
 * @version CoinChange,  2020/8/24
 */
public class CoinChange {

    public int f(int v){
        int[] coins = new int[]{1,3,4,5};
        if (v == 0) return 0; /* base case */
        /* recursive caseS */
        var ans = 99; //设置一个最大值
        for (var i = 0; i < 4; i++)
            if (v-coins[i] >= 0)
                ans = Math.min(ans, 1 + f(v-coins[i]));
        return ans;
    }
}
