package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> hearmap = new HashMap<>();
        HashMap<String, Integer> seemap = new HashMap<>();
        ArrayList<String> answerlist = new ArrayList<>();

        for(int i=0; i<N;i++){
            hearmap.put(br.readLine(), 0);
        }
        for(int i=0; i<M;i++){
            seemap.put(br.readLine(), 0);
        }
        Iterator<String> keys = hearmap.keySet().iterator();
        while( keys.hasNext() ) {
            String key = keys.next();
            if(seemap.containsKey(key)){
                answerlist.add(key);
            }
        }

        Collections.sort(answerlist);
        System.out.println(answerlist.size());
        for(String name : answerlist){
            System.out.println(name);
        }
    }
}
