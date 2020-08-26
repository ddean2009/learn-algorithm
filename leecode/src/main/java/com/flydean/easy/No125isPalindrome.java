package com.flydean.easy;

/**
 * @author wayne
 * @version No125isPalindrome,  2020/8/26
 *
 * 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 */
public class No125isPalindrome {

    /**
     * 方法一：筛选 + 判断
     * 最简单的方法是对字符串 ss 进行一次遍历，并将其中的字母和数字字符进行保留，放在另一个字符串 sgood 中。
     * 这样我们只需要判断 sgood 是否是一个普通的回文串即可。
     *
     * 判断的方法有两种。第一种是使用语言中的字符串翻转 API 得到 sgood 的逆序字符串 sgood_rev，
     * 只要这两个字符串相同，那么 sgood 就是回文串。
     *
     */

    public boolean isPalindrome(String s) {
        StringBuffer sgood = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sgood.append(Character.toLowerCase(ch));
            }
        }
        StringBuffer sgood_rev = new StringBuffer(sgood).reverse();
        return sgood.toString().equals(sgood_rev.toString());
    }

    /**
     * 第二种是使用双指针。初始时，左右指针分别指向 sgood 的两侧，随后我们不断地将这两个指针相向移动，
     * 每次移动一步，并判断这两个指针指向的字符是否相同。当这两个指针相遇时，就说明 sgood 时回文串。
     *
     */

    public boolean isPalindrome2(String s) {
        StringBuffer sgood = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sgood.append(Character.toLowerCase(ch));
            }
        }
        int n = sgood.length();
        int left = 0, right = n - 1;
        while (left < right) {
            if (Character.toLowerCase(sgood.charAt(left)) != Character.toLowerCase(sgood.charAt(right))) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }

    /**
     * 方法二：在原字符串上直接判断
     * 我们可以对方法一中第二种判断回文串的方法进行优化，就可以得到只使用 O(1)O(1) 空间的算法。
     *
     * 我们直接在原字符串 ss 上使用双指针。在移动任意一个指针时，需要不断地向另一指针的方向移动，
     * 直到遇到一个字母或数字字符，或者两指针重合为止。也就是说，我们每次将指针移到下一个字母字符或数字字符，
     * 再判断这两个指针指向的字符是否相同。
     *
     */

    public boolean isPalindrome3(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
        }
        return true;
    }



}
