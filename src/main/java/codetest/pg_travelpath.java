package codetest;

import java.util.*;

public class pg_travelpath {

    static int N;
    static boolean[] visit;
    static String tMap[][];
    static ArrayList<String> list = new ArrayList<>();

    public static void main(String args[]) {

        //String[][] tickets = {{"ICN", "JFK"}, {"ICN", "AAD"}, {"JFK", "ICN"}};
        String[][] tickets = {{"ICN", "BOO"}, {"ICN", "COO"}, {"COO", "DOO"}, {"DOO", "COO"}, {"BOO", "DOO"}, {"DOO", "BOO"}, {"BOO", "ICN"}, {"COO", "BOO"}};

        N = tickets.length;
        tMap = new String[N][2];
        for(int i=0;i<N;i++) {
            tMap[i] = tickets[i].clone();
        }


        String[] answer = new String[N+1];


        bfs();
        String ans = "ZZZ";
        for(String s:list){
            if(s.length()>=4*(N+1)){
                if(ans.length()<1){
                    ans = s;
                }else if(s.compareTo(ans)<0){
                    ans = s;
                }

            }
        }
        list.forEach(K->System.out.println(K));

        answer = ans.split(" ");
        System.out.println(Arrays.toString(answer));
        return;
    }

    static void bfs(){
        Queue<Ticket> q = new LinkedList<>();
        for(int i=0;i<N;i++){
            if(tMap[i][0].equals("ICN")){
                Ticket t = new Ticket(tMap[i][0], tMap[i][1], i);
                StringBuilder sb = new StringBuilder();
                sb.append(t.depart+" "+t.arrive+" ");
                String str = sb.toString();
                list.add(str);
                q.add(t);
            }
        }

        while(!q.isEmpty()){
            Ticket s = q.remove();
            boolean[] visit = new boolean[N];
            HashMap<String, Integer> map = new HashMap<>();
            for(int i=0;i<N;i++){
                String t = tMap[i][0]+" "+tMap[i][1];
                if(list.get(s.idx).contains(t)){
                    if(map.containsKey(t)) continue;
                    map.put(t, 1);
                    visit[i] = true;
                }
            }

            for(int i=0;i<N;i++){
                if(tMap[i][0].equals(s.arrive)&&visit[i]==false){
                    Ticket move = new Ticket(tMap[i][0], tMap[i][1], i);
                    StringBuilder sb = new StringBuilder();
                    sb.append(list.get(s.idx)+move.arrive+" ");
                    String str = sb.toString();
                    if(str.length()>4*(N+1)) continue;
                    list.add(str);
                    move.idx = list.indexOf(str);
                    q.add(move);
                }
            }
        }

    }

    static class Ticket{
        String depart,arrive;
        int idx;
        Ticket(String depart, String arrive, int idx){
            this.depart = depart;
            this.arrive = arrive;
            this.idx = idx;
        }
    }
}
