import java.io.*;

public class bj1904 {
    static int[] tileArr;
    static int N ;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        tileArr = new int[N+1];

        System.out.println(countTile(N));

    }
    public static int countTile(int n) {
        if(tileArr[n]!=0)
            return tileArr[n];

        if(n == 1)
            return 1;
        else if(n == 2)
            return 2;
        else if(n > 2)
            return tileArr[n] = (countTile(n-1) + countTile(n-2))%15746;
        else
            return n;
    }

}
