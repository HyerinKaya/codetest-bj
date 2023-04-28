package bj;

import java.util.*;
import java.io.*;

public class bj16234 {

    static int N, L, R, map[][];
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static boolean visit[][], isMove;


    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visit = new boolean[N][N];
        isMove = true;
        int answer =0;

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(isMove==true) {
            isMove = false;
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    if(visit[i][j]==false) {
                        bfs(i, j);
                    }
                }
            }
            visit = new boolean[N][N];
//			System.out.println(isMove);
//			for(int i=0;i<N;i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}

            if(isMove==true) {
                answer+=1;
            }
        }


        System.out.println(answer);

    }

    static void bfs(int r, int c) { //find neighbor country to population move
        Queue<Spot> q = new LinkedList<>();
        Spot start = new Spot(r, c);
        q.add(start);
        visit[start.r][start.c] = true;
        ArrayList<Spot> list = new ArrayList<>();
//
//		System.out.println();
//		System.out.print(start.r+" "+start.c+":");

        while(!q.isEmpty()) {
            Spot s = q.remove();
            list.add(s);

            int num = map[s.r][s.c];
            for(int i=0;i<4;i++) {
                int rr = s.r+dr[i];
                int cc = s.c+dc[i];
                if(inRange(rr, cc)&&visit[rr][cc]==false) {
                    int gap = Math.abs(num - map[rr][cc]);
                    if(gap>=L&&gap<=R) {
                        visit[rr][cc] = true;
                        Spot mv = new Spot(rr, cc);
                        q.add(mv);
                    }
                }
            }
        }
        if(list.size()>1) {
            move(list);
            isMove=true;
        }

    }

    static void move(ArrayList<Spot> list) { //population move
        int sum= 0;
        for(int i=0;i<list.size();i++) {
            Spot s = list.get(i);
            sum+=map[s.r][s.c];
        }
        int moveNum = sum/list.size();
        for(int i=0;i<list.size();i++) {
            Spot s = list.get(i);
            map[s.r][s.c] = moveNum;
        }
    }

    static boolean inRange(int r, int c) {
        return(r>=0&&r<N&&c>=0&&c<N);
    }

    static class Spot{
        int r, c;
        Spot(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}