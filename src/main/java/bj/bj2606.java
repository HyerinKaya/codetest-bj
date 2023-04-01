package bj;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj2606 {

    private static int result;
    private static boolean[] visited;
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        StringTokenizer stnext = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(stnext.nextToken());

        result = 0;

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] fromTo = br.readLine().split(" ");
            int from = Integer.parseInt(fromTo[0]);
            int to = Integer.parseInt(fromTo[1]);

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        visited = new boolean[n + 1];
        getHacked(1);

        System.out.println(result);

    }


    private static void getHacked(int x) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(x);
        visited[x] = true;

        while(!queue.isEmpty()) {
            int front = queue.remove();
            for(int value : graph.get(front)) {
                if(!visited[value]) {
                    visited[value] = true;
                    queue.add(value);
                    result++;
                }
            }
        }
    }
}
