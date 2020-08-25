package com.flydean;

/**
 * 斐波那契数列
 * @author wayne
 * @version Fibonacci,  2020/8/23
 */
public class Fibonacci {

    public int f(int n){
        if (n <= 1) /* base case */
            return n;
        else /* recursive caseS */
            return f(n-1) + f(n-2);
    }
}
