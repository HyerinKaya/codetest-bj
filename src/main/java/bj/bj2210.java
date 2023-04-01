package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public class bj2210 {

    static int[][] map = new int[5][5];
    static Map<String, Integer> resultMap = new HashMap<>();
    static int answer =0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<5;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<5;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<5;i++){
            for(int j=0; j<5; j++){
                dfs(i, j, "");
            }
        }

        Iterator<String> keys = resultMap.keySet().iterator();
        while( keys.hasNext() ){
            String key = keys.next();
            answer++;
        }

        System.out.println(answer);


    }

    static void dfs(int x, int y, String result){
        StringBuilder sb = new StringBuilder(result);
        if(result.length()>5){
            resultMap.put(result, 0);
            return;
        }
        sb.append(String.valueOf(map[x][y]));
        result = sb.toString();
        if(x>0){
            dfs(x-1, y, result);
        }
        if(y>0){
            dfs(x, y-1, result);
        }
        if(x<4){
            dfs(x+1, y, result);
        }
        if(y<4){
            dfs(x, y + 1, result);
        }
    }
}
