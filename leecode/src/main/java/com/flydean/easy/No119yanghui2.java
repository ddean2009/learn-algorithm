package com.flydean.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wayne
 * @version No119yanghui2,  2020/8/26
 *
 * 杨辉三角 II
 *
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *
 *
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 3
 * 输出: [1,3,3,1]
 * 进阶：
 *
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 */
public class No119yanghui2 {

    /**
     * 公式法
     * 如果输入的行数是n，那么该行内容依次是：1、(n-1)/1、(n-1)(n-2)/2、(n-1)(n-2)(n-3)/3...
     */

    public List<Integer> getRow(int rowIndex) {
        rowIndex++;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < rowIndex; i++) {
            if (i == 0) {
                list.add(1);
            } else {
                long num = (long)list.get(i - 1) * (long)(rowIndex - i) / i;
                list.add((int)num);
            }
        }
        return list;
    }
}
