package com.flydean;

/**
 * 0-1 背包问题
 * @author wayne
 * @version knapsack,  2020/8/24
 */
public class Knapsack {

    /**
     *
     * @param count  背包中的元素个数
     * @param packageWeight 背包能够容纳的总重量
     * @return
     */
    public int f (int count, int packageWeight){
        int[] values= new int[]{100, 70, 50, 10};
        int[] weights=new int[]{10, 4, 6, 12};

        /* base caseS */
        if (packageWeight == 0 || count < 0) return 0;
        else if (weights[count] > packageWeight) return f(count-1, packageWeight);
        return Math.max(
                values[count] + f(count-1, packageWeight-weights[count]), /* take */
                f(count-1, packageWeight)); /* not take */

    }
}
