package com.flydean;

import java.util.ArrayList;

/**
 * FenwickTree也叫做BIT(Binary Indexed Tree),树状数组
 * 是一种用于高效处理对一个存储数字的列表进行更新及求前缀和的数据结构。
 * @author wayne
 * @version FenwickTree,  2020/8/22
 */
public class FenwickTree {

    private ArrayList<Integer> ft;

    private int lowBitOne(int S) { return (S & (-S)); }

    public FenwickTree() {}

    // 初始化数组，值为0
    public FenwickTree(int n) {
        ft = new ArrayList<>();
        for (int i = 0; i <= n; i++) ft.add(0);
    }

    public int rangeSumQuery(int j) {                              // 范围查询 1 - j
        int sum = 0; for (; j > 0; j -= lowBitOne(j)) {
            sum += ft.get(j);
        }
        return sum;
    }

    public int rangeSumQuery(int i, int j) {                       // 范围查询 i - j
        return rangeSumQuery(j) - rangeSumQuery(i-1);
    }

    // 构造FenwickTree，更新相应的值
    void update(int i, int v) {
        for (; i < ft.size(); i += lowBitOne(i)){
            ft.set(i, ft.get(i)+v);
        }
    }

    public static void main(String[] args) {
        // idx   0 1 2 3 4 5 6 7  8 9 10, no index 0!
        FenwickTree ft = new FenwickTree(10); // ft = {-,0,0,0,0,0,0,0, 0,0,0}
        ft.update(2, 1);                      // ft = {-,0,1,0,1,0,0,0, 1,0,0}, idx 2,4,8 => +1
        ft.update(4, 1);                      // ft = {-,0,1,0,2,0,0,0, 2,0,0}, idx 4,8 => +1
        ft.update(5, 2);                      // ft = {-,0,1,0,2,2,2,0, 4,0,0}, idx 5,6,8 => +2
        ft.update(6, 3);                      // ft = {-,0,1,0,2,2,5,0, 7,0,0}, idx 6,8 => +3
        ft.update(7, 2);                      // ft = {-,0,1,0,2,2,5,2, 9,0,0}, idx 7,8 => +2
        ft.update(8, 1);                      // ft = {-,0,1,0,2,2,5,2,10,0,0}, idx 8 => +1
        ft.update(9, 1);                      // ft = {-,0,1,0,2,2,5,2,10,1,1}, idx 9,10 => +1
        System.out.printf("%d\n", ft.rangeSumQuery(1, 1));  // 0 => ft[1] = 0
        System.out.printf("%d\n", ft.rangeSumQuery(1, 2));  // 1 => ft[2] = 1
        System.out.printf("%d\n", ft.rangeSumQuery(1, 6));  // 7 => ft[6] + ft[4] = 5 + 2 = 7
        System.out.printf("%d\n", ft.rangeSumQuery(1, 10)); // 11 => ft[10] + ft[8] = 1 + 10 = 11
        System.out.printf("%d\n", ft.rangeSumQuery(3, 6));  // 6 => rsq(1, 6) - rsq(1, 2) = 7 - 1

        ft.update(5, 2); // update demo
        System.out.printf("%d\n", ft.rangeSumQuery(1, 10)); // now 13
    }

}
