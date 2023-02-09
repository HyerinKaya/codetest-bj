import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class bj1758 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer arr[] = new Integer[N];
        long sum = 0;

        for(int i=0; i<N;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr, Comparator.reverseOrder());

        for(int i=0; i<N ;i++){
            int tip = arr[i]-(i);
            if(tip>0) sum+= tip;
        }

        System.out.println(sum);
    }
}
