package com.algochap1;

import com.stdlib.StdRandom;

/**
 * Practice 1.3.37
 * Created by Nathan on 5/5/2015.
 */
public class Josephus {
    // N个身陷绝境的人一致同意通过以下方式减少生存人数。他们围成一圈（位置记为0到N-1）并从第一个人开始报数，报到M的人会被杀死，直到最后一个人留下来

    /**
     * 使用队列，将要杀死的人的前面的人逐个退出并重新加入到队列中
     *
     * @param M
     * @param N
     */
    public static void plot(int M, int N) {
        LinkedQueue<Integer> q = new LinkedQueue<>();
        for (int i = 0; i < N; i++) {
            q.enqueue(i);
        }
        while (!q.isEmpty()) {
            for (int i = 0; i < M - 1; i++) {
                q.enqueue(q.dequeue());
            }
            System.out.print(q.dequeue() + "\t");
        }
        System.out.println();
    }

    /**
     * 使用数组方式实现，比较直观，时间复杂度较上面那种方式高
     *
     * @param M
     * @param N
     */
    public static void plotArray(int M, int N) {
        int[] arr = new int[N];
        int counter = 0, index = 0;
        int runCnt = 0;// the number of people killed
        while (runCnt < (N - 1)) {// run N-1 times
            if (arr[index] == 0) {
                counter++;
                if (counter == M) {
                    arr[index] = -1;
                    System.out.print(index + "\t");
                    runCnt++;
                    counter = 0;
                }
            }
            index++;
            if (index >= N)// wrap around
                index = 0;
        }
        for (int i = 0; i < N; i++) {// find the survivor
            if (arr[i] == 0) {//
                System.out.print(i + "\t");
                break;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //for (int i = 0; i < 10; i++) {
        //    int M = StdRandom.uniform(30);
        //    int N = StdRandom.uniform(30);
        //    System.out.println("M: " + M + ", N: " + N);
        //    plot(M, N);
        //}
        plot(4, 9);
        plotArray(4, 9);
    }

}
