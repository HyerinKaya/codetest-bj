package codetest;

import java.util.*;

class Solution {

    static PriorityQueue<Target> pq;

    public int solution(int[][] targets) {
        int answer = 0;
        pq = new PriorityQueue<>();

        for(int i=0;i<targets.length;i++){
            Target t = new Target(targets[i][0], targets[i][1]);
            pq.offer(t);
        }


        int end = 0;
        while(!pq.isEmpty()){
            Target s = pq.poll();
            if(end<=s.start){
                end = s.end;
                answer+=1;
            }
            // System.out.println(s.start+" "+s.end+" "+end);
        }
        return answer;
    }

    static class Target implements Comparable<Target>{
        int start, end;
        Target(int start, int end){
            this.start = start;
            this.end = end;
        }

        public int compareTo(Target o){
            if(this.end>o.end){
                return 1;
            }else if(this.end==o.end){
                return this.start - o.start;
            }else{
                return -1;
            }
        }
    }
}