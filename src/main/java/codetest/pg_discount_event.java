package codetest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class pg_discount_event {

    static HashMap<String, Integer> wmap;

    public static void main(String[] args) {
        String[] want = {"banana", "apple", "rice", "pork", "pot"};
        int[] number = {3, 2, 2, 2, 1};
        String[] discount = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};
        System.out.println("answer:"+solution(want, number, discount));
    }

    static int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        wmap = new HashMap<>();
        for(int i=0;i<want.length;i++){
            wmap.put(want[i], number[i]);
        }

        ArrayList<String> dclist = new ArrayList<>();
        for(int i=0;i<10;i++){
            dclist.add(discount[i]);
        }
        int idx = 9;
        String obj = "";

        while(idx<discount.length){
            HashMap<String, Integer> temp = new HashMap<>();
            for(int i=0;i<10;i++){
                obj = dclist.get(i);
                if(temp.containsKey(obj)){
                    temp.put(obj, temp.get(obj)+1);
                }else{
                    temp.put(obj, 1);
                }
            }

            temp.forEach((K,V)-> System.out.print("("+K+","+V+")"));
            System.out.println();
            if(checkComplete(temp)){
                System.out.println(idx-9);
                answer+=1;
            }

            idx+=1;
            if(idx<discount.length){
                dclist.remove(0);
                dclist.add(discount[idx]);
            }
        }



        return answer;
    }

    static boolean checkComplete(HashMap<String, Integer> temp){
        AtomicBoolean result = new AtomicBoolean(true);

        wmap.forEach((K,V)->{
            if(temp.containsKey(K)){
                if(V!=temp.get(K)){
                    result.set(false);
                }
            }else{
                result.set(false);
            }
        });

        return result.get();
    }
}
