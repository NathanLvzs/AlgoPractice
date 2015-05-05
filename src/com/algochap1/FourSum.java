package com.algochap1;

import java.util.Arrays;

public class FourSum {

    /**
     * N^4，不去重
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
                    for (int m = k + 1; m < N; m++) {
                        if (a[i] + a[j] + a[k] + a[m] == 0) {
                            System.out.println(a[i] + ", " + a[j] + ", " + a[k] + ", " + a[m]);
                            cnt++;
                        }
                    }
        return cnt;
    }

    /**
     * N^3，去重
     *
     * @param a
     * @return
     */
    public static int countFast(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N - 3; i++) {
            int key = a[i];
            for (int j = i + 1; j < N - 2; j++) {
                int cur = a[j];
                int start = j + 1;
                int end = N - 1;
                while (start < end) {
                    int b = a[start];
                    int c = a[end];
                    if (cur + b + c + key == 0) {
                        System.out.println(key + ", " + cur + ", " + b + ", " + c);
                        cnt++;
                        start++;
                        end--;
                    }
                    else if (cur + b + c + key > 0) {
                        end--;
                    }
                    else {
                        start++;
                    }
                }
            }
        }
        return cnt;
    }

    /**
     * 用二分法的思路，如果把所有的两两pair都求出来，然后再进行一次Two Sum的匹配，我们知道Two Sum是一个排序加上一个线性的操作，并且把所有pair的数量是O((n-1)+(n-2)+...+1)=O(n(n-1)/2)=O(n^2)。所以对O(n^2)的排序如果不用特殊线性排序算法是O(n^2*log(n^2))=O(n^2*2logn)=O(n^2*logn)，算法复杂度比上一个方法的O(n^3)是有提高的。
     * 思路虽然明确，不过细节上会多很多情况要处理。首先，我们要对每一个pair建一个数据结构来存储元素的值和对应的index，这样做是为了后面当找到合适的两对pair相加能得到target值时看看他们是否有重叠的index，如果有说明它们不是合法的一个结果，因为不是四个不同的元素。接下来我们还得对这些pair进行排序，所以要给pair定义comparable的函数。最后，当进行Two Sum的匹配时因为pair不再是一个值，所以不能像Two Sum中那样直接跳过相同的，每一组都得进行查看，这样就会出现重复的情况，所以我们还得给每一个四个元素组成的tuple定义hashcode和相等函数，以便可以把当前求得的结果放在一个HashSet里面，这样得到新结果如果是重复的就不加入结果集了。
     *
     * @param a
     * @return
     */
    public static int countFaster(int[] a) {
        int N = a.length;
        int cnt = 0;


        return cnt;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-12, 23, 68, 23, 18, -9, 12, -5, 23, 57, 33, -6, 77, 11, -1};
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + ": " + arr[i]);
        }
        System.out.println("countBrute: " + countBrute(arr));
        System.out.println("countFast: " + countFast(arr));
        //System.out.println("countFaster: "+countFaster(arr));
    }

}
