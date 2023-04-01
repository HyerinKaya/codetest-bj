package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj14501 {
    static int N;
    static int[][] arr;
    static int[] visit;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        answer=0;
        visit = new int[N];
        dfs(0);
        System.out.println(answer);
    }

    static void dfs(int idx){ // 상담 경우의 수
        if(idx==N){
            income();
            return;
        }

        for(int i=idx;i<N;i++) {
            int days = arr[i][0];
            if(days<=(N-i)&&visit[i]<1){
                int[] copy = new int[N];
                copy = visit.clone();
                visit[i] = arr[i][1];
                for(int j=1;j<days;j++){
                    visit[i+j] = -1;
                }
                dfs(i+days);
                visit = copy.clone();
            }else if(days>(N-idx)){
                dfs(i+1);
            }
        }
    }

    static void income(){ // 이익 계산
        int income = 0;
        for(int i:visit){
            if(i!=0&&i!=-1){
                income+=i;
            }
        }
        answer = Math.max(income, answer);
    }
}
