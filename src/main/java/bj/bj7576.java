package bj;

import java.util.*;
import java.io.*;

public class bj7576 {

    static int[][] map;
    static int leftT, N, M;
    static ArrayList<Spot> list;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {-1, 1, 0, 0};

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        list = new ArrayList<>();
        for(int i=0;i<M;i++) {
            StringTokenizer str = new StringTokenizer(br.readLine());
            for(int j= 0;j<N;j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
                if(map[i][j]==1) {
                    Spot s = new Spot(i,j);
                    list.add(s);
                }else if(map[i][j]==0) {
                    leftT+=1;
                }
            }
        }

        bfs();

        int day =0;
        if(leftT>0) {
            System.out.println(-1);
            return;
        }else {
            for(int i=0;i<M;i++) {
                for(int j=0;j<N;j++) {
                    day = Math.max(map[i][j], day);
                }
            }
        }
        System.out.println(day-1);

    }

    static void bfs(){
        Queue<Spot> q = new LinkedList<>();
        list.forEach(s->q.add(s));
        while(!q.isEmpty()) {
            Spot s = q.remove();
            for(int i=0;i<4;i++) {
                int rr = s.r+dr[i];
                int cc = s.c+dc[i];
                if(inRange(rr, cc)&&map[rr][cc]==0) {
                    map[rr][cc]=map[s.r][s.c]+1;
                    leftT-=1;
                    Spot mv = new Spot(rr, cc);
                    q.offer(mv);
//
//					for(int z=0;z<M;z++) {
//						System.out.println(Arrays.toString(map[z]));
//					}
//					System.out.println();

                }
            }
        }

    }

    static class Spot{
        int r, c;
        Spot(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    static boolean inRange(int r, int c) {
        return(r>=0&&r<M&&c>=0&&c<N);
    }

}