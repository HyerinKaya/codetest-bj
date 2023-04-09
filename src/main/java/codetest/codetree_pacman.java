package codetest;

import java.io.*;
import java.util.*;

public class codetree_pacman {

    static int m, t, turn, eatMax, corpseMap[][], pmPath[], dfsPath[];
    static Spot pacman;
    static ArrayList<Spot> eggList, monsterList;
    static ArrayList<Corpse> corpseList;
    static int[] dr = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 0, -1, -1, -1, 0, 1, 1 ,1};
    static int[] pr = {-1, 0, 1, 0};
    static int[] pc = {0, -1, 0, 1};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        eggList = new ArrayList<Spot>();
        corpseList = new ArrayList<Corpse>();
        monsterList = new ArrayList<Spot>();
        corpseMap = new int[4][4];
        pmPath = new int[3];
        dfsPath = new int[3];
        turn =0;


        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken())-1;
        int c = Integer.parseInt(st.nextToken())-1;
        pacman = new Spot(r, c, 0);

        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken())-1;
            c = Integer.parseInt(st.nextToken())-1;
            int d = Integer.parseInt(st.nextToken());
            Spot monster = new Spot(r, c, d);
            monsterList.add(monster);
        }

        while(turn<t) {
            System.out.println();
            System.out.println();
            System.out.println("------------------turn"+turn);


            copyMonster();


            System.out.print("egg:");
            for(int i=0;i<eggList.size();i++) {
                System.out.print(" ("+eggList.get(i).r+","+eggList.get(i).c+")");
            }
            System.out.println();


            monsterMove();
            System.out.print("monsterMove:");
            for(int i=0;i<monsterList.size();i++) {
                System.out.print(" ("+monsterList.get(i).r+","+monsterList.get(i).c+")");
            }
            System.out.print("pacman ("+pacman.r+","+pacman.c+")");
            System.out.println();

            pacmanMove();
            System.out.print("->pacman move:");
            System.out.print(" ("+pacman.r+","+pacman.c+")");
            System.out.println();

            cleanCorpse();
            System.out.print("corpse:");
            for(int i=0;i<corpseList.size();i++) {
                System.out.print(" ("+corpseList.get(i).r+","+corpseList.get(i).c+")");
            }
            System.out.println();

            hatchMonster();
            System.out.print("hatch:");
            for(int i=0;i<monsterList.size();i++) {
                System.out.print(" ("+monsterList.get(i).r+","+monsterList.get(i).c+")");
            }
            System.out.println();
            System.out.println();

            turn+=1;
        }

        System.out.println(monsterList.size());


    }

    static void copyMonster() { //monster copy egg
        eggList.clear();
        for(int i=0;i<monsterList.size();i++) {
            int r = monsterList.get(i).r;
            int c = monsterList.get(i).c;
            int d = monsterList.get(i).d;
            Spot monster = new Spot(r, c, d);

            eggList.add(monster);
        }
    }

    static void monsterMove() { //monster move 1
        for(int i=0;i<monsterList.size();i++) {
            Spot spot = monsterList.get(i);

            int rr = spot.r+dr[spot.d];
            int cc = spot.c+dc[spot.d];
            if(inRange(rr, cc)&& corpseMap[rr][cc]==0&&!isPacman(rr,cc)) { //inRange, no corpse, no pm > move 1
                spot.r = rr;
                spot.c = cc;
                monsterList.remove(i);
                monsterList.add(i, spot);
            }else {
                int dir = spot.d+1;
                for(int j=1;j<9;j++) {  //rotate anti-clockwise
                    if(dir==9) {
                        dir = 1;
                    }
                    rr = spot.r+dr[dir];
                    cc = spot.c+dc[dir];
                    if(inRange(rr,cc)&&corpseMap[rr][cc]==0&&!isPacman(rr,cc)) {
                        spot.r = rr;
                        spot.c = cc;
                        monsterList.remove(i);
                        monsterList.add(i, spot);
                        break;
                    }
                    dir+=1;
                }
            }

        }
    }

    static void pacmanMove() {
        eatMax=0;


        ArrayList<Spot> list = (ArrayList<Spot>) monsterList.clone();

        eatDfs(pacman.r, pacman.c, 0, 0, list);
        System.out.println("pmPath:"+Arrays.toString(pmPath));
        for(int i=0;i<3;i++) { //eat by path

            System.out.print("monsterList:");
            for(int j=0;j<monsterList.size();j++) {
                System.out.print(" ("+monsterList.get(j).r+","+monsterList.get(j).c+")");
            }
            System.out.println();

            int rr = pacman.r+pr[pmPath[i]];
            int cc = pacman.c+pc[pmPath[i]];

            System.out.println("pacman("+rr+","+cc+")");

            Stack<Spot> stack = new Stack<>(); //save live monster&reset mosnterlist
            for(int j=0;j<monsterList.size();j++) {
                int r = monsterList.get(j).r;
                int c = monsterList.get(j).c;
                int d = monsterList.get(j).d;
                Spot monster = new Spot(r, c, d);
                if(monster.r==rr&&monster.c==cc) { //eat monster in path and add to corpseList
                    Corpse corpse = new Corpse(rr, cc, turn);
                    corpseMap[rr][cc]=1;
                    corpseList.add(corpse);
                }else {
                    stack.push(monster); //keep monster not in path
                }
            }
            monsterList.clear();
            System.out.println();
            while(!stack.isEmpty()) {
                monsterList.add(stack.pop());

            }
            pacman.r = rr;
            pacman.c = cc;
        }

    }

    static void eatDfs(int r, int c, int eatCnt, int idx, ArrayList<Spot> list) {
        if(idx>=3) {
            if(eatMax<eatCnt) {
                System.out.println("/ dfs:"+Arrays.toString(dfsPath)+" eatCnt:"+eatCnt);
                eatMax = eatCnt;
                pmPath = dfsPath.clone();
            }
            return;
        }
        for(int i=0;i<4;i++) {
            ArrayList<Spot> original = (ArrayList<Spot>) list.clone();
            Stack<Spot> stack = new Stack<>();
            int rr = r+pr[i]; //^ < v >
            int cc = c+pc[i];
            int num = eatCnt;
            if(inRange(rr, cc)) {
                for(int j=0;j<list.size();j++) {
                    int mr = list.get(j).r;
                    int mc = list.get(j).c;
                    int md = list.get(j).d;
                    Spot monster = new Spot(mr, mc, md);
                    if(monster.r==rr&&monster.c==cc) {
                        num+=1;
                        continue;
                    }
                    stack.push(monster);
                }
                dfsPath[idx] = i;
                list.clear();
                while(!stack.isEmpty()) {
                    list.add(stack.pop());
                }

                eatDfs(rr, cc, num, idx+1, list);
                list = (ArrayList<Spot>) original.clone();
            }
        }

    }

    static void cleanCorpse() {
        Stack<Corpse> stack = new Stack<>();//save live corpse&reset corpselist
        for(int i=0;i<corpseList.size();i++) {
            int r = corpseList.get(i).r;
            int c = corpseList.get(i).c;
            int t = corpseList.get(i).t;
            Corpse corpse = new Corpse(r, c, t);
            corpseMap[corpse.r][corpse.c]=0;
            if(turn-corpse.t<2) stack.push(corpse);
        }
        corpseList.clear();
        while(!stack.isEmpty()) {
            corpseList.add(stack.pop());

        }
    }

    static void hatchMonster() {
        for(int i=0;i<eggList.size();i++) {
            Spot egg = eggList.get(i);
            monsterList.add(egg);
        }
    }

    static class Spot{
        int r, c, d;
        Spot(int r, int c, int dir){
            this.r = r;
            this.c = c;
            this.d = dir;
        }
    }

    static class Corpse{
        int r, c, t;
        Corpse(int r, int c, int turn){
            this.r = r;
            this.c = c;
            this.t = turn;
        }
    }

    static boolean inRange(int r, int c) {
        return(r>=0&&r<4&&c>=0&&c<4);
    }

    static boolean isPacman(int r, int c) {
        return(r==pacman.r&&c==pacman.c);
    }

}
