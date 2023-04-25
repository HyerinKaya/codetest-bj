package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class bj1068 {

    static int N, M;
    static ArrayList<Integer>[] listArr;

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        listArr = new ArrayList[N];
        for(int i=0;i<N;i++){
            ArrayList<Integer> list = new ArrayList<>();
            listArr[i] = list;
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cnt = 0;
        for(int i=0;i<N;i++) {
            int node = Integer.parseInt(st.nextToken());
            if (node != -1) {
                listArr[node].add(cnt);
            }
            cnt += 1;
        }
        M = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            if(listArr[i].contains(M)){
                listArr[i].remove(listArr[i].indexOf(M));
            }
        }

//        System.out.println();
//        for(int i=0;i<N;i++){
//            System.out.print(i+":");
//            listArr[i].forEach(v-> System.out.print(" "+v));
//            System.out.println();
//        }

        Queue<Integer> q = new LinkedList<>();
        q.add(M);
        HashMap<Integer, Integer> map = new HashMap<>();

        while(!q.isEmpty()){
            int start = q.remove();
            map.put(start, 0);
            listArr[start].forEach(V-> q.add(V));
            listArr[start].clear();
        }

        int answer = 0;
        for(int i=0;i<N;i++){
            if(listArr[i].isEmpty()&&!map.containsKey(i)){
                answer+=1;
            }
        }
//        System.out.println("N:"+N+", M:"+M);
        System.out.println(answer);
    }

}
