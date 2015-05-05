package com.algochap1;

import java.util.Arrays;

/**
 * StaticSETofInts
 * Created by Nathan on 4/21/2015.
 */
public class StaticSETofInts {
    private int[] a;
    private int templo, temphi;

    public StaticSETofInts(int[] keys) {
        a = new int[keys.length];
        for (int i = 0; i < keys.length; i++) {
            a[i] = keys[i];
        }
        Arrays.sort(a);
    }

    public boolean contains(int key) {
        return rank(key) != -1;
    }

    private int rank(int key) {
        int lo = 0;
        int hi = a.length - 1;
        int loopcnt = 0;
        while (lo <= hi) {
            loopcnt++;
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else {
                templo = lo;
                temphi = hi;
                System.out.println("rank loopcnt: " + loopcnt);
                return mid;
            }
        }
        System.out.println("rank loopcnt: " + loopcnt);
        return -1;
    }

    private int rankMin(int key, int lo, int hi) {
        int pos = a.length;
        int loopcnt = 0;
        while (lo <= hi) {
            loopcnt++;
            System.out.println("#rankMin internal search# lo: " + lo + ", hi: " + hi);
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else {
                if (mid < pos) {
                    pos = mid;
                }
                hi = mid - 1;
            }
        }
        System.out.println("rankMin loopcnt: " + loopcnt);
        return pos == a.length ? -1 : pos;
    }

    private int rankMax(int key, int lo, int hi) {
        int pos = -1;
        int loopcnt = 0;
        while (lo <= hi) {
            loopcnt++;
            System.out.println("#randMax internal search# lo: " + lo + ", hi: " + hi);
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else {
                if (mid > pos)
                    pos = mid;
                lo = mid + 1;
            }
        }
        System.out.println("rankMax loopcnt: " + loopcnt);
        return pos;
    }

    /**
     * 找出给定键的出现次数，且在最坏情况下所需的运行时间和logN成正比
     *
     * @param key
     * @return
     */
    public int howMany(int key) {
        int index = rank(key);
        if (index == -1) return 0;
        int minIndex = rankMin(key, templo, index);
        int maxIndex = rankMax(key, index, temphi);
        System.out.println("minIndex: " + minIndex + ", maxIndex: " + maxIndex);
        return maxIndex - minIndex + 1;
    }

    public static void main(String[] args) {
        int[] whitelist = new int[]{84, 23, 68, 23, 18, 77, 12, 23, 23, 57, 33, 23, 77, 11, 23};
        // sort the array
        Arrays.sort(whitelist);
        StaticSETofInts set = new StaticSETofInts(whitelist);

        for (int i = 0; i < whitelist.length; i++) {
            System.out.println(i + ": " + whitelist[i]);
        }
        System.out.println();

        System.out.println("cnt of 23: " + set.howMany(23));
    }
}
