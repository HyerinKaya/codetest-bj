package codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class codetree_battleground {
    static int n,m,k, map[][][], gun[], point[], visit[][];
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static ArrayList<Player> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][n][n];
        visit = new int[n][n]; //player들 위치 표시, fight/moveL 사용
        gun = new int[m];
        point = new int[m];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j][0] = Integer.parseInt(st.nextToken());
            }
        }

        Player fake = new Player(0, 0, 0, 0);
        list.add(fake);
        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[4];
            arr[0] = Integer.parseInt(st.nextToken())-1;
            arr[1] = Integer.parseInt(st.nextToken())-1;
            arr[2] = Integer.parseInt(st.nextToken());
            arr[3] = Integer.parseInt(st.nextToken());
            Player player = new Player(arr[0], arr[1], arr[2], arr[3]);
            visit[player.r][player.c]=i;
            list.add(player);
        }

//        System.out.println("initial setting");
//        for(int i=0;i<n;i++){
//            System.out.println(Arrays.toString(visit[i]));
//        }
//        System.out.println();

        for(int i=0;i<k;i++){
            for(int j=1;j<=m;j++){
                move(j);
            }
        }

        for(int i=0;i<m;i++){
            System.out.print(point[i]+" ");
        }

    }

    static class Player{
        int r;
        int c;
        int d;
        int s;
        Player(int x, int y, int dir, int strength){
            this.r = x;
            this.c = y;
            this.d = dir;
            this.s = strength;
        }

    }


    static void move(int idx){
        //앞으로 이동
        Player player = list.get(idx);
        int dir = player.d;
        int rr = player.r+dr[dir];
        int cc = player.c+dc[dir];
        if(rr>=0 && rr<n && cc>=0 && cc<n){
            //System.out.println("idx: "+idx+" /r,c: "+player.r+" "+player.c);
            visit[player.r][player.c] = 0; //visit 위치 수정
            player.r=rr;
            player.c=cc;
            list.remove(idx);
            list.add(idx, player);
            if(visit[rr][cc]!=0){
                fight(idx, visit[rr][cc]);
            }else{
                getGun(idx);
                visit[rr][cc]=idx;
            }
        }else{ //벗어날 시 반대방향
            if(dir==0){
                dir=2;
            }else if(dir==1){
                dir=3;
            }else if(dir==2){
                dir=0;
            }else if(dir==3){
                dir=1;
            }
            player.d=dir;
            visit[player.r][player.c] = 0; //visit 위치 수정
            player.r+=dr[dir];
            player.c+=dc[dir];
            if(visit[player.r][player.c]!=0){
                fight(idx, visit[player.r][player.c]);
            }else{
                list.remove(idx);
                list.add(idx, player);
                visit[player.r][player.c] = idx;
                getGun(idx);
            }
        }

//        System.out.println("move: "+idx+" dir:"+player.d+" gun:"+Arrays.toString(gun));
//        for(int i=0;i<n;i++){
//            System.out.println(Arrays.toString(visit[i]));
//        }
//        System.out.println();
    }

    static void getGun(int idx){
        Player player = list.get(idx);
        int r = player.r;
        int c = player.c;
        Arrays.sort(map[r][c]);
        if(gun[idx-1]<map[r][c][n-1]){
            int temp = gun[idx-1];
            gun[idx-1]= map[r][c][n-1];
            map[r][c][n-1]=temp;
        }
    }

    static void fight(int aidx, int bidx){
        Player a = list.get(aidx);
        Player b = list.get(bidx);
        int result = (a.s+gun[aidx-1])-(b.s+gun[bidx-1]);
        int win =0;
        if(result==0){
            win = a.s-b.s;
        }

        if(result>0||win>0){
            //a승리
            //System.out.println(aidx+":"+a.s+" "+bidx+":"+b.s+" "+aidx+" win:"+result+" "+Arrays.toString(gun));
            point[aidx-1]+=result; //a포인트 얻기
            visit[a.r][a.c]=aidx; //a가 칸 차지
            map[b.r][b.c][0] = gun[bidx-1]; //b총기 내려놓기
            gun[bidx-1]=0;
            getGun(aidx);
            moveL(bidx);//b움직이기
        }else if(result<0||win<0) {
            //b승리
            //System.out.println(aidx+":"+a.s+" "+bidx+":"+b.s+" "+aidx+" win:"+-1*result+" "+Arrays.toString(gun));
            point[bidx-1]+=-1*result; //b포인트 얻기
            visit[b.r][b.c]=bidx; //b가 칸 차지
            map[a.r][a.c][0] = gun[aidx-1]; //a총기 내려놓기
            gun[aidx-1] = 0;
            getGun(bidx);
            moveL(aidx);//a움직이기
        }

//        System.out.println("fight:"+aidx+":"+a.s+" "+bidx+":"+b.s+" "+Arrays.toString(gun));
//        for(int i=0;i<n;i++){
//            System.out.println(Arrays.toString(visit[i]));
//        }
//        System.out.println();

    }

    static void moveL(int idx){
        //앞으로 이동
        Player player = list.get(idx);
        int dir = player.d;
        int rr = player.r+dr[dir];
        int cc = player.c+dc[dir];
        if(rr>=0 && rr<n && cc>=0 && cc<n && visit[rr][cc]==0){
            player.r=rr;
            player.c=cc;
            //list 다시 넣어주기
            list.remove(idx);
            list.add(idx, player);
            visit[rr][cc]=idx;
            getGun(idx);
        }else{
            //벗어나거나 사람 있을 시 90도 회전
            if(dir==0){
                dir=1;
            }else if(dir==1){
                dir=2;
            }else if(dir==2){
                dir=3;
            }else if(dir==3){
                dir=0;
            }
            player.d=dir;
            //list 다시 넣어주기
            list.remove(idx);
            list.add(idx, player);
            moveL(idx);
        }


    }

}
