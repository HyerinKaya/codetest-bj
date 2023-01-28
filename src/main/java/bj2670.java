import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj2670 {
    static int N;
    static ArrayList<Double> list = new ArrayList<>();
    static double max;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for(int i=0; i<N;i++){
            String temp = new String(br.readLine());
            Double num = Double.parseDouble(temp);
            list.add(num);
        }

        max = 0;
        for(int i =0; i<list.size();i++){
            findMax(i, 1);
        }
        System.out.println(Math.round(max * 1000) / 1000.0);
    }

    public static void findMax(int end, double pre_sum){
        double sum = pre_sum*(list.get(end));
        if(sum>max){
            max = sum;
        }
        if(end<list.size()-1){
            findMax(end+1, sum);
        }
    }
}
