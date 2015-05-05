package com.algochap1;

import com.stdlib.StdRandom;

import java.util.Arrays;

public class Chap1Sect4 {


    // 给定一个含有N个double值的数组a[]，在其中找到一堆最接近的值：两者之差的绝对值最小的两个数
    // 最坏情况下运行时间为线性对数级别
    public static double[] ClosestPair(double[] a) throws Exception {
        double[] result = new double[2];
        int N = a.length;
        Arrays.sort(a);
        for (int tempi = 0; tempi < N; tempi++) {
            System.out.println(a[tempi]);
        }
        double minDiff = Double.POSITIVE_INFINITY;
        int i = 0, j = i + 1;
        if (N < 2)
            throw new Exception("length is less than 2!");
        while (j < N) {
            double tempDiff = Math.abs(a[i] - a[j]);
            if (tempDiff < minDiff) {
                minDiff = tempDiff;
                result[0] = a[i];
                result[1] = a[j];
            }
            i++;
            j++;
        }
        return result;
    }

    // 给定一个含有N个double值的数组a[]，在其中找到一堆最遥远的值：两者之差的绝对值最大的两个数
    // 最坏情况下运行时间为线性级别
    public static double[] farthestPair(double[] a) {
        double[] result = new double[2];
        int N = a.length;
        for (double anA : a) {
            System.out.println(anA);
        }
        result[1] = Double.NEGATIVE_INFINITY;// tempMax
        result[0] = Double.POSITIVE_INFINITY;// tempMin
        for (double anA : a) {
            if (anA > result[1])
                result[1] = anA;
            if (anA < result[0])
                result[0] = anA;
        }
        return result;
    }

    public static void pairTest() throws Exception {
        StdRandom.setSeed(12345);
        System.out.println();
        double[] arr = new double[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = StdRandom.uniform(-1000.0, 1000.0);
        }
        double[] result = ClosestPair(arr);
        System.out.println("closestPair: " + result[0] + ", " + result[1]);

        result = farthestPair(arr);
        System.out.println("farthestPair: " + result[0] + ", " + result[1]);
    }

    // 1.4.18, P133

    // 给定一个含有N个不同整数的数组，找到一个局部最小元素，满足a[i]<a[i-1]，且a[i]<a[i+1]的索引i
    // 最坏情况下所需的比较次数为~2lgN
    // 书上的提示：检查数组的中间值a[N/2]以及跟它相邻的元素a[N/2-1]和a[N/2+1]，如果a[N/2]是一个局部最小值则算法终止，否则则在较小的相邻元素的半边中继续寻找
    // 这个实现有问题。。。迭代式实现有难度。。。
    public static int localMin(int[] a) {
        int lo = 1;
        int hi = a.length - 2;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] < a[mid - 1] && a[mid] < a[mid + 1]) {
                return mid;
            }
            else {
                if (a[mid - 1] < a[mid + 1])
                    hi = mid - 1;
                else
                    lo = mid + 1;
            }
        }
        return -1;
    }

    public static void localMinTest() {
        StdRandom.setSeed(1100);
        System.out.println();
        int[] testArr = new int[20];
        for (int i = 0; i < testArr.length; i++) {
            testArr[i] = StdRandom.uniform(-20, 20);
            System.out.print(testArr[i] + " ");
        }
        System.out.println();
        int index = localMin(testArr);
        System.out.print("localMin: " + "index=" + index);
        if (index != -1)
            System.out.println(" value=" + testArr[index]);
    }

    // 上面的规则貌似不能保证使得在数组中一定能够找到局部最小值，比如单调递增或者单调递减的情况，还有可能在错误的半边寻找导致没有找到局部最小值
    // 重新定义局部最小值如下：a[i]<=a[i-1] && a[i]<=a[i+1]（如果是头或者尾，则只看一边)，则a[i]叫做一个局部最小值。
    public static int localMinNew(int[] a, int lo, int hi) {
        int length = hi - lo + 1;
        System.out.println(lo + ", " + hi);
        if (length == 1)
            return lo;
        else if (length == 2)
            return a[lo] <= a[hi] ? lo : hi;
        int mid = lo + (hi - lo) / 2;
        if (a[mid] > a[mid - 1])
            return localMinNew(a, lo, mid - 1);
        if (a[mid] > a[mid + 1])
            return localMinNew(a, mid + 1, hi);
        return mid;
    }

    public static void localMinNewTest() {
        StdRandom.setSeed(4800);
        System.out.println();
        int[] testArr = new int[10];
        for (int i = 0; i < testArr.length; i++) {
            testArr[i] = StdRandom.uniform(-100, 100);
            System.out.print(testArr[i] + " ");
        }
        System.out.println();
        int index = localMinNew(testArr, 0, testArr.length - 1);
        System.out.print("localMin: " + "index=" + index);
        System.out.println(" value=" + testArr[index]);
    }

    // 1.4.19, P133

    // 给定一个含有N*N个不同整数的N*N数组a[]，设计一个运行时间跟N成正比的算法来找出一个局部最小值
    // 满足a[i][j]<[i+1][j],a[i][j]<a[i][j+1],a[i][j]<a[i-1][j],a[i][j]<a[i][j-1]的索引i和j
    // 运行时间最坏情况下应和N成正比
    // 下面的递归实现方式感觉跟直接两层循环判断的复杂度差不多。。。无法满足要求
    // TODO: improve
    public static int[] matrixLocalMin(int[][] a, int[][] aux, int i, int j, int rows, int cols) {
        int[] result = new int[2];
        int val = a[i][j];
        int left, right, up, down;
        if ((i + 1) <= (rows - 1)) {
            down = a[i + 1][j];
            if (val > down && aux[i + 1][j] == 0) {
                aux[i][j] = -1;
                return matrixLocalMin(a, aux, i + 1, j, rows, cols);
            }
        }
        if ((i - 1) >= 0) {
            up = a[i - 1][j];
            if (val > up && aux[i - 1][j] == 0) {
                aux[i][j] = -1;
                return matrixLocalMin(a, aux, i - 1, j, rows, cols);
            }
        }
        if ((j + 1) <= (cols - 1)) {
            right = a[i][j + 1];
            if (val > right && aux[i][j + 1] == 0) {
                aux[i][j] = -1;
                return matrixLocalMin(a, aux, i, j + 1, rows, cols);
            }
        }
        if ((j - 1) >= 0) {
            left = a[i][j - 1];
            if (val > left && aux[i][j - 1] == 0) {
                aux[i][j] = -1;
                return matrixLocalMin(a, aux, i, j - 1, rows, cols);
            }
        }

        result[0] = i;
        result[1] = j;
        return result;
    }

    public static void matrixLocalMinTest() {
        System.out.println();
        int N = 10;
        int[][] a = new int[N][N];
        int[][] aux = new int[N][N];
        StdRandom.setSeed(233333);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = StdRandom.uniform(-80, 80);
                System.out.print(a[i][j] + "\t\t");
            }
            System.out.println();
        }
        int[] result = matrixLocalMin(a, aux, 0, 0, N, N);
        System.out.println("row: " + (result[0] + 1) + " col: " + (result[1] + 1));
    }

    // 1.4.20, P133

    // 双调查找：数组中所有元素先递增后递减，则称这个数组为双调的
    // 给定一个含有N个不同整数的双调数组，判断它是否有给定的整数
    // 最坏情况下所需的比较次数为~3lgN
    // TODO: summary
    public static int bitonicSearch(int[] a, int key, int lo, int hi) {
        if (hi < lo) return -1;
        int mid = lo + (hi - lo) / 2;
        if (a[mid] == key) return mid;
        if (a[mid] < a[mid + 1]) {// mid处在升序的一遍
            int temp = BinarySearch.rank(key, a, lo, mid - 1);
            if (temp == -1)
                return bitonicSearch(a, key, mid + 1, hi);
            else return temp;
        }
        else {// mid处在降序的一遍
            int temp = BinarySearch.rankReverse(key, a, mid + 1, hi);
            if (temp == -1)
                return bitonicSearch(a, key, lo, mid - 1);
            else return temp;
        }
    }

    public static void bitonicSearchTest() {
        StdRandom.setSeed(23300);
        int len1 = StdRandom.uniform(3, 12);
        int len2 = StdRandom.uniform(3, 12);
        System.out.println("len1: " + len1 + ", len2: " + len2);
        int[] arr1 = new int[len1];
        int[] arr2 = new int[len2];
        int[] arr = new int[len1 + len2];
        for (int i = 0; i < len1; i++)
            arr1[i] = StdRandom.uniform(1, 30);
        for (int i = 0; i < len2; i++)
            arr2[i] = StdRandom.uniform(1, 30);
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        for (int i = 0; i < len1; i++)
            arr[i] = arr1[i];
        for (int i = 1; i <= len2; i++)
            arr[len1 + len2 - i] = arr2[i - 1];
        for (int i = 0; i < len1 + len2; i++)
            System.out.print(arr[i] + "\t");
        System.out.println();
        int selectedIndex = StdRandom.uniform(0, len1 + len2);
        int index = bitonicSearch(arr, 9, 0, arr.length - 1);//arr[selectedIndex]
        System.out.print("bitonicSearch: " + "selectedIndex=" + selectedIndex + " foundIndex=" + index);
        if (index != -1)
            System.out.println(" value=" + arr[index]);
    }

    public static void main(String[] args) throws Exception {
        //pairTest();
        //localMinTest();
        //localMinNewTest();
        //matrixLocalMinTest();
        bitonicSearchTest();
    }
}
