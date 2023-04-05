package codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class codetree_destroytree {

    static int n, m, k, cy, answer, map[][], killmap[][], spreadmap[][];
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};
    static int[] ddr = {-1, -1, 1, 1};
    static int[] ddc = {-1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        cy = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        answer = 0;

        for(int i=0;i<n;i++){
            StringTokenizer str = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }


        int year = 1;
        while(year<=m){

            System.out.println("---------year:"+year+"---------");

            //나무 칸 돌며 성장
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(map[i][j]>0) {
                        grow(i, j);
                    }
                }
            }

            //나무 칸 돌며 번식
            spreadmap = new int[n][n];
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(map[i][j]>0){
                        spreadmap[i][j] = 1;
                    }else if(map[i][j]<0){
                        spreadmap[i][j] = -1;
                    }
                }
            }
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(spreadmap[i][j]>0){
                        spread(i, j);
                    }
                }
            }
            System.out.println("spread");
            for(int i=0;i<n;i++){
                System.out.println(Arrays.toString(map[i]));
            }
            System.out.println();

            //모든 칸 돌며 제초제 뿌릴 시 죽을 나무 개수 기록
            killmap = new int[n][n];
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    killCheck(i,j);
                }
            }


            //killmap 돌며 나무 가장 많이 죽는 칸 찾기
            int drugR = 0;
            int drugC = 0;
            int maxKill = 0;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(killmap[i][j]>maxKill){
                        maxKill=killmap[i][j];
                        drugR = i;
                        drugC = j;
                    }
                }
            }

            //찾은 칸에 제초제 뿌리기
            drug(drugR, drugC);

            System.out.println("drug : "+drugR+" "+drugC);
            for(int i=0;i<n;i++){
                System.out.println(Arrays.toString(map[i]));
            }
            System.out.println("killed:"+answer);
            System.out.println();

            drugFade();
            year+=1;
        }
        System.out.println(answer);

    }

    static void grow(int r, int c){
        int tree = 0;
        for(int i=0; i<4;i++){
            int rr = r+dr[i];
            int cc = c+dc[i];
            if(rr<0||rr>=n||cc<0||cc>=n)continue;
            if(map[rr][cc]>0){
                tree+=1;
            }
        }
        map[r][c] += tree;
    }

    static void spread(int r, int c){
        int blank = 0;
        for(int i=0;i<4;i++){ //빈칸 찾기
            int rr = r+dr[i];
            int cc = c+dc[i];
            if(rr<0||rr>=n||cc<0||cc>=n) continue;
            if(spreadmap[rr][cc]==0){
                blank+=1;
            }
        }
        if(blank==0) return;
        int newTree = map[r][c]/blank;
        for(int i=0;i<4;i++){ //빈칸에 나무 번식
            int rr = r+dr[i];
            int cc = c+dc[i];
            if(rr<0||rr>=n||cc<0||cc>=n) continue;
            if(spreadmap[rr][cc]==0){
                map[rr][cc]+=newTree;
            }
        }
    }

    static void killCheck(int r, int c){
            int killCnt = map[r][c];
            if(map[r][c]<1) return;
            for(int p=0;p<4;p++){ //대각선 방향 돌며 나무 죽이기
                int rr = r;
                int cc = c;
                for(int q=0;q<k;q++){
                    rr = rr+ddr[p];
                    cc = cc+ddc[p];
                    if(rr<0||rr>=n||cc<0||cc>=n) continue;
                    if(map[rr][cc]<1){
                        break;
                    }else{
                        killCnt+=map[rr][cc];
                    }
                }
            }
            killmap[r][c] = killCnt;
    }

    static void drug(int r, int c){
        if(map[r][c]>0) answer+=map[r][c];
        map[r][c] = -1*(cy+100);
        for(int i=0;i<4;i++){ //대각선 방향 돌며 제초제 뿌리기
            int rr = r;
            int cc = c;
            for(int j=0;j<k;j++){
                rr = rr+ddr[i];
                cc = cc+ddc[i];
                if(rr<0||rr>=n||cc<0||cc>=n) continue;
                if(map[rr][cc]==-1) break;
                if(map[rr][cc]<-1||map[rr][cc]==0){
                    map[rr][cc] = -1*(cy+100);
                    break;
                }else{
                    answer+=map[rr][cc];
                    map[rr][cc] = -1*(cy+100);
                }
            }
        }

    }

    static void drugFade(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(map[i][j] == -100){
                    map[i][j]=0;
                }else if(map[i][j]<-100){
                    map[i][j] += 1;
                }
            }
        }
    }

}
