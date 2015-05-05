package com.algochap1;

import java.util.Arrays;

/**
 * TwoSum
 * Created by Nathan on 4/21/2015.
 */
public class TwoSum {

    /**
     * N^2，不去重
     *
     * @param a
     * @return
     */
    public static int countBrute(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                if (a[i] + a[j] == 0) {
                    System.out.println(a[i] + ", " + a[j]);
                    cnt++;
                }
        return cnt;
    }

    /**
     * NlogN，不去重
     *
     * @param a
     * @return
     */
    public static int countFast(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            int index = BinarySearch.rank(-a[i], a);
            if (index > i) {
                System.out.println(a[i] + ", " + a[index]);
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * N，去重
     *
     * @param a
     * @return
     */
    public static int countFaster(int[] a) {
        int N = a.length;
        int cnt = 0;
        int start = 0;
        int end = N - 1;
        while (start < end) {
            int tempsum = a[start] + a[end];
            if (tempsum == 0) {
                System.out.println(a[start] + ", " + a[end]);
                cnt++;
                start++;
                end--;
            }
            else if (tempsum > 0)
                end--;
            else
                start++;
        }

        return cnt;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-12, 23, 68, 23, 18, -9, 12, -5, 23, 5, 33, -6, 77, 11, -1};
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + ": " + arr[i]);
        }
        System.out.println("countBrute: " + countBrute(arr));
        System.out.println("countFast: " + countFast(arr));
        System.out.println("countFaster: " + countFaster(arr));
    }

}
