package com.flydean.easy;

/**
 * @author wayne
 * @version No69mySqrt,  2020/8/25
 *
 * x 的平方根
 * 实现 int sqrt(int x) 函数。
 *
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 *
 * 输入: 4
 * 输出: 2
 * 示例 2:
 *
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 */
public class No69mySqrt {

    /**
     * 袖珍计算器算法
     * 「袖珍计算器算法」是一种用指数函数 \expexp 和对数函数 \lnln 代替平方根函数的方法。我们通过有限的可以使用的数学函数，得到我们想要计算的结果。
     *
     * 我们将 sqrt{x}
     *   写成幂的形式 x^{1/2}
     *  ，再使用自然对数 ee 进行换底，即可得到
     *
     *  在得到结果的整数部分 \textit{ans}ans 后，我们应当找出 \textit{ans}ans 与 \textit{ans} + 1ans+1 中哪一个是真正的答案。
     */

    public int mySqrt1(int x) {
        if (x == 0) {
            return 0;
        }
        int ans = (int)Math.exp(0.5 * Math.log(x));
        return (long)(ans + 1) * (ans + 1) <= x ? ans + 1 : ans;
    }

    /**
     * 二分查找
     * 由于 xx 平方根的整数部分 ans 是满足 k^2 <=2
     * 的最大 k 值，因此我们可以对 k 进行二分查找，从而得到答案。
     *
     * 二分查找的下界为 0，上界可以粗略地设定为 x。在二分查找的每一步中，
     * 我们只需要比较中间元素 mid 的平方与 x 的大小关系，并通过比较的结果调整上下界的范围。
     * 由于我们所有的运算都是整数运算，不会存在误差，因此在得到最终的答案 ans 后，也就不需要再去尝试 ans+1 了。
     *
     */

    public int mySqrt2(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long)mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }
        return ans;
    }




}
