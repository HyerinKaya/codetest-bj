package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj1500 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long answer = 1;

        int[] arr = new int[K];
        int base = S/K;
        int remain = S%K;

        for (int i = 0; i < K; i++) {
            arr[i] = base;
            if(remain>0) {
                arr[i]+=1;
                remain-=1;
            }
        }

        for(int i=0;i<K;i++){
            answer *= arr[i];
        }
        System.out.println(answer);

    }
}