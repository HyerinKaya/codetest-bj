package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj14891 {
    static int answer;
    static int[][] gear = new int[4][8];
    static int N;
    static int[][] move;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0;i<4;i++){
            String input = br.readLine();
            String[] temp = input.split("");
            for(int j=0;j<8;j++){
                gear[i][j] = Integer.parseInt(temp[j]);
            }
        }


        N = Integer.parseInt(br.readLine());
        move = new int[N][2];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            move[i][0] = Integer.parseInt(st.nextToken());
            move[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++){
            int start = move[i][0]-1;
            int startDir = move[i][1];
            if(start==0){
                if(gear[start+1][6]!=gear[start][2]){//극이 다름, 반대로 돌아감
                    if(gear[start+2][6]!=gear[start+1][2]){
                        if(gear[start+3][6]!=gear[start+2][2]){
                            moveGear(start+3, 0-startDir);
                        }
                        moveGear(start+2, startDir);
                    }
                    moveGear(start+1, 0-startDir);
                }
                moveGear(start, startDir);
            }
            if(start==1){
                if(gear[start+1][6]!=gear[start][2]){//극이 다름, 반대로 돌아감
                    if(gear[start+2][6]!=gear[start+1][2]){
                        moveGear(start+2, startDir);
                    }
                    moveGear(start+1, 0-startDir);
                }
                if(gear[start-1][2]!=gear[start][6]){
                    moveGear(start-1, 0-startDir);
                }
                moveGear(start, startDir);
            }
            if(start==2){
                if(gear[start+1][6]!=gear[start][2]){//극이 다름, 반대로 돌아감
                    moveGear(start+1, 0-startDir);
                }
                if(gear[start-1][2]!=gear[start][6]){
                    if(gear[start-2][2]!=gear[start-1][6]){
                        moveGear(start-2, startDir);
                    }
                    moveGear(start-1, 0-startDir);
                }
                moveGear(start, startDir);
            }
            if(start==3){
                if(gear[start-1][2]!=gear[start][6]){//극이 다름, 반대로 돌아감
                    if(gear[start-2][2]!=gear[start-1][6]){
                        if(gear[start-3][2]!=gear[start-2][6]){
                            moveGear(start-3, 0-startDir);
                        }
                        moveGear(start-2, startDir);
                    }
                    moveGear(start-1, 0-startDir);
                }
                moveGear(start, startDir);
            }
/*
            System.out.println(Arrays.toString(gear[0]));
            System.out.println(Arrays.toString(gear[1]));
            System.out.println(Arrays.toString(gear[2]));
            System.out.println(Arrays.toString(gear[3]));
            System.out.println();

 */

        }



        answer = 0;
        //N극0, S극1
        for(int i=0;i<4;i++){
            if(gear[i][0]==1){
                answer+=Math.pow(2,i);
            }
        }
        System.out.println(answer);
    }

    //move one gear, dir1:right/dir2:left
    static void moveGear(int gearNum, int dir){
        if(dir==1){
            int preTemp =gear[gearNum][7];
            int temp=0;
            for(int i=0; i<8;i++){
                temp = gear[gearNum][i];
                gear[gearNum][i] = preTemp;
                preTemp = temp;
            }
        }
        if(dir==-1){
            int preTemp =gear[gearNum][0];
            int temp=0;
            for(int i=7; i>=0;i--){
                temp = gear[gearNum][i];
                gear[gearNum][i] = preTemp;
                preTemp = temp;
            }
        }
    }
}
