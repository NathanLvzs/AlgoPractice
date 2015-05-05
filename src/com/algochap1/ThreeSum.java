package com.algochap1;

import java.util.Arrays;

public class ThreeSum {

    /**
     * N^3，不去重
     *
     * @param a
     * @return
     */
    public static int countBrute(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                for (int k = j + 1; k < N; k++)
                    if (a[i] + a[j] + a[k] == 0) {
                        System.out.println(a[i] + ", " + a[j] + ", " + a[k]);
                        cnt++;
                    }
        return cnt;
    }

    /**
     * N^2logN，不去重
     *
     * @param a
     * @return
     */
    public static int countFast(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++) {
                int index = BinarySearch.rank(-a[i] - a[j], a);
                if (index > j) {
                    System.out.println(a[i] + ", " + a[j] + ", " + a[index]);
                    cnt++;
                }
            }
        return cnt;
    }

    /**
     * 这是其中一种方法，相当于利用TwoSum，时间复杂度为O(N^2)。去重
     * 另外一种是将数组中的元素插入到一个哈希表中，查找元素的时间复杂度为O(1)，两层循环，这样的时间复杂度为O(N^2)。
     *
     * @param a
     * @return
     */
    public static int countFaster(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N - 2; i++) {
            int cur = a[i];
            int start = i + 1;
            int end = N - 1;
            while (start < end) {
                int b = a[start];
                int c = a[end];
                if (cur + b + c == 0) {
                    System.out.println(cur + ", " + b + ", " + c);
                    cnt++;
                    start++;
                    end--;
                }
                else if (cur + b + c > 0) {
                    end--;
                }
                else {
                    start++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-12, 23, -6, 23, 18, -9, 12, -5, 23, 57, 33, -6, 77, 11, -1};
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + ": " + arr[i]);
        }
        System.out.println("countBrute: " + countBrute(arr));
        System.out.println("countFast: " + countFast(arr));
        System.out.println("countFaster: " + countFaster(arr));
    }
}
