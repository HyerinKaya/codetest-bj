package bj;

import java.util.*;
import java.io.*;

public class bj10844 {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long answer = 0;

        long[][] dp = new long[N+1][10];
        for(int i=1;i<10;i++) {
            dp[1][i] = 1;
        }

        for(int i=2;i<N+1;i++) {
            for(int j=0;j<10;j++) {
                long up = 0;
                long down = 0;
                if(j>0) down = dp[i-1][j-1]; //if 0 only up
                if(j<9) up = dp[i-1][j+1]; //if 9 only down
                dp[i][j] = (up+down)%1000000000;
            }
        }


        for(int i=0;i<10;i++) {
            answer +=dp[N][i];
        }
//		for(int i=0;i<N+1;i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}

        System.out.println(answer%1000000000);
    }

}