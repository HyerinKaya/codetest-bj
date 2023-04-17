package bj;

import java.util.*;
import java.io.*;

public class bj2206 {

    static int N, M, map[][], len[][][];
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        len = new int[N][M][2];  //broke wall:1, not break:0

        for(int i=0;i<N;i++) {
            String str = br.readLine();
            String[] strArr = str.split("");
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(strArr[j]);
                len[i][j][0] = N*M+1;
                len[i][j][1] = N*M+1;
            }
        }
        len[0][0][0] = 1;
        len[0][0][1] = 1;

        bfs();

//		for(int i=0;i<N;i++) {
//			for(int j=0;j<M;j++) {
//				System.out.print(len[i][j][0]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//
//		for(int i=0;i<N;i++) {
//			for(int j=0;j<M;j++) {
//				System.out.print(len[i][j][1]+" ");
//			}
//			System.out.println();
//		}
//


        int answer = Math.min(len[N-1][M-1][0], len[N-1][M-1][1]);
        if(answer>=N*M+1) {
            answer = -1;
        }

        System.out.println(answer);

    }

    static void bfs() {
        Queue<Spot> q = new LinkedList<>();
        Spot start = new Spot(0,0,0);
        q.add(start);

        while(!q.isEmpty()) {
            Spot s = q.remove();
            for(int i=0;i<4;i++) {
                Spot mv = new Spot(s.r+dr[i], s.c+dc[i], s.wallCnt);


                if(inRange(mv)&&map[mv.r][mv.c]==0) {
                    if(len[mv.r][mv.c][mv.wallCnt]<N*M+1) continue;

                    if(map[s.r][s.c]==1) { //before was wall
                        len[mv.r][mv.c][mv.wallCnt] = Math.abs(len[s.r][s.c][s.wallCnt])+1;
                    }else {  //before was 0
                        len[mv.r][mv.c][mv.wallCnt] = len[s.r][s.c][s.wallCnt]+1;
                    }
                    q.offer(mv);
                }else if(inRange(mv)&&map[mv.r][mv.c]==1) {
                    if(len[mv.r][mv.c][mv.wallCnt]<N*M+1) continue;
                    if(map[s.r][s.c]==1||s.wallCnt>0) { //before already was wall
                        continue;
                    }else { //before was 0 can break a wall
                        mv.wallCnt+=1;
                        len[mv.r][mv.c][mv.wallCnt] = -1*(len[s.r][s.c][s.wallCnt]+1);
                        q.offer(mv);
                    }
                }
            }
        }
    }

    static class Spot{
        int r, c, wallCnt;
        Spot(int r, int c, int wallCnt){
            this.r = r;
            this.c = c;
            this.wallCnt = wallCnt;
        }
    }

    static boolean inRange(Spot s) {
        return(s.r>=0&&s.r<N&&s.c>=0&&s.c<M);
    }

}