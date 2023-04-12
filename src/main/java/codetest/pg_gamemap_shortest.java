package codetest;

import java.util.*;


public class pg_gamemap_shortest {

    static int N, M, min, map[][], len[][];
    static boolean visit[][];
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String args[]) {

        Scanner in= new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        map = new int[N][M];
        len = new int[N][M];

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                map[i][j]= in.nextInt();
            }
        }

        int answer = 0;
        visit = new boolean[N][M];
        min = Integer.MAX_VALUE;

        for(int i=0;i<N;i++) {
            System.out.println(Arrays.toString(visit[i]));
        }
        for(int i=0;i<N;i++) {
            System.out.println(Arrays.toString(map[i]));
        }

        bfs();

        if(min==Integer.MAX_VALUE){
            answer = -1;
        }else{
            answer = min;
        }

        System.out.println(answer);

        return;
    }

//     static void dfs(int r, int c, int count){
//         if(r==N-1&&c==M-1){
//             min = Math.min(count, min);
//             return;
//         }else if(count>min){
//             return;
//         }
//         for(int i=0;i<4;i++){
//             int rr = r+dr[i];
//             int cc = c+dc[i];
//             if(inRange(rr, cc)&&visit[rr][cc]==false&&map[rr][cc]==1){
//                 visit[rr][cc]=true;
//                 dfs(rr, cc, count+1);
//                 visit[rr][cc]=false;
//             }
//         }


//     }

    static void bfs(){
        Queue<Spot> q = new LinkedList<>();
        Spot start= new Spot(0, 0);
        len[start.r][start.c] = 1;

        q.add(start);
        while(!q.isEmpty()){
            Spot s = q.remove();
            for(int i=0;i<4;i++){
                int rr = s.r+dr[i];
                int cc = s.c+dc[i];
                if(inRange(rr, cc)&&map[rr][cc]==1&&len[rr][cc]==0){
                    len[rr][cc] = len[s.r][s.c]+1;
                    Spot move = new Spot(rr, cc);
                    if(rr==N-1&&cc==M-1) {
                        min = Math.min(min,len[rr][cc]);
                        continue;
                    }//else if(len[rr][cc]>minLen) continue;
                    q.add(move);
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

    static boolean inRange(int r, int c){
        return(r>=0&&r<N&&c>=0&&c<M);
    }

}
