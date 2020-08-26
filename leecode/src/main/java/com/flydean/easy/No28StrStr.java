package com.flydean.easy;

/**
 * 实现 strStr()
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 *
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 *
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 * @author wayne
 * @version No28StrStr,  2020/8/25
 */
public class No28StrStr {

    /**
     * 方法一：子串逐一比较 - 线性时间复杂度
     * 最直接的方法 - 沿着字符换逐步移动滑动窗口，将窗口内的子串与 needle 字符串比较。
     *
     * 时间复杂度：O((N−L)L)，其中 N 为 haystack 字符串的长度，L 为 needle 字符串的长度。内循环中比较字符串的复杂度为 L，总共需要比较 (N - L) 次。
     *
     * 空间复杂度：O(1)。
     */

    public int strStr1(String haystack, String needle) {
        int L = needle.length(), n = haystack.length();

        for (int start = 0; start < n - L + 1; ++start) {
            if (haystack.substring(start, start + L).equals(needle)) {
                return start;
            }
        }
        return -1;
    }

    /**
     * 方法二：双指针 - 线性时间复杂度
     * 上一个方法的缺陷是会将 haystack 所有长度为 L 的子串都与 needle 字符串比较，实际上是不需要这么做的。
     *
     * 首先，只有子串的第一个字符跟 needle 字符串第一个字符相同的时候才需要比较。
     *
     * 算法
     *
     * 移动 pn 指针，直到 pn 所指向位置的字符与 needle 字符串第一个字符相等。
     *
     * 通过 pn，pL，curr_len 计算匹配长度。
     *
     * 如果完全匹配（即 curr_len == L），返回匹配子串的起始坐标（即 pn - L）。
     *
     * 如果不完全匹配，回溯。使 pn = pn - curr_len + 1， pL = 0， curr_len = 0。
     *
     *时间复杂度：最坏时间复杂度为 O((N - L)L)，最优时间复杂度为 O(N)。
     *
     * 空间复杂度：O(1)。
     */

    public int strStr2(String haystack, String needle) {
        int L = needle.length(), n = haystack.length();
        if (L == 0) return 0;

        int pn = 0;
        while (pn < n - L + 1) {
            // find the position of the first needle character
            // in the haystack string
            while (pn < n - L + 1 && haystack.charAt(pn) != needle.charAt(0)) ++pn;

            // compute the max match string
            int currLen = 0, pL = 0;
            while (pL < L && pn < n && haystack.charAt(pn) == needle.charAt(pL)) {
                ++pn;
                ++pL;
                ++currLen;
            }

            // if the whole needle string is found,
            // return its start position
            if (currLen == L) return pn - L;

            // otherwise, backtrack
            pn = pn - currLen + 1;
        }
        return -1;
    }


    /**
     * 方法三： Rabin Karp - 常数复杂度
     * 有一种最坏时间复杂度也为 O(N) 的算法。思路是这样的，先生成窗口内子串的哈希码，然后再跟 needle 字符串的哈希码做比较。
     *
     * 这个思路有一个问题需要解决，如何在常数时间生成子串的哈希码？
     *
     * 滚动哈希：常数时间生成哈希码
     *
     * 生成一个长度为 L 数组的哈希码，需要 O(L) 时间。
     *
     * 如何在常数时间生成滑动窗口数组的哈希码？利用滑动窗口的特性，每次滑动都有一个元素进，一个出。
     *
     * 由于只会出现小写的英文字母，因此可以将字符串转化成值为 0 到 25 的整数数组： arr[i] = (int)S.charAt(i) - (int)'a'。按照这种规则，abcd 整数数组形式就是 [0, 1, 2, 3]，转换公式如下所示。
     *
     * 下面来考虑窗口从 abcd 滑动到 bcde 的情况。这时候整数形式数组从 [0, 1, 2, 3] 变成了 [1, 2, 3, 4]，数组最左边的 0 被移除，同时最右边新添了 4。
     *
     * 滑动后数组的哈希值可以根据滑动前数组的哈希值来计算，计算公式如下所示。
     *
     * 时间复杂度：O(N)，计算 needle 字符串的哈希值需要 O(L) 时间，之后需要执行 (N - L) 次循环，每次循环的计算复杂度为常数。
     *
     * 空间复杂度：O(1)。
     *
     * @param idx
     * @param s
     * @return
     */

    // function to convert character to integer
    public int charToInt(int idx, String s) {
        return (int)s.charAt(idx) - (int)'a';
    }

    public int strStr3(String haystack, String needle) {
        int L = needle.length(), n = haystack.length();
        if (L > n) return -1;

        // base value for the rolling hash function
        int a = 26;
        // modulus value for the rolling hash function to avoid overflow
        long modulus = (long)Math.pow(2, 31);

        // compute the hash of strings haystack[:L], needle[:L]
        long h = 0, ref_h = 0;
        for (int i = 0; i < L; ++i) {
            h = (h * a + charToInt(i, haystack)) % modulus;
            ref_h = (ref_h * a + charToInt(i, needle)) % modulus;
        }
        if (h == ref_h) return 0;

        // const value to be used often : a**L % modulus
        long aL = 1;
        for (int i = 1; i <= L; ++i) aL = (aL * a) % modulus;

        for (int start = 1; start < n - L + 1; ++start) {
            // compute rolling hash in O(1) time
            h = (h * a - charToInt(start - 1, haystack) * aL
                    + charToInt(start + L - 1, haystack)) % modulus;
            if (h == ref_h) return start;
        }
        return -1;
    }



}
