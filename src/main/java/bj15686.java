import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class bj15686 {
    static int N, M;
    static int[][] map, chickenArr;
    static boolean[] visit;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i=0;i<N;i++){
            StringTokenizer str = new StringTokenizer(br.readLine());
            for(int j=0; j<N;j++){
                map[i][j] = Integer.parseInt(str.nextToken());
                if(map[i][j]==2){
                    ArrayList<Integer> tempList = new ArrayList<>();
                    tempList.add(i);
                    tempList.add(j);
                    list.add(tempList);
                }
            }
        }

        answer = Integer.MAX_VALUE;
        chickenArr = new int[list.size()][2]; //모든 치킨집 위치 배열
        Iterator<ArrayList<Integer>> keys = list.iterator();
        int num =0;
        while(keys.hasNext()){
            ArrayList<Integer> tempList = keys.next();
            chickenArr[num][0] = tempList.get(0);
            chickenArr[num][1] = tempList.get(1);
            num++;
        }
        visit = new boolean[list.size()]; //치킨집 조합 포함 배열

        selectShop(0,0);
        System.out.println(answer);
    }

    //치킨집 조합
    static void selectShop(int idx, int count){
        if(idx>=visit.length||count>=M){
            if(count>=M){
                findLen();
            }
            return;
        }
        visit[idx]=true;
        selectShop(idx+1, count+1);
        visit[idx]=false;
        selectShop(idx+1,count);

    }

    //각 치킨집 돌며 최소거리 찾기
    static void findLen(){

        int totalLen = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]==1){
                    int minLen=Integer.MAX_VALUE;
                    //선택된 치킨집 순환
                    for(int p=0;p<visit.length;p++){
                        if(visit[p]==true){
                            int len = Math.abs(i-chickenArr[p][0])+Math.abs(j-chickenArr[p][1]);
                            minLen = Math.min(len, minLen);
                        }
                    }
                    totalLen += minLen;
                }
            }
        }
        answer = Math.min(totalLen, answer);
    }
}
