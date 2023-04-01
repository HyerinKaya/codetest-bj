package bj;

import java.io.*;
import java.util.StringTokenizer;

public class bj17204 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        boolean[] visited = new boolean[N];
        int next = 0;
        int count = 0;
        int answer = -1;


        for (int i = 0; i < N; i++) {

            StringTokenizer str = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(str.nextToken());
        }


        while(true){
            if(next==M){
                answer = count;
                System.out.println(answer);
                break;
            }
            if(visited[next]==true){
                System.out.println(answer);
                break;
            }
            visited[next] = true;
            next = arr[next];
            count++;
        }

    }
}
