package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj14889 {
    static int N;
    static int[][] map;
    static long answer;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        map = new int[N][N];
        for(int i=0; i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        answer = Integer.MAX_VALUE;
        makeTeam(0,0);
        System.out.println(answer);

    }

    static void makeTeam(int idx, int count){
        if(count==(N/2)){
            //두 팀의 능력치 차이 구하기
            subTeamScore();
            return;
        }
        for(int i=idx;i<N;i++){
            visited[i] = true;
            makeTeam(i+1, count+1);
            visited[i] = false;
        }
    }

    static void subTeamScore(){
        int startScore = 0;
        int linkScore = 0;
        for(int i=0;i<N-1;i++){
            for(int j=i+1;j<N;j++){
                if(visited[i]==true&&visited[j]==true){
                    startScore+=map[i][j]+map[j][i];
                }else if(visited[i]==false&&visited[j]==false){
                    linkScore+=map[i][j]+map[j][i];
                }
            }
        }
        answer = Math.min(answer, Math.abs(startScore - linkScore));
        if (answer==0){
            System.out.println(answer);
            System.exit(0);
        }
    }
}
