import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj1847 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int count =1;
        Stack<Integer> stack = new Stack<>();
        ArrayList<String> answer = new ArrayList<>();

        for(int i=0; i<N; i++){
            int num = Integer.parseInt(br.readLine());

            for(;count<=num;count++){
                stack.push(count);
                answer.add("+");
            }

            if(num==stack.peek()){
                stack.pop();
                answer.add("-");
            }else if(num!=stack.peek()){
                System.out.println("NO");
                return;
            }
        }
        for(String s: answer){
            System.out.println(s);
        }

    }
}
