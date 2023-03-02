import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj1654 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[K];


        long max = 0;
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if(max < arr[i]) {
                max = arr[i];
            }
        }

        max++;

        long min = 0;
        long mid;

        while (min < max) {
            mid = (max + min) / 2;
            long count = 0;
            // 구해진 중간 길이로 잘라서 총 몇 개가 만들어지는지를 구한다.
            for (int i = 0; i < arr.length; i++) {
                count += (arr[i] / mid);
            }
            if(count < N) {
                max = mid;
            }
            else {
                min = mid+1;
            }
        }
        System.out.println(min - 1);
    }
}
