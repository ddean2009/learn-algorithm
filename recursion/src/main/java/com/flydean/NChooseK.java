package com.flydean;

/**
 * N中选K
 * @author wayne
 * @version NChooseK,  2020/8/24
 */
public class NChooseK {

    public int f(int n, int k){
        if (k == 0 || k == n) /* base caseS */
            return 1;
        else /* recursive caseS */
            return f(n-1, k-1) + /* take */
                    f(n-1, k); /* not take */
    }
}
