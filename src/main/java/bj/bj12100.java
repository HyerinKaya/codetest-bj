package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


public class bj12100 {
    static int N;
    static int answer;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for(int i=0; i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        game(arr, 0);
        System.out.println(answer);
    }

    public static void findMax(int[][] map){
        int max=0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N;j++){
                if(map[i][j]>max) max = map[i][j];
            }
        }
        if(max>answer){
            answer=max;
        }
        return;
    }

    public static void game(int[][] map, int count){
        if(count==5){
            findMax(map);
            return;
        }

        int copy[][] = new int[N][N];
        copy = map.clone();
        for(int i = 0; i < N; i++)
            copy[i] = map[i].clone();

        for(int i=1; i<5;i++){
            int[][] newMap = move(map,i);
            game(newMap, count+1);
           for(int a = 0; a < N; a++)
                map[a] = copy[a].clone();
        }

    }

    public static int[][] move(int[][] map, int direction){
        int[][] copy = map.clone();
        //direction: 1>, 2<, 3v, 4^
        if(direction==1){
                for (int i = 0; i < N; i++) {
                    Stack<Integer> stack = new Stack<>();
                    boolean flag = false;
                    for (int j = 0; j < N; j++) {
                        stack.push(copy[i][j]);
                    }
                    int count = N - 1;
                    while (!stack.isEmpty()) {
                        int num = stack.peek();
                        if (num == 0) {
                            stack.pop();
                        } else if (count == N - 1) {
                            copy[i][count] = stack.pop();
                            count--;
                        } else if (num == copy[i][count + 1] && flag == false) {
                            copy[i][count + 1] = stack.pop() * 2;
                            flag = true;
                        } else {
                            copy[i][count] = stack.pop();
                            count--;
                            flag = false;
                        }
                    }
                    for (int j = 0; j <= count; j++) {
                        copy[i][j] = 0;
                    }
                }
            }
        else if(direction==2){
            for (int i = 0; i < N; i++) {
                Stack<Integer> stack = new Stack<>();
                boolean flag = false;
                for (int j = N - 1; j >= 0; j--) {
                    stack.push(copy[i][j]);
                }
                int count = 0;
                while (!stack.isEmpty()) {
                    int num = stack.peek();
                    if (num == 0) {
                        stack.pop();
                    } else if (count == 0) {
                        copy[i][count] = stack.pop();
                        count++;
                    } else if (num == copy[i][count - 1] && flag == false) {
                        copy[i][count - 1] = stack.pop() * 2;
                        flag = true;
                    } else {
                        copy[i][count] = stack.pop();
                        count++;
                        flag = false;
                    }
                }
                for (int j = N - 1; j >= count; j--) {
                    copy[i][j] = 0;
                }
            }
        }
        else if(direction==3) {
            for (int i = 0; i < N; i++) {
                Stack<Integer> stack = new Stack<>();
                boolean flag = false;
                for (int j = 0; j < N; j++) {
                    stack.push(copy[j][i]);
                }
                int count = N - 1;
                while (!stack.isEmpty()) {
                    int num = stack.peek();
                    if (num == 0) {
                        stack.pop();
                    } else if (count == N - 1) {
                        copy[count][i] = stack.pop();
                        count--;
                    } else if (num == copy[count + 1][i] && flag == false) {
                        copy[count + 1][i] = stack.pop() * 2;
                        flag = true;
                    } else {
                        copy[count][i] = stack.pop();
                        count--;
                        flag = false;
                    }

                }
                for (int j = 0; j <= count; j++) {
                    map[j][i] = 0;
                }
            }
        }
        else if(direction==4) {
            for (int i = 0; i < N; i++) {
                Stack<Integer> stack = new Stack<>();
                boolean flag = false;
                for (int j = N - 1; j >= 0; j--) {
                    stack.push(copy[j][i]);
                }
                int count = 0;
                while (!stack.isEmpty()) {
                    int num = stack.peek();
                    if (num == 0) {
                        stack.pop();
                    } else if (count == 0) {
                        copy[count][i] = stack.pop();
                        count++;
                    } else if (num == copy[count - 1][i] && flag == false) {
                        copy[count - 1][i] = stack.pop() * 2;
                        flag = true;
                    } else {
                        copy[count][i] = stack.pop();
                        count++;
                        flag = false;
                    }
                }
                for (int j = N - 1; j >= count; j--) {
                    map[j][i] = 0;
                }
            }
        }
        return copy;
    }
}
