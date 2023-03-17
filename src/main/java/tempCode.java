import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
if(history.equals("3")){
            System.out.println("history: "+history);
            for(int i=0; i<N;i++){
                for(int j=0; j<N;j++){
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
        }
 */

public class tempCode {
    static int N;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];

        for(int i=0; i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] copy = new int[N][N];
        copy = arr.clone();
        for(int p=0; p<N;p++){
            for(int q=0; q<N;q++){
                System.out.print(copy[p][q]);
            }
            System.out.println();
        }

        for(int i = 0; i < N; i++)
            copy[i] = arr[i].clone();

        for(int p=0; p<N;p++){
            for(int q=0; q<N;q++){
                System.out.print(copy[p][q]);
            }
            System.out.println();
        }
        //game(arr, 1, "");
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

    public static void game(int[][] map, int count, String history){
        if(count==0){
            findMax(map);
            return;
        }
        for(int i=1; i<5;i++){
            int[][] nextMap = move(map, i);
            System.out.println("i:"+i);
            for(int p=0; p<N;p++){
                for(int q=0; q<N;q++){
                    System.out.print(nextMap[p][q]);
                }
                System.out.println();
            }

            StringBuilder sb = new StringBuilder(history);
            sb.append(String.valueOf(i));
            String newHistory = sb.toString();
            game(nextMap, count-1, newHistory);
        }
    }

    public static int[][] move(int[][] map, int direction){
        int[][] fakeMap = new int[N][N];
        //direction: 1>, 2<, 3v, 4^
        switch (direction){
            case 1 -> {
                for (int i = 0; i < N; i++) {
                    Stack<Integer> stack = new Stack<>();
                    boolean flag = false;
                    for (int j = 0; j < N; j++) {
                        stack.push(map[i][j]);
                    }
                    int count = N - 1;
                    while (!stack.isEmpty()) {
                        int num = stack.peek();
                        if (num == 0) {
                            stack.pop();
                        } else if (count == N - 1) {
                            map[i][count] = stack.pop();
                            count--;
                        } else if (num == map[i][count + 1] && flag == false) {
                            map[i][count + 1] = stack.pop() * 2;
                            flag = true;
                        } else {
                            map[i][count] = stack.pop();
                            count--;
                            flag = false;
                        }
                    }
                    for (int j = 0; j <= count; j++) {
                        map[i][j] = 0;
                    }
                }
                break;
            }
            case 2 -> {
                for (int i = 0; i < N; i++) {
                    Stack<Integer> stack = new Stack<>();
                    boolean flag = false;
                    for (int j = N - 1; j >= 0; j--) {
                        stack.push(map[i][j]);
                    }
                    int count = 0;
                    while (!stack.isEmpty()) {
                        int num = stack.peek();
                        if (num == 0) {
                            stack.pop();
                        } else if (count == 0) {
                            map[i][count] = stack.pop();
                            count++;
                        } else if (num == map[i][count - 1] && flag == false) {
                            map[i][count - 1] = stack.pop() * 2;
                            flag = true;
                        } else {
                            map[i][count] = stack.pop();
                            count++;
                            flag = false;
                        }
                    }
                    for (int j = N - 1; j >= count; j--) {
                        map[i][j] = 0;
                    }
                }
                break;
            }
            case 3 -> {
                for (int i = 0; i < N; i++) {
                    Stack<Integer> stack = new Stack<>();
                    boolean flag = false;
                    for (int j = 0; j < N; j++) {
                        stack.push(map[j][i]);
                    }
                    int count = N - 1;
                    while (!stack.isEmpty()) {
                        int num = stack.peek();
                        if (num == 0) {
                            stack.pop();
                        } else if (count == N - 1) {
                            map[count][i] = stack.pop();
                            count--;
                        } else if (num == map[count + 1][i] && flag == false) {
                            map[count + 1][i] = stack.pop() * 2;
                            flag = true;
                        } else {
                            map[count][i] = stack.pop();
                            count--;
                            flag = false;
                        }

                    }
                    for (int j = 0; j <= count; j++) {
                        map[j][i] = 0;
                    }
                }
                break;
            }
            case 4 -> {
                for (int i = 0; i < N; i++) {
                    Stack<Integer> stack = new Stack<>();
                    boolean flag = false;
                    for (int j = N - 1; j >= 0; j--) {
                        stack.push(map[j][i]);
                    }
                    int count = 0;
                    while (!stack.isEmpty()) {
                        int num = stack.peek();
                        if (num == 0) {
                            stack.pop();
                        } else if (count == 0) {
                            map[count][i] = stack.pop();
                            count++;
                        } else if (num == map[count - 1][i] && flag == false) {
                            map[count - 1][i] = stack.pop() * 2;
                            flag = true;
                        } else {
                            map[count][i] = stack.pop();
                            count++;
                            flag = false;
                        }
                    }
                    for (int j = N - 1; j >= count; j--) {
                        map[j][i] = 0;
                    }
                }
                break;
            }
            default -> {return fakeMap;}
        }
        return map;
    }
}
