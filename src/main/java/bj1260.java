import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class bj1260 {
    static int N;
    static int M;
    static int V;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean dfsvisit[];
    static boolean bfsvisit[];
    static ArrayList<Integer> dfsresult;
    static ArrayList<Integer> bfsresult;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        dfsvisit = new boolean[N+1];
        bfsvisit = new boolean[N+1];

        for(int i=0 ; i <= N; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            graph.add(list);
        }

        for(int i=0; i < M; i++) {
            StringTokenizer temp = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(temp.nextToken());
            int v2 = Integer.parseInt(temp.nextToken());
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }

        for(int i=0; i <= N; i++){
            Collections.sort(graph.get(i));
        }


        dfsresult = new ArrayList<>();
        bfsresult = new ArrayList<>();
        DFS(V);
        BFS(V);

        for(Integer i : dfsresult) System.out.print(i+" ");
        System.out.println();
        for(Integer i : bfsresult) System.out.print(i+" ");
    }

    private static void DFS(int node) {
        dfsvisit[node] = true;
        // 방문 노드 저장
        dfsresult.add(node);

        // 인접 노드 탐색
        for (Integer i : graph.get(node)){
            if (dfsvisit[i]==false){
                DFS(i);
            }
        }
    }

    private static void BFS(int node){
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        bfsvisit[node] = true;

        while(!q.isEmpty()){
            int value = q.poll();
            bfsresult.add(value);

            for(int e : graph.get(value)){
                if(!bfsvisit[e]){
                    bfsvisit[e] = true;
                    q.offer(e);
                }
            }
        }

    }

}
