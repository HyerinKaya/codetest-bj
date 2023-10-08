package codetest;

import java.util.*;

public class pg_nextbignum {

    public static int solution(int n) {
        int answer = 0;
        int cntBinary = 0;
        int cntBignum = 0;

        String binary = Integer.toBinaryString(n);
        for(int i=0;i<binary.length();i++){
            if(binary.charAt(i)=='1') cntBinary+=1;
        }
        //System.out.println(binary+" "+cntBinary);

        for(int i=n+1;i<1000000;i++){
            cntBignum = 0;
            String bigNum = Integer.toBinaryString(i);
            for(int j=0;j<bigNum.length();j++){
                if(bigNum.charAt(j)=='1') cntBignum+=1;
            }
            if(cntBignum==cntBinary){
                answer = i;
                break;
            }

        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 78;
        System.out.println(pg_nextbignum.solution(n));
    }
}
