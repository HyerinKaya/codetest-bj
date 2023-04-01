package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj1713 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> timeCount = new HashMap<>();


        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<K ;i++){
            int candidate = Integer.parseInt(st.nextToken());

            if(map.size()<N){ //map 크기<K
                //map에 있는지 확인 > 있으면 .put(.get)
                if(map.containsKey(candidate)){
                    map.put(candidate, map.get(candidate) + 1);
                }else{
                    map.put(candidate, 1);
                    timeCount.put(candidate, i);
                }
            }else{ //map 크기>=N
                if(map.size()==N&&map.containsKey(candidate)){
                    map.put(candidate, map.get(candidate) + 1);
                    continue;
                }

                //삭제할 거 골라내기
                int min = 0;

                Iterator<Integer> keys = map.keySet().iterator();
                while( keys.hasNext() ){
                    Integer key = keys.next();
                    if(min==0){
                        min=key;
                        continue;
                    }
                    //추천수 적은 거 고르기
                    if(map.get(key)<map.get(min)){
                        min = key;
                    }else if(map.get(key)==map.get(min)){
                        //추천수가 같을 때 오래된 거 고르기
                        if(timeCount.get(key)<timeCount.get(min)){
                            min = key;
                        }
                    }
                }

                map.remove(min);
                timeCount.remove(min);
                map.put(candidate, 1);
                timeCount.put(candidate, i);
            }
        }

        int[] answer = new int[map.size()];
        int count=0;
        Iterator<Integer> nums = map.keySet().iterator();
        while( nums.hasNext() ) {
            Integer num = nums.next();
            answer[count]=num;
            count++;
        }

        Arrays.sort(answer);
        for(int i=0;i<answer.length;i++){
            System.out.print(answer[i]+" ");
        }

    }
}
