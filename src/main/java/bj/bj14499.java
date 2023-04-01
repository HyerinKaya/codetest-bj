package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class bj14499 {

    static int N, M, R, C, K;
    static int[][] map, dice;
    static int[] move;
    static ArrayList<Integer> answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dice = new int[4][3];
        map = new int[N][M];
        move = new int[K];
        answer = new ArrayList<>();

        for(int i=0;i<N;i++){
            StringTokenizer str = new StringTokenizer(br.readLine());
            for(int j=0; j<M;j++){
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        //(방향 1>, 2<, 3^, 4v)
        StringTokenizer str = new StringTokenizer(br.readLine());
        for(int i=0;i<K;i++){
            move[i] = Integer.parseInt(str.nextToken());
        }

        doMove();
        for(int i=0;i<answer.size();i++){
            System.out.println(answer.get(i));
        }

    }

    //움직임 명령 수행
    static void doMove(){
        for(int i:move){
            if(i==1){
                if(C<M-1){
                    if(map[R][C+1]!=0){
                        dice[1][2]=map[R][C+1];
                        map[R][C+1]=0;
                    }else{
                        map[R][C+1]=dice[1][2];
                    }
                    rollDice(1);
                    answer.add(dice[1][1]);
                    C+=1;
                }else{
                }
            }
            if(i==2){
                if(C>0){
                    if(map[R][C-1]!=0){
                        dice[1][0]=map[R][C-1];
                        map[R][C-1]=0;
                    }else{
                        map[R][C-1]=dice[1][0];
                    }
                    rollDice(2);
                    answer.add(dice[1][1]);
                    C-=1;
                }else{
                }
            }
            if(i==3){
                if(R>0){
                    if(map[R-1][C]!=0){
                        dice[0][1]=map[R-1][C];
                        map[R-1][C]=0;
                    }else{
                        map[R-1][C]=dice[0][1];
                    }
                    rollDice(3);
                    answer.add(dice[1][1]);
                    R-=1;
                }else{
                }
            }
            if(i==4){
                if(R<N-1){
                    if(map[R+1][C]!=0){
                        dice[2][1]=map[R+1][C];
                        map[R+1][C]=0;
                    }else{
                        map[R+1][C]=dice[2][1];
                    }
                    rollDice(4);
                    answer.add(dice[1][1]);
                    R+=1;
                }else{
                }
            }
        }
    }

    //주사위 형태변화(방향 1>, 2<, 3^, 4v)
    static void rollDice(int dir){
        if(dir==1){
            int temp = dice[1][2];
            dice[1][2] = dice[1][1];
            dice[1][1] = dice[1][0];
            dice[1][0] = dice[3][1];
            dice[3][1] = temp;
        }
        if(dir==2){
            int temp = dice[1][0];
            dice[1][0] = dice[1][1];
            dice[1][1] = dice[1][2];
            dice[1][2] = dice[3][1];
            dice[3][1] = temp;
        }
        if(dir==3){
            int temp = dice[0][1];
            dice[0][1] = dice[1][1];
            dice[1][1] = dice[2][1];
            dice[2][1] = dice[3][1];
            dice[3][1] = temp;
        }
        if(dir==4){
            int temp = dice[3][1];
            dice[3][1] = dice[2][1];
            dice[2][1] = dice[1][1];
            dice[1][1] = dice[0][1];
            dice[0][1] = temp;
        }
    }

}
