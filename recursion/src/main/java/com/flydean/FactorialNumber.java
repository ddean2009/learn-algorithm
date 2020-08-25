package com.flydean;

/**
 * 阶乘
 * @author wayne
 * @version FactorialNumber,  2020/8/23
 */
public class FactorialNumber {

    public int f(int n){
        if (n <= 1) /* base case */
            return 1;
        else /* recursive case */
            return n*f(n-1);
    }

    public static void main(String[] args) {
        FactorialNumber number=new FactorialNumber();
        System.out.println(number.f(4));
    }
}
