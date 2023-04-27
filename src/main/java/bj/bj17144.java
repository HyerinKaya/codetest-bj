package bj;

import java.util.*;
import java.io.*;

public class bj17144 {

    static int R, C, T, map[][], airR[];
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        airR = new int[2];

        int airCnt = 0;
        for(int i=0;i<R;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] ==-1) {
                    airR[airCnt] = i;
                    airCnt++;
                }
            }
        }

        for(int time=0;time<T;time++) {
            spread();
            rotate();
        }

        int answer =0;
        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                if(map[i][j]<0) continue;
                answer+=map[i][j];
            }
        }

        System.out.println(answer);

    }

    static void spread() {
        int[][] temp = new int[R][C];
        for(int i=0;i<R;i++) {
            temp[i] = map[i].clone();
        }

        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                if(map[i][j]>0) {
                    for(int p=0;p<4;p++) {
                        int rr = i+dr[p];
                        int cc = j+dc[p];
                        if(inRange(rr, cc)) {
                            int spreadNum= map[i][j]/5;
                            temp[rr][cc]+= spreadNum;
                            temp[i][j] -= spreadNum;
                        }
                    }
                }
            }
        }

        for(int i=0;i<R;i++) {
            map[i] = temp[i].clone();
        }
    }

    static void rotate(){
        int upR = airR[0];
        int downR = airR[1];
        int[][] upArr = new int[upR+1][C];
        int[][] downArr = new int[R-downR][C];
        int[][] upArr2 = new int[upR+1][C];
        int[][] downArr2 = new int[R-downR][C];

        for(int i=0;i<upR+1;i++) {
            upArr[i] = map[i].clone();
            upArr2[i] = map[i].clone();
        }
        for(int i=0;i<R-downR;i++) {
            downArr[i] = map[i+downR].clone();
            downArr2[i] = map[i+downR].clone();
        }


        for (int i = 0; i < upR+1; i++) {
            for (int j = 0; j < C; j++) {
                //anti-clockwise rotate
                if(upArr2[i][j]==-1) continue;
                if(i==0&&j<C-1) { //upside
                    if(upArr[i][j+1]==-1) {
                        upArr2[i][j] = 0;
                        continue;
                    }
                    upArr2[i][j] = upArr[i][j+1];
                }
                if(i==upR&&j>0){  //downside
                    if(upArr[i][j+-1]==-1) {
                        upArr2[i][j] = 0;
                        continue;
                    }
                    upArr2[i][j] = upArr[i][j-1];
                }
                if(j==0&&i>0) { //leftside
                    if(upArr[i-1][j]==-1) {
                        upArr2[i][j] = 0;
                        continue;
                    }
                    upArr2[i][j] = upArr[i-1][j];
                }
                if(j==C-1&&i<upR) { //rightside
                    upArr2[i][j] = upArr[i+1][j];
                }
            }
        }

        for (int i = 0; i < R-downR; i++) {
            for (int j = 0; j < C; j++) {
                if(downArr2[i][j]==-1) continue;
                //clockwise rotate
                if(i==0&&j>0) { //upside
                    if(downArr[i][j-1]==-1) {
                        downArr2[i][j] = 0;
                        continue;
                    }
                    downArr2[i][j] = downArr[i][j-1];
                }
                if(i==R-downR-1&&j<C-1){  //downside
                    if(downArr[i][j+1]==-1) {
                        downArr2[i][j] = 0;
                        continue;
                    }
                    downArr2[i][j] = downArr[i][j+1];
                }
                if(j==0&&i<R-downR-1) { //leftside
                    if(downArr[i+1][j]==-1) {
                        downArr2[i][j] = 0;
                        continue;
                    }
                    downArr2[i][j] = downArr[i+1][j];
                }
                if(j==C-1&&i>0) { //rightside
                    downArr2[i][j] = downArr[i-1][j];
                }
            }
        }

        for(int i=0;i<upR+1;i++) {
            map[i] = upArr2[i].clone();
        }
        for(int i=0;i<R-downR;i++) {
            map[i+downR] = downArr2[i].clone();
        }
    }

    static boolean inRange(int r, int c) {
        return (r>=0&&r<R&&c>=0&&c<C&&map[r][c]!=-1);
    }

}