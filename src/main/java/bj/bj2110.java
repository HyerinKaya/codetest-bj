package bj;

import java.util.*;
import java.io.*;

public class bj2110 {

    static int N, C, arr[];

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //num of house
        int C = Integer.parseInt(st.nextToken()); //num of wifi

        arr = new int[N];
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int hi = arr[N-1]-arr[0];
        int lo = 1;
        int mid = (hi+lo)/2;
        int len = 0;

        while(lo<=hi) {
            mid = (lo+hi)/2;
            int count = 1;
            int start = arr[0];
            for(int i=1;i<N;i++) {
                len=arr[i]-start;
                if(len>=mid) {
                    count+=1;
                    start = arr[i];
                    len=0;
                }
            }
            if(count>=C) { //too short
                lo = mid+1;
            }else { //too long
                hi = mid-1;
            }
        }

        mid=(hi+lo)/2;
        //System.out.println("mid:"+mid+",hi:"+hi+",lo:"+lo);

        System.out.println(mid);

    }

}
