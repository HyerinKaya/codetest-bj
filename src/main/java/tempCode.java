import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class tempCode{

    static int[][] route;
    static int answer =0;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        route = new int[N][2];

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            route[i][0] = Integer.parseInt(st.nextToken());
            route[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(route));
    }

    static int solution(int[][] routes) {
        N = routes.length;
        route = new int[N][2];
        for(int i=0; i<N;i++){
            route[i] = routes[i].clone();
        }
        setCamera();

        return answer;
    }

    static class Pair implements Comparable<Pair>{
        int start;
        int end;

        Pair(int s, int e){
            this.start = s;
            this.end = e;
        }

        //start가 작으면 앞으로, 같으면 end가 작은게 앞으로
        public int compareTo(Pair o){
            if (this.start>o.start){
                return 1;
            } else if(this.start==o.start){
                return this.end - o.end;
            } else{
                return -1;
            }
        }
    }

    static void setCamera(){
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int start =0;
        int end =0;
        for(int i=0; i<N;i++){
            start = route[i][0];
            end = route[i][1];
            pq.add(new Pair(start, end));
        }

        int location = -30000;
        while(!pq.isEmpty()){
            //카메라 위치가 Pair의 start보다 작으면 end로 갱신
            Pair p = pq.poll();
            if(location<p.start){
                location = p.end;
                answer+=1;
            }
        }
    }
}


