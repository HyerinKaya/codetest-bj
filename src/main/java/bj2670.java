import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class bj2670 {
    static int N;
    static ArrayList<Double> list = new ArrayList<>();
    static double max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        double[] arr = new double[n+1];
        double[] dp = new double[n+1];
        double max = 0;

        for (int i = 1; i <= n; i++) {
            arr[i] = Double.parseDouble(br.readLine());
            dp[i] = Math.max(arr[i], dp[i-1] * arr[i]);
            max = Math.max(max, dp[i]);
        }
        System.out.print(String.format("%.3f",max));
    }
}
