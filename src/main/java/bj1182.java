import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1182 {
    static int N, S, answer=0;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        int sum = 0;

        StringTokenizer str = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(str.nextToken());
        }

        dfs(0, sum);
        if(S==0) answer--;

        System.out.println(answer);
    }

    static void dfs(int V, int sum){
        if(V==N&&sum==S){
            answer++;
            return;
        }else if(V==N&&sum!=S){
            return;
        }else{
            dfs(V+1, sum+arr[V]);
            dfs(V+1, sum);
        }
    }
}
