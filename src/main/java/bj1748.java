import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj1748 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long answer = 0;


        int count =1;
        int num =10;

        for(int i=1;i<=N;i++){
            if(i%num==0&&i/num==1){
                count++;
                num*=10;
            }
            answer+=count;
        }


        System.out.println(answer);
    }
}
