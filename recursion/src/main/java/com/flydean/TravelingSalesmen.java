package com.flydean;

/**
 * @author wayne
 * @version TravelingSalesmen,  2020/8/24
 */
public class TravelingSalesmen {

    public int f(int u, int m){
        int[][] arrays = new int[][]{{0, 20, 42, 35}, {20, 0, 30, 34}, {42, 30, 0, 12}, {35, 34, 12, 0}};
        if (m == (1<<4)-1) return arrays[u][0];
        var ans = 99; /* recursive caseS */
        for (var v = 0; v < 4; v++)
            if (v != u && ((m & (1<<v)) == 0))
                ans = Math.min(ans, arrays[u][v] + f(v, m | (1<<v)));
        return ans;
    }

    public static void main(String[] args) {
        TravelingSalesmen travelingSalesmen= new TravelingSalesmen();
        System.out.println(travelingSalesmen.f(0,1));
    }
}
