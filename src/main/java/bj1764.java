import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class bj1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<String> hearlist = new ArrayList<>();
        ArrayList<String> seelist = new ArrayList<>();

        for(int i=0; i<N;i++){
            hearlist.add(br.readLine());
        }
        for(int i=0; i<N;i++){
            seelist.add(br.readLine());
        }
    }
}
