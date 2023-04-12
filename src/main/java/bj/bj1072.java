package bj;

import java.util.*;
import java.io.*;

public class bj1072 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int Z = (int)((long)Y*100/X);

        int lo = 0;
        int hi = (int) 1e9;
        int mid = (lo+hi)/2;
        int answer = -1;
        int score = 0;


        while(lo<=hi) {
            mid = (lo+hi)/2;
            score = (int)((long)(Y+mid)*100/(X+mid));
            if(score!=Z) {
                //System.out.println(lo+", "+hi+", "+score);
                answer = mid;
                hi = mid-1;
            }else {
                //System.out.println(lo+", "+hi+", "+score);
                lo = mid+1;
            }
        }

        System.out.println(answer);

    }
}

