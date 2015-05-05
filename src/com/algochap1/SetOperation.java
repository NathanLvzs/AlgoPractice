package com.algochap1;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Set operation algorithm practice
 * Created by Nathan on 4/21/2015.
 */
public class SetOperation {

    // 有序打印给定的两个有序数组（长度为N）中的所有公共元素，最坏情况下所需运行时间应该和N成正比
    public static int intersect(int[] a, int[] b) throws Exception {
        HashSet set = new HashSet();
        int N = a.length;
        if (b.length != N)
            throw new Exception("the lengths of the two arrays do not match!");
        int i = 0, j = 0;
        while (i < N && j < N) {
            if (a[i] == b[j]) {
                System.out.print(a[i] + " ");
                if (!set.contains(a[i]))
                    set.add(a[i]);
                i++;
                j++;
            }
            else if (a[i] > b[j]) {
                j++;
            }
            else {
                i++;
            }
        }
        return set.size();
    }

    public static void main(String[] args) throws Exception {
        int[] arr1 = new int[]{-12, 23, 68, 23, 18, -9, 12, -5, 23, 57, 33, -6, 77, 11, -1};
        Arrays.sort(arr1);
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();
        int[] arr2 = new int[]{-11, 23, 6, 27, 18, 9, 92, -5, 32, 57, 33, -43, 34, 3, 1};
        Arrays.sort(arr2);
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();
        System.out.println("intersect: " + intersect(arr1, arr2));
    }
}
