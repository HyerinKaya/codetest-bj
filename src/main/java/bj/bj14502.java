package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj14502 {

    static int N, M, virusCount;
    static int[][] map, virusMap;
    static boolean[][] visit;
    static int[] dirR = {0, 0, 1, -1};
    static int[] dirC = {1, -1, 0, 0};
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit =new boolean[N][M];
        virusMap = new int[N][M];
        answer =0;

        for(int i=0;i<N;i++){
            StringTokenizer str = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                int num = Integer.parseInt(str.nextToken());
                map[i][j] = num;
                if(num!=0){
                    visit[i][j] = true;
                }
            }
        }

        wallDfs(0, 0, 3);
        System.out.println(answer);

    }


    //벽 위치 조합 dfs visit[][] for()
    static void wallDfs(int r, int c, int count){
        if(r>=N&&count>0){
            return;
        }
        if(count<=0){
            for(int i=0; i<map.length;i++){
                virusMap[i] = map[i].clone();
            }

            findVirus();
            return;
        }
        if(map[r][c]==0){
            map[r][c]=1;
            if(c>=M-1){
                wallDfs(r+1, 0, count-1);
            }else{
                wallDfs(r, c+1, count-1);
            }
            map[r][c]=0;
        }
        if(c>=M-1){
            wallDfs(r+1, 0, count);
        }else{
            wallDfs(r, c+1, count);
        }
    }


    //벽의 개수 3개, 3개 모두 써야 함
    //바이러스 퍼지는 dfs
    //바이러스 위치 (r,c)
    static void virusDfs(int r, int c){
        for(int i=0;i<4;i++){
            int rr= r + dirR[i];
            int cc= c + dirC[i];
            if(rr<0||N<=rr||cc<0||M<=cc)continue;
            if (virusMap[rr][cc] == 0) {
                virusMap[rr][cc] = 2;
                virusCount+=1;
                virusDfs(rr, cc);
            }

        }
    }


    //map 돌며 바이러스 찾아서 virusDfs 해주기
    //안전공간 개수 세주기
    static void findVirus(){
        virusCount=0;

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j]==2){
                    virusDfs(i, j);
                }
            }
        }


        int safeCount=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(virusMap[i][j]==0){
                    safeCount+=1;
                }
            }
        }
//        if(safeCount>19){
//            System.out.println("after virus spread");
//            for(int i=0; i<map.length;i++){
//                System.out.println(Arrays.toString(virusMap[i]));
//            }
//            System.out.println("safeCount: "+safeCount);
//            System.out.println();
//        }

        answer = Math.max(safeCount, answer);
    }
}
