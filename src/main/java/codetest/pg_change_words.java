package codetest;

import java.util.*;

public class pg_change_words {

        static int N, M, tIdx, min,len[];
        static String wordArr[];

        public int main(String begin, String target, String[] words) {

            int answer = 0;
            N = begin.length(); //word length
            M = words.length+1; //words array length
            min = Integer.MAX_VALUE;
            len = new int[M+1];
            tIdx = 0;
            wordArr = new String[M+1];
            wordArr[0] = begin;

            for(int i=1;i<M;i++){
                wordArr[i] = words[i-1];
                if(words[i-1].equals(target)){
                    tIdx = i;
                }
            }

            if(tIdx==0) return 0;

            bfs();
            if(len[tIdx]==0){
                return 0;
            }else{
                answer = len[tIdx];
            }

            return answer;
        }

        static void bfs(){
            Queue<Integer> q = new LinkedList<>();
            q.add(0);
            while(!q.isEmpty()){
                int s = q.remove();
                for(int i=1;i<M;i++){
                    if(len[i]==0){
                        if(isChangeable(wordArr[s], wordArr[i])){
                            len[i] = len[s]+1;
                            if(i==tIdx) continue;
                            q.add(i);
                        }
                    }
                }
            }


        }

        static boolean isChangeable(String begin, String target){
            String[] arrb = begin.split("");
            String[] arrt = target.split("");
            int count = 0;
            for(int i=0;i<N;i++){
                if(arrb[i].equals(arrt[i])) count+=1;
            }
            return(count==N-1);
        }
    }
}
