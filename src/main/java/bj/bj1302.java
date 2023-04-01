package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class bj1302 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        HashMap<String, Integer> map = new HashMap<>();
        String title = " ";
        for(int i=0;i<N;i++){
            title = sc.next();
            if(map.containsKey(title)){
                map.put(title, map.get(title)+1);
            }else{
                map.put(title, 1);
            }
        }

        int max =0;
        String max_title = "";
        Iterator<String> keys = map.keySet().iterator();
        while( keys.hasNext() ) {
            String key = keys.next();
            if (max < map.get(key)) {
                max = map.get(key);
                max_title = key;
            }else if(max == map.get(key)) {
                if(max_title.compareTo(key)>0){
                    max_title = key;
                }
            }
        }

        System.out.println(max_title);
    }
}
