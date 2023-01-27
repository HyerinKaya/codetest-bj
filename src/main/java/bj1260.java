import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class bj1260 {
    static int N;
    static int M;
    static int V;
    static ArrayList<LinkedList<Integer>> graph = new ArrayList<>();
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
            LinkedList<Integer> list = new LinkedList<>();
            graph.add(list);
        }

        for(int i=0; i < M; i++) {
            StringTokenizer temp = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(temp.nextToken());
            int v2 = Integer.parseInt(temp.nextToken());
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }


        dfsresult = new ArrayList<>();
        bfsresult = new ArrayList<>();
        DFS(V);
        bfsresult.add(V);
        BFS(V);

        for(Integer i : dfsresult) System.out.print(i);
        System.out.println();
        for(Integer i : bfsresult) System.out.print(i);
    }

    private static void DFS(int node) {
        dfsvisit[node] = true;
        // 방문 노드 저장
        dfsresult.add(V);

        // 인접 노드 탐색
        for (int i : graph.get(node)){
            if (dfsvisit[i]==false){
                DFS(i);
            }
        }
    }

    private static void BFS(int node){
        bfsvisit[node] = true;
        // 인접 노드 탐색
        for (int i : graph.get(node)){
            bfsvisit[i] = true;
            // 방문 노드 저장
            bfsresult.add(i);
        }
        for(int i : graph.get(node)){
            if(bfsvisit[i]==false){
                BFS(i);
            }
        }
    }
}
