import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1260 {
    static int N;
    static int M;
    static int V;
    static boolean arr[][];
    static boolean dfsvisit[][];
    static boolean bfsvisit[][];
    static int nowX;
    static int nowY;
    static int directionX[] = {0, 0, -1, 1};
    static int directionY[] = {-1, 1, 0, 0};
    static int dfsresult[];
    static int bfsresult[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        arr = new boolean[N][M];
        dfsvisit = new boolean[N][M];
        bfsvisit = new boolean[N][M];

        for(int i=0; i < M; i++) {
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            arr[v1][v2] = true;
            arr[v2][v1] = true;
        }

        dfsresult = new int[N];
        bfsresult = new int[N];


    }

    private static void DFS(int x, int y, int V, int[] result) {
        dfsvisit[x][y] = true;

        while(dfsresult[N-1]<1) {
            nowX = arr[x][y];
            nowY = arr[];

            if(range_check() && !dfsvisit[nowX][nowY] && arr[nowX][nowY] == true) {
                DFS(nowX, nowY, V, result);
            }
        }
    } // End of DFS

    private static boolean range_check() {
        return nowX >= 0 && nowX < N && nowY >= 0 && nowY < M;
    } // End of range_check

    private static void BFS(int x, int y, int V, int[] result){
        bfsvisit[x][y] = true;

        for(){

        }
    }
}
