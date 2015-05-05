package com.algochap1;

import java.util.Calendar;

/**
 * Binomial
 * Created by Nathan on 4/17/2015.
 */
public class Binomial {

    private static double[][] cacheArray;

    public static double binomialRecursive(int N, int k, double p) {
        if (N == 0 && k == 0) return 1.0;
        if (N < 0 || k < 0) return 0.0;
        return (1.0 - p) * binomialRecursive(N - 1, k, p) + p * binomialRecursive(N - 1, k - 1, p);
    }

    public static double binomialCache(int N, int k, double p) {
        if (cacheArray == null) {
            cacheArray = new double[N][k];
        }
        if (N == 0 && k == 0) return 1.0;
        if (N < 0 || k < 0) return 0.0;

        double valpart1, valpart2;
        int i = N - 2;
        int j = k - 1;
        if (i >= 0 && j >= 0) {
            if (cacheArray[N - 2][k - 1] == 0.0) {
                valpart1 = binomialCache(N - 1, k, p);
                cacheArray[N - 2][k - 1] = valpart1;
            }
            else {
                valpart1 = cacheArray[N - 2][k - 1];
            }
        }
        else {
            valpart1 = binomialCache(N - 1, k, p);
        }
        if (i >= 0 && (j - 1) >= 0) {
            if (cacheArray[N - 2][k - 2] == 0.0) {
                valpart2 = binomialCache(N - 1, k - 1, p);
                cacheArray[N - 2][k - 2] = valpart2;
            }
            else {
                valpart2 = cacheArray[N - 2][k - 2];
            }
        }
        else {
            valpart2 = binomialCache(N - 1, k - 1, p);
        }
        return (1.0 - p) * valpart1 + p * valpart2;
    }

    /*
    * */
    public static void main(String[] args) {
        // 100, 50, 0.25
        System.out.println("test1");
        long t1 = System.currentTimeMillis();
        double result = binomialRecursive(40, 10, 0.25);
        System.out.println(result);
        long t2 = System.currentTimeMillis();
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(t2 - t1);
        System.out.println("timespan: " + c.get(Calendar.MINUTE) + "minutes " + c.get(Calendar.SECOND) + "seconds " + c.get(Calendar.MILLISECOND) + " millis");

        System.out.println("test2");
        t1 = System.currentTimeMillis();
        result = binomialCache(40, 10, 0.25);
        System.out.println(result);
        t2 = System.currentTimeMillis();
        c.setTimeInMillis(t2 - t1);
        System.out.println("timespan: " + c.get(Calendar.MINUTE) + "minutes " + c.get(Calendar.SECOND) + "seconds " + c.get(Calendar.MILLISECOND) + " millis");
    }
}
