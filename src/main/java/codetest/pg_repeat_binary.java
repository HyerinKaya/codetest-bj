package codetest;
import java.util.*;
public class pg_repeat_binary {
    //이진변환 반복하기

    static int zero;
    static int one;
    static int totalZero;
    static int count;
    public static int[] solution(String s){
        totalZero = 0;
        count = 0;
        int[] answer = new int[2];
        while(!s.equals("1")){
            zero = 0;
            one = 0;

            for(int i=0;i<s.length();i++){
                if(s.charAt(i)=='0'){
                    zero+=1;
                }else{
                    one+=1;
                }
            }
            totalZero+=zero;
            StringBuilder sb = new StringBuilder();
            while(one>0){
                sb.append(String.valueOf(one%2));
                one = one/2;
            }
            s = sb.toString();
            count+=1;
        }

        answer[0] = count;
        answer[1] = totalZero;
        return answer;
    }

    public static void main(String[] args) {
        String s= "1111111";
        int[] answer = pg_repeat_binary.solution(s);
        System.out.println(answer[0]+" "+answer[1]);
    }

}
