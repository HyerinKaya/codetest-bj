package codetest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class midasit_basic {
    static String[][] ADInfos = {{"A", "5", "3"}, {"B", "3", "5"}, {"C", "2", "6"}};
    static String[][] logs = {{"V", "A"}, {"C", "A"}, {"V", "B"},{"C", "B"}, {"V", "C"}, {"C", "C"}, };

    public static void main(String[] args) throws IOException {
        String answer = "";
        int adN = ADInfos.length;
        int lN = logs.length;
        HashMap<String, Integer> price = new HashMap<>();

        for(int i=0;i<lN;i++){
            String action = logs[i][0];
            String ad = logs[i][1];
            for(int j=0;j<adN;j++){
                if(ADInfos[j][0].equals(ad)){
                    if(price.containsKey(ad)){
                        Integer v = action.equals("V") ?
                                price.put(ad, price.get(ad) + Integer.parseInt(ADInfos[j][1]))
                                : price.put(ad, price.get(ad) + Integer.parseInt(ADInfos[j][2]));
                    }else{
                        Integer v = action.equals("V") ?
                                price.put(ad, Integer.parseInt(ADInfos[j][1]))
                                : price.put(ad, Integer.parseInt(ADInfos[j][2]));
                    }
                    break;
                }
            }
        }
        int max=0;

        Iterator<String> keys = price.keySet().iterator();
        while(keys.hasNext()){
            String key = keys.next();
            if(price.get(key)>max){
                max = price.get(key);
                answer = key;
            }else if(price.get(key)==max&&!answer.equals("")){
                StringBuilder sb = new StringBuilder(answer);
                sb.append(key);
                answer = sb.toString();
            }
        }

        if(answer.length()>1){
            System.out.println("-1");
        }else {
            System.out.println(answer);
        }
    }
}
