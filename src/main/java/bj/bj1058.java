package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj1058 {
    static int N;
    static int answer = 0;
    static ArrayList<ArrayList<Integer>> map;
    static ArrayList<ArrayList<Integer>> map2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        map = new ArrayList<ArrayList<Integer>>();
        map2 = new ArrayList<ArrayList<Integer>>();


        for(int i=0; i<N; i++){
            map.add(new ArrayList<Integer>());
            map2.add(new ArrayList<Integer>());
            char[] arr = br.readLine().toCharArray();
            for(int j=0; j<N;j++){
                String C = String.valueOf(arr[j]);
                if(C.equals("Y")){
                    map.get(i).add(j);
                }
            }
        }


        makeFriendList();
        findMaxFriend();

        System.out.println(answer);
    }

    static void makeFriendList(){
        for(int i=0;i<N;i++){
            for(int j : map.get(i)){
                for(int p : map.get(j)){
                    if(p!=i && !map.get(i).contains(p) && !map2.get(p).contains(i)){
                        map2.get(i).add(p);
                        map2.get(p).add(i);
                    }
                }
            }
        }
    }

    static void findMaxFriend(){
        for(int i=0; i<N; i++){
            int sum = map.get(i).size() + map2.get(i).size();
            if(sum>answer) answer=sum;
        }
    }
}
