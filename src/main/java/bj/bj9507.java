package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj9507 {
    static int max;
    static long[] koong;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int[] arr = new int[t];
        max = 0;

        for(int i=0; i<t;i++){
            int n = Integer.parseInt(br.readLine());
            arr[i] = n;
            if(n>max) max = n;
        }

        koong = new long[max+1];
        koongpv(arr);
        for(int num : arr){
            System.out.println(koong[num]);
        }
    }

    private static void koongpv(int[] arr){
        koong[0] = 1;
        if(max>0) koong[1] = 1;
        if(max>1) koong[2] = 2;
        if(max>3) koong[3] = 4;
        if(max>4){
            for(int i=4 ; i<=max; i++){
                koong[i] = koong[i-1] + koong[i-2] + koong[i-3] + koong[i-4];
            }
        }
    }
}
