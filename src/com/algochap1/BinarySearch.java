package com.algochap1;

import java.util.Arrays;

/**
 * 1.4.10 修改二分查找算法，使之总是返回和被查找的键匹配的索引最小的元素（且仍能够保证对数级别的运行时间）
 */
public class BinarySearch {

    /**
     * This class should not be instantiated.
     */
    private BinarySearch() {
    }

    /**
     * 迭代式二分查找，数组a为升序
     *
     * @param key the search key
     * @param a   the array of integers, must be sorted in ascending order
     * @return index of key in array a[] if present; -1 if not present
     */
    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    // 递归式二分查找，数组a为升序
    public static int rank(int key, int[] a, int lo, int hi) {
        if (hi < lo) return -1;
        int mid = lo + (hi - lo) / 2;
        if (a[mid] == key) return mid;
        else if (a[mid] < key)
            return rank(key, a, mid + 1, hi);
        else
            return rank(key, a, lo, mid - 1);
    }

    // 递归式二分查找，数组a为降序
    public static int rankReverse(int key, int[] a, int lo, int hi) {
        if (hi < lo) return -1;
        int mid = lo + (hi - lo) / 2;
        if (a[mid] == key) return mid;
        else if (a[mid] > key)
            return rankReverse(key, a, mid + 1, hi);
        else
            return rankReverse(key, a, lo, mid - 1);
    }

    public static int rankMinIndexByBinary(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        int pos = a.length;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else {
                if (mid < pos) {
                    pos = mid;
                }
                hi = mid - 1;
                System.out.println("#internal search# lo: " + lo + ", hi: " + hi);
            }
        }
        if (pos == a.length)
            return -1;
        else
            return pos;
    }

    public static int rankMinIndexByLoop(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        int pos = -1;
        int loopcnt = 0;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else {
                pos = mid;
                for (int i = mid - 1; i >= lo; i--) {
                    loopcnt++;
                    if (a[i] != key) {
                        pos = i + 1;
                        break;
                    }
                }
                break;
            }
        }
        System.out.println("loopcnt: " + loopcnt);
        return pos;
    }

    /**
     * Reads in a sequence of integers from the whitelist file, specified as
     * a command-line argument. Reads in integers from standard input and
     * prints to standard output those integers that do *not* appear in the file.
     */
    public static void main(String[] args) {

        int[] whitelist = new int[]{84, 23, 68, 23, 18, 77, 12, 23, 23, 57, 33, 23, 77, 11, 23};

        // sort the array
        Arrays.sort(whitelist);

        for (int i = 0; i < whitelist.length; i++) {
            System.out.println(i + ": " + whitelist[i]);
        }

        System.out.println();
        System.out.println("original found index is: " + rank(23, whitelist));
        System.out.println();
        System.out.println("min index found by binary is: " + rankMinIndexByBinary(23, whitelist));
        System.out.println();
        System.out.println("min index found by loop is: " + rankMinIndexByLoop(23, whitelist));
        System.out.println();
        System.out.println("original found index is: " + rank(85, whitelist, 0, whitelist.length-1));
        System.out.println();

        int N = whitelist.length;
        int[] templist = new int[N];
        for (int i = 0; i < N; i++) {
            templist[i] = whitelist[N - 1 - i];
            System.out.print(templist[i] + "\t");
        }
        System.out.println();
        System.out.println("original found index is: " + rankReverse(18, templist, 0, N - 1));
    }
}
