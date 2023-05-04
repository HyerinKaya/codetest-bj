package codetest;

import java.util.*;

class draft {

    static int N, apeach, lion, Info[], result[], max, answer[];

    public static void main(String args[]) {
        int[] info = {8,0,0,0,0,0,0,0,1,0,1};
        int n = 10;
        System.out.println(Arrays.toString(solution(n, info))+"answer max:"+max);
    }

    static int[] solution(int n, int[] info) {
        answer = new int[11];
        N = n;
        apeach = 0;
        lion = 0;
        Info = info;
        result = new int[11];
        max = 0;

        dfs(0, 0);

        if(max==0) {
            int[] temp = {-1};
            answer = temp;
        }
        return answer;
    }

    static void dfs(int idx, int cnt){
        if(idx>10&&cnt<N){
            return;
        }
        if(cnt>=N){
            score();
            return;
        }else{
            result[idx]+=1;
            dfs(idx, cnt+1);
            result[idx]-=1;
            dfs(idx+1, cnt);
        }
    }

    static void score(){
        apeach = 0;
        lion = 0;
        for(int i=0;i<N;i++){
            if(Info[i]>=result[i]&&Info[i]!=0){ //apeach 득점
                apeach+=(10-i);
            }else if(Info[i]<result[i]){ //lion 득점
                lion+=(10-i);
            }
        }

        if(apeach<lion){ //lion 승리
            if(max<=(lion-apeach)){
                if(max==(lion-apeach)){
                    System.out.print(Arrays.toString(answer)+" "+Arrays.toString(result));
                    for(int i = 10; i >=0 ; i--) {
                        if(answer[i]==result[i]) continue;
                        if(answer[i] < result[i]){
                            answer = result.clone();
                            System.out.print("result win");
                            break;
                        }else if(answer[i] > result[i]){
                            break;
                        }
                    }
                    System.out.println();
                }else{
                    max = lion-apeach;
                    answer = result.clone();
                    System.out.println("max:"+max+Arrays.toString(answer));
                }
            }
        }
    }
}