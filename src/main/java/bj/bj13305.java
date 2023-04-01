package bj;

import java.io.*;
import java.util.StringTokenizer;

public class bj13305 {
    static int[] road;
    static int[] oil;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        road = new int[N-1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N-1; i++) {
            road[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        oil = new int[N];
        for (int i=0; i < N; i++){
            oil[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(oilSum(N, road, oil));
    }

    public static long oilSum(int N, int[] road, int[] oil){
        long totalPrice = 0;
        long minPrice = oil[0];
        int visit = 0;
        int count = 0;
        for(int i=1; i<N; i++) {
            if(oil[i]<=minPrice || i==(N-1)){
                count++;
                while(count>0){
                    totalPrice+=(road[visit]*minPrice);
                    visit++;
                    count--;
                }
                minPrice = oil[i];
            }else{
                count++;
            }
        }
        return totalPrice;
    }
}
