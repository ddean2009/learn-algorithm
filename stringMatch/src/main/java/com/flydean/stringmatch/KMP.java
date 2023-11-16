package com.flydean.stringmatch;

/**
 * Knuth-Morris-Pratt（KMP）算法是一种用于在一个文本串中查找一个模式串出现位置的字符串匹配算法。它的特点在于避免了对于每个位置的匹配都回溯到模式串的开头，从而减少了比较的次数，提高了匹配效率。
 * <p>
 * ### KMP算法的基本思想：
 * 1. **预处理模式串：** KMP算法首先对模式串进行预处理，得到一个部分匹配表（Partial Match Table），记为`next[]`。这个表用于指导匹配过程中的跳跃。
 * <p>
 * 2. **匹配过程：** 在匹配过程中，当发生不匹配时，根据部分匹配表中的信息，调整模式串的位置，使得不必回溯到模式串的开头，从而提高匹配效率。
 * <p>
 * ### 部分匹配表 `next[]` 的构建：
 * - `next[i]` 表示当第 `i` 个字符不匹配时，模式串应该跳跃的位置。
 * - 对于模式串 `p`，`next[0] = -1`，`next[1] = 0`。
 * - 对于 `i > 1`，若 `p[0...k-1]` 是 `p[0...i-1]` 的最大相同前缀后缀，令 `next[i] = k`，否则令 `k = next[k]`。
 * <p>
 * ### KMP算法步骤：
 * 1. 初始化文本串指针 `i` 和模式串指针 `j`。
 * 2. 若当前字符匹配，则 `i` 和 `j` 同时后移。
 * 3. 若当前字符不匹配，根据 `next[j]` 调整 `j` 的位置。
 * 4. 重复步骤2-3，直到找到匹配或文本串遍历完。
 * <p>
 * ### 算法复杂度：
 * - KMP算法的时间复杂度是 O(n + m)，其中 n 是文本串的长度，m 是模式串的长度。
 * - 部分匹配表的构建时间是 O(m)。
 * <p>
 * KMP算法在大规模文本匹配中具有较高的效率，尤其在一些大数据处理场景下表现优越。
 */

import java.util.Arrays;

public class KMP {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDAB";
        int[] next = KMP_next(str2);
        System.out.println("next=" + Arrays.toString(next));
        int index = KmpSearch(str1, str2);
        System.out.println(index);
    }

    //KMP搜索算法
    public static int KmpSearch(String str1, String str2) {
        int[] next = KMP_next(str2);
        //遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取到一个字符串的部分匹配值
    public static int[] KMP_next(String dest) {
        //创建一个数组next，保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串是长度为1 部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(j) != dest.charAt(i)，我们需要从next[j-1]获取新的j
            //知道我们发现有dest.charAt(j) == dest.charAt(i)成立才停止
            while (j > 0 && dest.charAt(j) != dest.charAt(i)) {
                j = next[j - 1];
            }
            //当dest.charAt(j) == dest.charAt(i)满足时，部分匹配值就是+1
            if (dest.charAt(j) == dest.charAt(i)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}

