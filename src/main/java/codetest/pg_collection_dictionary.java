package codetest;

import java.util.*;

public class pg_collection_dictionary {

    static PriorityQueue<String> pq = new PriorityQueue<>();
    static HashMap<String, Integer> map = new HashMap();
    static String[] arr;

    public int solution(String word) {
        int answer = 0;
        arr = new String[]{"A", "E", "I", "O", "U"};

        arrange(0, "");
        int num = 1;
        while(!pq.isEmpty()){
            String str = pq.poll();
            map.put(str, num);
            num+=1;
        }

        answer = map.get(word);

        return answer;
    }

    static void arrange(int cnt, String word){
        if(cnt>4){
            return;
        }
        for(int i=0;i<arr.length;i++){
            StringBuilder sb = new StringBuilder();
            sb.append(word);
            sb.append(arr[i]);
            String str = sb.toString();
            pq.add(str);
            arrange(cnt+1, str);
        }
    }

}
