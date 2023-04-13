package codetest;

import java.util.*;

public class pg_immigration {
    public long solution(int n, int[] times) {
        long answer = 0;
        long lo = 0;
        long hi = (long)n*times[times.length-1];
        long mid = (lo+hi)/2;

        while(lo<=hi){
            long sum = 0;
            mid = (lo+hi)/2;
            for(int i=0;i<times.length;i++){
                sum+=mid/times[i];
            }
            if(sum>=n){ //n보다 많이 심사함
                hi = mid-1;
                answer = mid;
            }else if(sum<n){ //n보다 적게 심사함
                lo = mid+1;
            }
        }

        return answer;
    }
}
