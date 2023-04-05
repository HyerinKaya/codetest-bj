package codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class codetree_codetreebread {
    static int N, M, map[][], arr[][], len[][];
    static boolean lock;
    static String[][] bfsPath;
    static ArrayList<String> pathList = new ArrayList<>();
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        arr = new int[M][2]; //편의점 위치 배열


        for(int i=0;i<N;i++){
            StringTokenizer str = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        for(int i=0;i<M;i++){
            StringTokenizer str = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(str.nextToken())-1;
            arr[i][1] = Integer.parseInt(str.nextToken())-1;
        }

        int arrive = 0; //도착한 사람 수
        int time = 1; //전체 걸린 시간
        lock = false;
        Queue<Person> pq = new LinkedList<>(); //돌아다니고 있는 사람 큐
        int num=1; //사람 차례대로 보내기 위해 보낸 수 세기

        while(arrive<M){
            if(time==num&&num<=M){
                Person person = new Person(-1, -1, arr[num-1][0], arr[num-1][1], num-1);
                pq.offer(person);
                num+=1;
            }

            int size = pq.size();
            int lockCnt = pq.size();


            for(int i=0;i<size;i++){ //큐에 있는 사람 돌아가며 작업
                Person p = pq.remove();

                if(pathList.size()>p.idx){ //path 있을(이동 중) 시
                    if(lock == true&&lockCnt>0){ //lock 걸려 있을 시 경로 재검색
                        Spot shop = new Spot(p.sr, p.sc);
                        Spot person = new Spot(p.r, p.c);
                        String path = shopBfs(person, shop);
                        pathList.add(p.idx, path);

                        //한 칸 이동
                        p = move(p);

                        lockCnt-=1;
                        if(lockCnt<1) lock=false;

                        if(p.r==p.sr&&p.c==p.sc){ //편의점 도착
                            map[p.r][p.c] = -1;
                            arrive+=1;
                            lock = true;
                            lockCnt = pq.size();
                            lockCnt -=1;
                        }else{
                            pq.offer(p);
                        }

                    }else{  //lock 없음, 한 칸 이동
                        p = move(p);

                        lockCnt-=1;
                        if(lockCnt<1) lock=false;

                        if(p.r==p.sr&&p.c==p.sc){ //편의점 도착
                            arrive+=1;
                            map[p.r][p.c] = -1;
                            lock = true;
                        }else{
                            pq.offer(p);
                        }

                    }
                }else if(pathList.size()<=p.idx){ //해당 사람 path 없을 시 캠프 찾기+편의점 최단경로 찾기
                    Spot shop = new Spot(p.sr, p.sc);
                    Spot camp = campBfs(shop);
                    p.r = camp.r;
                    p.c = camp.c; //캠프 위치에 사람 넣어주기
                    map[p.r][p.c] = -1; //lock 걸어주기
                    lock = true;
                    String path = shopBfs(camp, shop);

                    pathList.add(path); //list에 경로 넣어주기

                    //한 칸 이동
                    p = move(p);

                    lockCnt-=1;
                    if(lockCnt<1) lock=false;

                    if(p.r==p.sr&&p.c==p.sc){ //편의점 도착
                        arrive+=1;
                        map[p.r][p.c] = -1;
                        lock = true;
                        lockCnt -=1;
                    }else{
                        pq.offer(p);
                    }
                }
            }
            time+=1;

        }

        System.out.println(time);

    }

    static class Person{
        int r, c, sr, sc, idx;
        Person(int r, int c, int sr, int sc, int idx){
            this.r = r;
            this.c = c;
            this.sr = sr;
            this.sc = sc;
            this.idx = idx;
        }
    }

    static class Spot {
        int r,c;
        Spot(int row, int col) {
            this.r = row;
            this.c = col;
        }
    }

    //편의점에서 가장 가까운 캠프 위치 반환
    static Spot campBfs(Spot shop) { //편의점 제일 가까운 캠프 찾기
        Queue<Spot> q = new LinkedList<>();
        q.add(shop);
        Spot s, mv, ans = new Spot(Integer.MAX_VALUE, Integer.MAX_VALUE);
        len = new int[N][N]; // 해당 위치까지의 거리 저장
        int minL = Integer.MAX_VALUE; //최소시간
        while(!q.isEmpty()) {
            s = q.remove();
            for(int i=0; i<4; i++) {
                mv = new Spot(s.r+dr[i], s.c+dc[i]);

                if(isArea(mv) && canMove(mv)) { //이동 가능한 위치면 이동
                    len[mv.r][mv.c] = len[s.r][s.c] + 1; // 해당 위치까지 거리 저장
                    if(len[mv.r][mv.c] > minL)
                        // 지금까지 찾은 최소 거리 넘어가면 해당 길은 탐색 X
                        continue;
                    if(map[mv.r][mv.c] == 1) { //해당 위치에 lock되지 않은 camp가 있는 경우
                        if(minL > len[mv.r][mv.c]) { // 이전에 찾았던 거리보다 더 가까운 camp 찾은 경우
                            minL = len[mv.r][mv.c];
                            ans = new Spot(mv.r, mv.c);
                        }
                        else if((ans.r == mv.r && ans.c > mv.c) || ans.r > mv.r) // 거리 같을 때 우선 순위에 있는 곳
                            ans = new Spot(mv.r, mv.c);
                    }
                    q.add(mv); // 계속 경로 탐색하기 위해 이동이 가능했던 곳은 다음 방문 예정 루트로 저장
                }
            }
        }
        return ans; // 우선순위가 가장 높은 캠프 위치 반환(만약 찾지 못하면 Integer.MAX_VALUE값 반환)
    }

    //현재위치에서 편의점까지 최단경로 반환
    static String shopBfs(Spot person, Spot shop){ //현 위치에서 편의점 가는 가장 짧은 길 찾기
        Queue<Spot> q = new LinkedList<>();
        q.add(person);
        Spot s, mv, ans = new Spot(Integer.MAX_VALUE, Integer.MAX_VALUE);
        len = new int[N][N]; //해당 위치까지의 거리 저장
        bfsPath = new String[N][N]; //경로 저장
        map[shop.r][shop.c] = 2;
        bfsPath[person.r][person.c] = "";

        int minL = Integer.MAX_VALUE; //최소시간
        while(!q.isEmpty()){
            s = q.remove();
            for(int i=0;i<4;i++){
                mv = new Spot(s.r+dr[i], s.c+dc[i]);

                if(isArea(mv)&&canMove(mv)){
                    len[mv.r][mv.c] = len[s.r][s.c]+1;
                    StringBuilder sb = new StringBuilder();
                    sb.append(bfsPath[s.r][s.c]+String.valueOf(mv.r)+" "+ String.valueOf(mv.c)+",");
                    bfsPath[mv.r][mv.c] = sb.toString();

                    if(len[mv.r][mv.c] > minL)
                        // 지금까지 찾은 최소 거리 넘어가면 해당 길은 탐색 X
                        continue;
                    if(map[mv.r][mv.c] == 2) { //해당 위치에 목표 편의점이 있는 경우
                        if(minL > len[mv.r][mv.c]) { // 이전에 찾았던 거리보다 더 가까운 경로를 찾은 경우
                            minL = len[mv.r][mv.c];
                            ans = new Spot(mv.r, mv.c);
                        }
                    }
                    q.add(mv); // 계속 경로 탐색하기 위해 이동이 가능했던 곳은 다음 방문 예정 루트로 저장
                }
            }

        }
        map[shop.r][shop.c] = 0; //편의점 위치 임시표시 되돌리기

        return bfsPath[ans.r][ans.c];
    }

    static Person move(Person p){
        //한 칸 이동
        String path = pathList.get(p.idx);
        String[] pathArr = path.split(",");
        String[] next = pathArr[0].split(" ");
        p.r = Integer.parseInt(next[0]);
        p.c = Integer.parseInt(next[1]);
        StringBuilder sb = new StringBuilder();
        for(int j=1;j<pathArr.length;j++){
            sb.append(pathArr[j]+",");
        }
        path = sb.toString();
        pathList.add(p.idx, path);
        pathList.remove(p.idx+1);

        return p;
    }

    // map에 존재하는 위치인지 판단
    static boolean isArea(Spot s) {
        return s.r>=0 && s.r<N && s.c>=0 && s.c<N;
    }

    //해당 칸 lock 아닌지 + 첫 방문인지 판단
    static boolean canMove(Spot s) {
        return map[s.r][s.c] !=-1 && len[s.r][s.c] == 0;
    }
}

