package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj16236 {
    static int N, size = 2;
    static int map[][], time[][];
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        Spot shark = new Spot(-1, -1);

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    shark.r = i;
                    shark.c = j;
                    map[i][j] = 0;
                }
            }
        }
        int totT = 0, eat = 0;
        Spot fish;

        while(true) {
            fish = bfs(shark); // 문제의 조건을 만족하는 먹이를 찾아 해당 위치를 변수에 저장
            if(fish.r != Integer.MAX_VALUE || fish.c != Integer.MAX_VALUE) { // 먹이를 찾은 경우
                eat+=1;
                map[fish.r][fish.c] = 0;
                shark = fish;
                totT += time[fish.r][fish.c]; // 해당 먹이를 먹는데 이동한 시간을 누적 시간에 더함
                if(eat == size) { // 상어 크기 +1
                    size++;
                    eat = 0;
                }
            }
            else // 먹이 탐색이 끝나도 먹이위치 반환 없을 시 종료
                break;
        }
        System.out.println(totT); // 먹을 수 있는 먹이를 다 먹은 후 최종 누적 시간 출력
    }
    static class Spot {
        int r;
        int c;
        Spot(int row, int col) {
            this.r = row;
            this.c = col;
        }
    }
    static Spot bfs(Spot shark) {
        Queue<Spot> queue = new LinkedList<>();
        queue.add(shark); // 상어의 위치를 시작점으로
        Spot s, mv, ans = new Spot(Integer.MAX_VALUE, Integer.MAX_VALUE);
        time = new int[N][N]; // 해당 위치까지의 시간 저장
        int minT = Integer.MAX_VALUE; //최소시간
        while(!queue.isEmpty()) {
            s = queue.remove();
            for(int i=0; i<4; i++) {
                mv = new Spot(s.r+dr[i], s.c+dc[i]);

                if(isArea(mv) && canMove(mv)) { //이동 가능한 위치면 이동
                    time[mv.r][mv.c] = time[s.r][s.c] + 1; // 해당 위치까지 걸린 시간 저장
                    if(time[mv.r][mv.c] > minT)
                        // 지금까지 먹이를 먹을 수 있는 최소 시간 넘어가면 해당 길은 탐색 X
                        continue;
                    if(map[mv.r][mv.c] != 0 && map[mv.r][mv.c] < size) { // 해당 위치에 있는 물고기를 상어가 먹을 수 있는 경우
                        if(minT > time[mv.r][mv.c]) { // 이전에 찾았던 시간보다 더 빠른 시간에 먹이를 찾은 경우
                            minT = time[mv.r][mv.c];
                            ans = new Spot(mv.r, mv.c);
                        }
                        else if((ans.r == mv.r && ans.c > mv.c) || ans.r > mv.r) // 시간 같을 때 우선 순위에 있는 먹이
                            ans = new Spot(mv.r, mv.c);
                    }
                    queue.add(mv); // 계속 경로 탐색하기 위해 이동이 가능했던 곳은 다음 방문 예정 루트로 저장
                }
            }
        }
        return ans; // 문제의 조건에 부합하는 우선순위가 가장 높은 먹이의 위치 반환(만약 먹이를 찾지 못하면 Integer.MAX_VALUE값 반환)
    }

    // 해당 위치가 map에 존재하는 위치인지 판단
    static boolean isArea(Spot s) {
        return s.r>=0 && s.r<N && s.c>=0 && s.c<N;
    }

    // 해당 위치가 상어가 이동할 수 있는 곳인지 판단
    static boolean canMove(Spot s) {
        return map[s.r][s.c] <= size && time[s.r][s.c] == 0;
    }
}