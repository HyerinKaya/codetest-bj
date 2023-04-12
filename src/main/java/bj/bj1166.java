package bj;

import java.util.*;
import java.io.*;

public class bj1166 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        double lo = 0;
        double hi = Math.min(L, Math.min(W, H));
        double mid = (lo+hi)/2;

        while(lo<hi) {
            mid = (lo+hi)/2;
            if((long)(L/mid)*(long)(W/mid)*(long)(H/mid)>=N) { //larger box is possible
                //System.out.println("right:"+lo+", "+hi);
                if(lo==mid) break;
                lo = mid;
            }else {
                if(hi==mid) break;
                //System.out.println("left:"+lo+", "+hi);
                hi = mid;

            }
        }

        System.out.println(mid);

    }
}
