import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj14888 {
    static int N;
    static int[] arr;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N]; //input 숫자 배열
        int[] calArr = new int[5];  //연산자 배열 +1, -2, *3, /4
        int[] formArr = new int[N - 1];  //연산자 배치
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        StringTokenizer str = new StringTokenizer(br.readLine());
        for(int i=1; i<5;i++){
            int calculator = Integer.parseInt(str.nextToken());
            calArr[i] = calculator;
        }

        makeForm(0,calArr, formArr);
        System.out.println(max);
        System.out.println(min);

    }

    //연산자 순서 정하기
    static void makeForm(int idx,  int[] calArr, int[] formArr){
        /*
        System.out.println(Arrays.toString(formArr));
        System.out.println("idx:"+idx);
        System.out.println(Arrays.toString(calArr));
        System.out.println();
         */
        if(idx==N-1) {
        /*
        완성된 식 계산하기
         */
            calculate(formArr);
            return;
        }
        if(calArr[1]>0){
            formArr[idx] = 1;
            calArr[1]-=1;
            makeForm(idx+1,  calArr, formArr);
            calArr[1]+=1;
        }
        if(calArr[2]>0){
            formArr[idx] =2;
            calArr[2]-=1;
            makeForm(idx+1,  calArr, formArr);
            calArr[2]+=1;
        }
        if(calArr[3]>0){
            formArr[idx]=3;
            calArr[3]-=1;
            makeForm(idx+1, calArr, formArr);
            calArr[3]+=1;
        }
        if(calArr[4]>0){
            formArr[idx] =4;
            calArr[4]-=1;
            makeForm(idx+1, calArr, formArr);
            calArr[4]+=1;
        }

    }

    static void calculate(int[] formArr){
        int answer = arr[0];
        for(int i=1;i<N;i++){
            if(formArr[i-1]==1){
                answer += arr[i];
            }else if(formArr[i-1]==2){
                answer -= arr[i];
            }else if(formArr[i-1]==3){
                answer *= arr[i];
            }else if(formArr[i-1]==4){
                answer /= arr[i];
            }
        }
        if(max<answer){
            //System.out.println("max:"+answer);
            max = answer;
        }
        if(min>answer){
            //System.out.println("min:"+answer);
            min = answer;
        }
    }
}
