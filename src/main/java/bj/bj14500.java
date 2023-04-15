package bj;

import java.util.*;
import java.io.*;

public class bj14500 {

    static int N, M, max,map[][];
    static boolean visit[][];
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        max = 0;
        map = new int[N][M];
        visit = new boolean[N][M];
        int answer = 0;

        for(int i=0;i<N;i++) {
            StringTokenizer str = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j]= Integer.parseInt(str.nextToken());
            }
        }


        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                dfs(i, j, 0, 0);
            }
        }

        answer = max;
        System.out.println(answer);

    }

    static void dfs(int r, int c, int count, int num) {
        if(count>=4) {
            max = Math.max(max, num);
            return;
        }
        for(int i=0;i<4;i++) {
            int rr = r+dr[i];
            int cc = c+dc[i];
            if(inRange(rr, cc)&&visit[rr][cc]==false) {
                visit[rr][cc]=true;
                dfs(rr, cc, count+1, num+map[rr][cc]);
                if(inRange(r-dr[i], c-dc[i])) {
                    if(count==2&&visit[r-dr[i]][c-dc[i]]==true){
                        if(i<2) {
                            if(inRange(r+dr[2],c+dc[2])&&visit[r+dr[2]][c+dc[2]]==false) {
                                dfs(r,c, count+2, num+map[rr][cc]+map[r+dr[2]][c+dc[2]]);
                            }
                            if(inRange(r+dr[3],c+dc[3])&&visit[r+dr[3]][c+dc[3]]==false) {
                                dfs(r,c, count+2, num+map[rr][cc]+map[r+dr[3]][c+dc[3]]);
                            }
                        }else {
                            if(inRange(r+dr[0],c+dc[0])&&visit[r+dr[0]][c+dc[0]]==false) {
                                dfs(r,c, count+2, num+map[rr][cc]+map[r+dr[0]][c+dc[0]]);
                            }
                            if(inRange(r+dr[1],c+dc[1])&&visit[r+dr[1]][c+dc[1]]==false) {
                                dfs(r,c, count+2, num+map[rr][cc]+map[r+dr[1]][c+dc[1]]);
                            }
                        }
                    }
                }
                visit[rr][cc]=false;
            }
        }
    }

    static boolean inRange(int r, int c) {
        return(r>=0&&r<N&&c>=0&&c<M);
    }

}
