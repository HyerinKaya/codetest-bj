package bj;
import java.util.*;

class pg_distancing {

    static String[][] place;
    static int visit[][], answer[];
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    TreeMap


    public int[] solution(String[][] places) {

        answer = new int[5];
        for(int i=0;i<5;i++){
            answer[i] = 1;
        }

        for(int room = 0;room<5;room++){
            place = new String[5][5];
            for(int i=0;i<5;i++){
                place[i] = places[room][i].split("");
            }
            for(int i=0;i<5;i++){
                for(int j=0;j<5;j++){
                    if(place[i][j].equals("P")){
                        Spot start = new Spot(i,j);
                        bfs(start, room);
                        if(answer[room]==0) break;
                    }
                }
                if(answer[room]==0) break;
            }

        }

        return answer;
    }

    static void bfs(Spot start, int answerIdx){
        Queue<Spot> q = new LinkedList<>();
        q.add(start);
        visit = new int[5][5];
        visit[start.r][start.c] = 1;
        while(!q.isEmpty()){
            Spot s = q.remove();
            if(visit[s.r][s.c]>=3) break;
            for(int i=0;i<4;i++){
                int rr = s.r+dr[i];
                int cc = s.c+dc[i];
                if(inRange(rr,cc)&&visit[rr][cc]==0&&!place[rr][cc].equals("X")){
                    if(place[rr][cc].equals("P")){
                        //System.out.println("s:"+s.r+" "+s.c+", mv:"+rr+" "+cc);
                        answer[answerIdx] = 0;
                        return;
                    }else{
                        visit[rr][cc] = visit[s.r][s.c]+1;
                        Spot mv = new Spot(rr, cc);
                        q.add(mv);
                    }
                }
            }
        }
    }

    static class Spot{
        int r, c;
        Spot(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    static boolean inRange(int r, int c){
        return(r>=0&&r<5&&c>=0&&c<5);
    }
}