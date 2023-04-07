package codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class codetree_treetycoon {

    public static int N, M, answer, map[][], drugMap[][], move[][];
    public static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    public static int[] dc = {0, 1, 1, 0, -1, -1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N]; //전체 map
        drugMap = new int[N][N]; //영양제 위치
        move = new int[M][2]; //해마다 영양제 이동 (방향, 이동 칸수)

        for(int i=0;i<N;i++){
            StringTokenizer str = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }
        for(int i=0;i<M;i++){
            StringTokenizer str = new StringTokenizer(br.readLine());
                move[i][0] = Integer.parseInt(str.nextToken());
                move[i][1] = Integer.parseInt(str.nextToken());
        }

        //초기 영양제 좌하단 4칸
        drugMap[N-2][0]=1;
        drugMap[N-2][1]=1;
        drugMap[N-1][0]=1;
        drugMap[N-1][1]=1;

//        System.out.println("start drugMap      Map");
//        for(int p=0;p<N;p++){
//            System.out.print(Arrays.toString(drugMap[p])+"  "+Arrays.toString(map[p]));
//            System.out.println();
//        }
//        System.out.println();

        for(int i=0;i<M;i++){
            drugMove(i);
//            System.out.println("drugMove() drugMap      Map");
//            System.out.println("방향,이동칸수:"+move[i][0]+" "+move[i][1]);
//            for(int p=0;p<N;p++){
//                System.out.print(Arrays.toString(drugMap[p])+"  "+Arrays.toString(map[p]));
//                System.out.println();
//            }
//            System.out.println();

            grow();

            buyDrug();
        }

        answer =0;
        getTotal();
        System.out.println(answer);

    }

    //영양제 방향과 이동칸수만큼 이동
    static void drugMove(int year){
        int dir = move[year][0];
        int len = move[year][1];
        int[][] tempMap = new int[N][N];
        for(int i=0; i<N;i++){
            for(int j=0;j<N;j++){
                if(drugMap[i][j]==1){
                    int rr = nextRange(i+len*dr[dir]);
                    int cc = nextRange(j+len*dc[dir]);
                    tempMap[rr][cc] = 1;
                }
            }
        }
        for(int i=0;i<N;i++){
            drugMap[i] = tempMap[i].clone();
        }
    }

    //영양제 있는 곳 대각선 나무 수만큼 성장
    static void grow(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(drugMap[i][j] == 1){
                    map[i][j]+=1;
                }
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(drugMap[i][j] == 1){
                    int count=0;//주변대각선 1이상인 나무 수
                    for(int p=1;p<=4;p++){
                        int rr = i+dr[p*2];
                        int cc = j+dc[p*2];
                        if(inRange(rr, cc)){
                            if(map[rr][cc]>=1) count+=1;
                        }
                    }
                    map[i][j]+=count;
                }

            }
        }
    }

    //영양제 없이 2이상인 곳 2만큼 자르기
    static void buyDrug(){
        int[][] tempMap = new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]>=2&&drugMap[i][j]!=1){//2이상이고 영양제 없을 때
                    map[i][j]-=2;
                    tempMap[i][j] = 1;
                }
            }
        }

        for(int i=0;i<N;i++){
            drugMap[i] = tempMap[i].clone();
        }
    }

    static void getTotal(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                answer+=map[i][j];
            }
        }
    }

    static int nextRange(int idx){
        if(idx<0){
            idx = N+idx;
            return idx;
        }else if(idx>=N){
            idx -= N;
            return idx;
        }else{
            return idx;
        }
    }
    static boolean inRange(int r, int c){
        return(r>=0&&r<N&&c>=0&&c<N);
    }
}
