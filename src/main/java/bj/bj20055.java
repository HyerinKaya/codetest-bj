package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicBoolean;

public class bj20055 {

    static int N, K, level, answer, belt[];
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        belt = new int[2*N];
        q = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<2*N;i++){
            belt[i] = Integer.parseInt(st.nextToken());
        }

        level=1;
        answer=0;

        check();



            turn();
            botMove();
            botUp();
            level+=1;
            check();

    }



    static void botUp(){ //벨트에 로봇을 올림
        if(belt[0]>0){
            q.offer(0);
            belt[0]-=1;
        }
//
//        System.out.print("botUp: ");
//        q.forEach(I-> System.out.print(I+" "));
//        System.out.println(Arrays.toString(belt));
//        System.out.println();

        check();
    }

    static void botMove(){ //컨베이어벨트 다음 칸으로 로봇 혼자 이동
        int size = q.size();
        for(int i=0;i<size;i++){
            int bot = q.poll();
            if(belt[bot+1]>0&!q.contains(bot+1)){
                belt[bot+1]-=1;
                //check();
                if(bot+1>=N-1){
                    continue;
                }
                q.offer(bot+1);

//                System.out.print("botMove: ");
//                q.forEach(I-> System.out.print(I+" "));
//                System.out.println(Arrays.toString(belt));
//                System.out.println();
            }else{
                q.offer(bot);

//                System.out.print("botMove: ");
//                q.forEach(I-> System.out.print(I+" "));
//                System.out.println(Arrays.toString(belt));
//                System.out.println();
            }
        }
    }

    static void turn(){ //컨베이어벨트 로봇을 싣고 회전
        int temp=0;
        int pre = belt[2*N-1];
        for(int i=0; i<2*N;i++){ //벨트 회전
            temp = belt[i];
            belt[i] = pre;
            pre = temp;
        }

        int size = q.size();
        for(int i=0;i<size;i++){  //로봇 회전
            int bot = q.poll();
            bot+=1;
            if(bot>=N-1){
                continue;
            }else{
                q.offer(bot);
            }
        }

//        System.out.print("turn: ");
//        System.out.println(Arrays.toString(belt));
//        System.out.println();
    }

    static void check(){ //벨트값 0이하 확인하고 종료 판단
        int zCnt=0;
        for(int i=0;i<2*N;i++){
            if(belt[i]<=0) zCnt+=1;
        }
        if(zCnt>=K){
            answer=level;
            System.out.println(answer);
            System.exit(0);
        }
    }
}
