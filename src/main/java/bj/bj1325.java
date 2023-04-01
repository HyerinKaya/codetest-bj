package bj;

import java.io.*;
import java.util.*;

/*
효율적인 해킹
https://www.acmicpc.net/problem/1325
*/

public class bj1325 {
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static boolean[] hacked;
    private static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nm = br.readLine().split(" ");

        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] fromTo = br.readLine().split(" ");
            int from = Integer.parseInt(fromTo[0]);
            int to = Integer.parseInt(fromTo[1]);

            graph.get(from).add(to);
        }
        result = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            hacked = new boolean[n + 1];
            countHack(i);
        }

        int max = 0;

        for (int i = 1; i <= n; i++) {
            max = Math.max(max, result[i]);
        }

        for (int i = 1; i <= n; i++) {
            if(result[i] == max) {
                bw.write(i + " ");
            }
        }

        bw.flush();
        bw.close();
    }

    private static void countHack(int x) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(x);
        hacked[x] = true;

        while(!queue.isEmpty()) {
            int front = queue.remove();
            for(int value : graph.get(front)) {
                if(!hacked[value]) {
                    hacked[value] = true;
                    queue.add(value);
                    result[value]++;
                }
            }
        }
    }
}


