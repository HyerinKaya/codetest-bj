import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class bj14889 {
    static HashMap<String, String> teamMap = new HashMap<>();
    static int N;
    static int[][] arr;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for(int i=0; i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        answer = Integer.MAX_VALUE;

        String startTeam = "";
        String linkTeam = "";
        makeTeam(startTeam, linkTeam,0);

        Iterator<String> keys = teamMap.keySet().iterator();
        while( keys.hasNext() ){
            String key = keys.next();
        }
        calScoreSub();

    }

    static void makeTeam(String start, String link, int count){
        if (count>=N||start.length()>=(N/2)){
            if(start.length()==(N/2)&&link.length()==(N/2)){
                teamMap.put(start, link);
            }
            return;
        }
        String copy = link + String.valueOf(count);
        makeTeam(start, copy, count+1);

        start = start + String.valueOf(count);
        makeTeam(start, link, count+1);
    }

    static void calScoreSub(){  //전체 팀 경우 돌며 능력치 차이 구하기
        Iterator<String> keys = teamMap.keySet().iterator();
        int[] start = new int[(N / 2)];
        int[] link = new int[(N / 2)];
        while( keys.hasNext() ){
            String key = keys.next();
            for(int i=0;i<(N/2);i++){
                start[i] = Integer.parseInt(String.valueOf(key.charAt(i)));
                link[i] = Integer.parseInt(String.valueOf(teamMap.get(key).charAt(i)));
            }
            int startSum = sumScore(start);
        }

        //int sum = arr[A][B]+arr[B][A];
    }

    static int sumScore(int[] team){  //한 팀 arr 돌며 모든 능력치 합 구하기
        int sum = 0;

        return sum;
    }
}
