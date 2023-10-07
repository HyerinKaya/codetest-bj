package codetest;

import java.util.Arrays;
import java.util.Comparator;

public class pg_makeminimum{

    static int[] A = new int[]{1, 4, 2};
    static int[] B = new int[]{5, 4, 4};
    public static int solution(int[] A, int[] B){
        int answer = 0;

        Integer[] arrA = new Integer[A.length];
        Integer[] arrB = new Integer[B.length];
        for(int i=0;i<A.length;i++){
            arrA[i] = (Integer)A[i];
            arrB[i] = (Integer)B[i];
        }

        Arrays.sort(arrA);
        Arrays.sort(arrB, Comparator.reverseOrder());

        for(int i=0; i<arrA.length;i++){
            answer+= arrA[i]*arrB[i];
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(A, B));
    }


}
