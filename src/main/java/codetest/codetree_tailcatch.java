package codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class codetree_tailcatch {

    //!!문제, 꼬리 머리가 한바퀴 돌아서 오면 팀 위치 헷갈림. (...머리aaa꼬리.)인지(aaa머리...꼬리a)인지

    public static int n, m, k, round, map[][], path[][][], head[], pathLen[];
    public static int[] dr = {0, -1, 0, 1}; // > ^ < v
    public static int[] dc = {1, 0, -1, 0};
    public static int[] sr, sc;
    public static boolean[][] visit;
    public static ArrayList<ArrayDeque<Spot>> onSpot;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        path = new int[m][n*n][2]; //각 팀 경로 저장(팀, 경로, 좌표)
        visit = new boolean[n][n]; //처음 길 찾는 dfs 시 사용
        head = new int[m]; //각 팀 머리 위치(0 first, 1 last)
        onSpot = new ArrayList<>(); //각 팀 지나는 경로 저장
        // headtail[team][0]머리, headtail[team][1]꼬리
        pathLen = new int[m];
        sr = new int[]{0, n-1, n-1, 0};
        sc = new int[]{0, 0, n-1, n-1};


        for(int i=0;i<n;i++){
            StringTokenizer str = new StringTokenizer(br.readLine());
            for(int j=0; j<n;j++){
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        round = 0;

    }

    static class Spot{
        int r, c;
        Spot(int r, int c){
            this.r = r;
            this.c = c;
        }

    }


    //map 돌다가 0아닌 수 발견시 경로 찾기 시작
    static void dfs(int r, int c, int team, int idx) {
        for (int i = 0; i < 4; i++) {
            int rr = r + dr[i];
            int cc = c + dc[i];
            if (inRange(rr, cc) && visit[rr][cc] == false && map[rr][cc] > 0) {
                visit[rr][cc] = true;
                path[team][idx][0] = rr; //idx 경로 순서대로 저장 위해 카운트
                path[team][idx][1] = cc;
                pathLen[team] += 1;
                if (map[rr][cc] == 1) {//머리
                    head[team] = idx;
                    if (onSpot.size() >= team) {
                        ArrayDeque<Spot> dq = onSpot.get(team);
                        Spot spot = new Spot(rr, cc);
                        dq.offerFirst(spot);
                        onSpot.remove(team);
                        onSpot.add(team, dq);
                    } else {
                        ArrayDeque<Spot> dq = new ArrayDeque<>();
                        Spot spot = new Spot(rr, cc);
                        dq.offerFirst(spot);
                        onSpot.add(dq);
                    }
                } else if (map[rr][cc] == 2) {//중간
                    ArrayDeque<Spot> dq = onSpot.get(team);
                    Spot spot = dq.peekFirst();
                    if (spot.r == path[team][idx][0] && spot.c == path[team][idx][1]) {//데크에 머리 있을 시 뒤로 넣어주기
                        spot = new Spot(rr, cc);
                        dq.offerLast(spot);
                        onSpot.remove(team);
                        onSpot.add(team, dq);
                    } else {//데크에 꼬리 있을 시 뒤로 넣어주기
                        spot = new Spot(rr, cc);
                        dq.offerFirst(spot);
                        onSpot.remove(team);
                        onSpot.add(team, dq);
                    }
                } else if (map[rr][cc] == 3) {//꼬리
                    if (onSpot.size() >= team) {
                        ArrayDeque<Spot> dq = onSpot.get(team);
                        Spot spot = new Spot(rr, cc);
                        dq.offerLast(spot);
                        onSpot.remove(team);
                        onSpot.add(team, dq);
                    } else {
                        ArrayDeque<Spot> dq = new ArrayDeque<>();
                        Spot spot = new Spot(rr, cc);
                        dq.offerLast(spot);
                        onSpot.add(dq);
                    }
                    dfs(rr, cc, team, idx + 1);
                }
            }
        }
    }

    static boolean inRange(int rr, int cc){
        return (rr>=0&&rr<n&&cc>=0&&cc<n);
    }

    //모든 팀 돌며 onSpot의 큐 밀어주기
    static void rotate(){
        for(int i=0;i<m;i++){
            //이동 방향 판단
            int[][] arr = path[i];
            int idx = head[i];
            ArrayDeque<Spot> dq = onSpot.get(i);
            if(arr[i][0]==dq.peekFirst().r&&arr[i][1]==dq.peekFirst().c){//앞이 머리
                dq.removeLast();
                if(idx<1) idx = pathLen[i]-1;
                Spot spot = new Spot(arr[idx-1][0], arr[idx-1][1]);
                dq.offerFirst(spot);
                onSpot.remove(i);
                onSpot.add(i, dq);
            }else if(arr[i][0]==dq.peekLast().r&&arr[i][1]==dq.peekLast().c){//뒤가 머리
                dq.removeLast();
                if(idx>pathLen[i]-1) idx = 0;
                Spot spot = new Spot(arr[idx+1][0], arr[idx+1][1]);
                dq.offerLast(spot);
                onSpot.remove(i);
                onSpot.add(i, dq);
            }

        }
    }


    //모든 팀 돌며 공이 지나는 길 위의 가장 가까운 사람 찾기
    static void ball(){
        int team = 0; //포인트 얻게 될 팀
        int person = 0;
        int min = 0; //가장 가까운 거리
        for(int i=0;i<m;i++){ //팀 돌며
            int teamSize = onSpot.get(i).size();
            int dir = ((k-1)/n)%4; // > ^ < v 방향
            int startR = sr[dir]+((k-1)%n);

            //해당 팀의 경로 중 공 지나는 길과 겹치는 점 있는지 확인
            for(int j=0;j<teamSize;j++){

            }
        }
    }

    static void getPoint(){

    }

    //공 잡고 반대 방향으로 머리 전환
    static void backward(int team){

    }


}
