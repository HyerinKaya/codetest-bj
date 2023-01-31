import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj13301 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] arr = new long[N+1];
        arr[1] = 1;
        if(N>1){arr[2] = 1;}
        for(int i=3; i<=N; i++){
            arr[i] = arr[i-1]+arr[i-2];
        }

        long length = 0;
        if(N>2) {
            length = 2 * (arr[N] + arr[N - 1]) + 2 * (arr[N - 1] + arr[N - 2]);
        }else if(N==2){
            length = 6;
        }else if(N==1){
            length = 4;
        }
        System.out.println(length);
    }
}
ㅎ