package com.flydean;

/**
 * @author wayne
 * @version GCD,  2020/8/24
 */
public class GCD {

    public int f(int a, int b){
        if (b == 0) /* base case */
            return a;
        else /* recursive case */
            return f(b, a%b);
    }
}
