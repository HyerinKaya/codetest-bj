package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class bj16173 {

    static int[][] map;
    static int N;
    static String answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        answer = "Hing";
        for(int i=0; i<N;i++){
            String str = br.readLine();
            map[i] = Stream.of(str.split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int move = 0;

        findMap(0, 0);

        System.out.println(answer);

    }

    private static void findMap(int r, int c){

        if(r>=N || c>=N){
            return;
        }else if( map[r][c]==-1){
            answer = "HaruHaru";
            return;
        }else if( map[r][c]==0){
            return;
        }
        int move = map[r][c];

        if(move>0){
            findMap(r+move, c);
            findMap(r, c+move);
        }else if(move==0){
            findMap(c+move, r);
            findMap(c, r+move);
        }
    }
}
