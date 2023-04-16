package bj;

import java.util.*;
import java.io.*;

public class bj1149 {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][3];
        int[][] dp = new int[N][3];
        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
            map[i][2] = Integer.parseInt(st.nextToken());
        }

        dp[0][0] = map[0][0];
        dp[0][1] = map[0][1];
        dp[0][2] = map[0][2];
        for(int i=1;i<N;i++) {
            dp[i][0] = Math.min(dp[i-1][1]+map[i][0], dp[i-1][2]+map[i][0]);
            dp[i][1] = Math.min(dp[i-1][0]+map[i][1], dp[i-1][2]+map[i][1]);
            dp[i][2] = Math.min(dp[i-1][0]+map[i][2], dp[i-1][1]+map[i][2]);
        }
        int answer = Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2]));
//		for(int i=0;i<N;i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
        System.out.println(answer);
    }
}
