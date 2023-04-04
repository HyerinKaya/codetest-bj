package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj14890 {

    static int N, L, answer;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        answer=0;

        for(int i=0; i<N;i++){
            StringTokenizer str = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        int oldCnt, newCnt, num = 0;
        boolean isRoad=true;

        for(int i=0;i<N;i++){
            oldCnt=1;
            newCnt=0;
            num=0;
            isRoad = true;
            boolean[] visit = new boolean[N];

            for(int j=1;j<N;j++) {
                if(map[i][j]==map[i][j-1]){
                    if(visit[j-1]==true) continue;
                    //앞과 같을 때 누적
                    oldCnt+=1;
                }else if(map[i][j]==(map[i][j-1]-1)){
                    //앞보다 1 작을 때 L만큼 있는지 확인
                    num = map[i][j];
                    newCnt=0;
                    oldCnt=1;
                    visit[j]=true;
                    while(map[i][j]==num){
                        newCnt+=1;
                        j+=1;
                        if(j>N-1||newCnt==L) break;
                        visit[j]=true;
                    }
                    j-=1;
                    if(newCnt!=L){
                        isRoad=false;
                        break;
                    }
                }else if(map[i][j]==(map[i][j-1]+1)){
                    //앞보다 1 클 때 앞 누적 L만큼 있는지 확인
                    if(oldCnt>=L&&visit[j-1]==false){
                        oldCnt=1;
                    }else{
                        isRoad=false;
                        break;
                    }
                }else{
                    //1보다 큰 차이일 때 길 탐색 건너뛰기
                    isRoad=false;
                    break;
                }
            }
            if(isRoad==true) answer+=1;
        }

        for(int j=0;j<N;j++){
            oldCnt=1;
            newCnt=0;
            num=0;
            isRoad = true;
            boolean[] visit = new boolean[N];

            for(int i=1;i<N;i++) {
                if(map[i][j]==map[i-1][j]){
                    if(visit[i-1]==true) continue;
                    //앞과 같을 때 누적
                    oldCnt+=1;
                }else if(map[i][j]==(map[i-1][j]-1)){
                    //앞보다 1 작을 때 L만큼 있는지 확인
                    num = map[i][j];
                    newCnt=0;
                    oldCnt=1;
                    visit[i]=true;
                    while(map[i][j]==num){
                        newCnt+=1;
                        i+=1;
                        if(i>N-1||newCnt==L) break;
                        visit[i]=true;
                    }
                    i-=1;
                    if(newCnt!=L){
                        isRoad=false;
                        break;
                    }
                }else if(map[i][j]==(map[i-1][j]+1)){
                    //앞보다 1 클 때 앞 누적 L만큼 있는지 확인
                    if(oldCnt>=L&&visit[i-1]==false){
                        oldCnt=1;
                    }else{
                        isRoad=false;
                        break;
                    }
                }else{
                    //1보다 큰 차이일 때 길 탐색 건너뛰기
                    isRoad=false;
                    break;
                }
            }
            if(isRoad==true) answer+=1;
        }


        System.out.println(answer);


    }
}
