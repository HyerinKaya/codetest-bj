import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1004 {
    static int startX;
    static int startY;
    static int endX;
    static int endY;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stN = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stN.nextToken());


        for (int i=0; i <N; i++) {
            StringTokenizer stpoint = new StringTokenizer(br.readLine());
            startX = Integer.parseInt(stpoint.nextToken());
            startY = Integer.parseInt(stpoint.nextToken());
            endX = Integer.parseInt(stpoint.nextToken());
            endY = Integer.parseInt(stpoint.nextToken());

            StringTokenizer stM = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(stM.nextToken());
            int answer = 0;
            for(int j=0; j<M; j++){
                StringTokenizer stCircle = new StringTokenizer(br.readLine());
                int circleX = Integer.parseInt(stCircle.nextToken());
                int circleY = Integer.parseInt(stCircle.nextToken());
                int r = Integer.parseInt(stCircle.nextToken());
                if(circle(circleX, circleY, r)==true){
                    answer++;
                }
            }
            System.out.println(answer);
        }
    }

    public static boolean circle(int circleX, int circleY, int r){
        boolean startPoint = Math.sqrt(Math.pow(circleX-startX,2)+Math.pow(circleY-startY,2))<r;
        boolean endPoint = Math.sqrt(Math.pow(circleX-endX,2)+Math.pow(circleY-endY,2))<r;

        if(startPoint!=endPoint){
            return true;
        }else{
            return false;
        }
    }

}
