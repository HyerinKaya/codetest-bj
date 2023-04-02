package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj14503 {

    static int  N, M, r, c, dir, answer;
    static int[][] map;
    static int[] ar = {1, 0, -1, 0, 1, 0, -1};
    static int[] ac = {0, 1, 0, -1, 0, 1, 0};
    static int[] d = {2, 1, 0, 3, 2, 1, 0};
    //static int[] ar = {0, 1, 0, -1, 0, 1, 0, -1};
    //static int[] ac = {-1, 0, 1, 0, -1, 0, 1, 0};
    //static int[] d = {3, 2, 1, 0, 3, 2, 1, 0};
    static int[] br = {1, 0, -1, 0};
    static int[] bc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0;i<N;i++) {
            StringTokenizer str = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        answer=0;
        clean();

        System.out.println(answer);
    }

    static void clean(){
        if(map[r][c]==0){
            map[r][c]=2;
            answer+=1;
/*
            for(int i=0;i<N;i++){
                System.out.println(Arrays.toString(map[i]));
            }
            System.out.println();
*/
            around();
        }else{
            back();
        }
    }

    static void around(){
        for(int i=(3-dir);i<(7-dir);i++){
            //반시계방향으로 돌며 먼저 나타나는 0칸 청소
            //if(r+ar[i]<0||r+ar[i]>=N||c+ac[i]<0||c+ac[i]>=M) continue;
            if(map[r+ar[i]][c+ac[i]]==0){
                r+=ar[i];
                c+=ac[i];
                dir= d[i];
                clean();
                return;
            }
            //돌았는데 없을 시 후진
            if(i==6-dir&&map[r+ar[i]][c+ac[i]]!=0){
                back();
            }
        }
    }

    static void back(){
        //후진 불가 시 STOP
        if(map[r+br[dir]][c+bc[dir]]==1) return;
        r+=br[dir];
        c+=bc[dir];
        around();
    }

}
