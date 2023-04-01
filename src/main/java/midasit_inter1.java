import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
//N개의 직사각형 나무블록이 있다.
//i번째 직사각형 나무블록의 크기는 Ai × Bi이다.
//여러분은 이 직사각형 나무블록을 좌우변이 서로 붙도록 일렬로 나열하여 하나의 큰 단순 직교 다각형을 만들었다.
//이때, 직사각형 나무블록을 자유롭게 회전할 수 있고, 나무블록 간의 순서를 바꾸어도 좋다.
//만든 하나의 큰 다각형에서 가장 큰 직사각형을 찾자.
//단, 이 직사각형의 변은 다각형의 변과 수직 또는 수평을 이루어야 한다.
//가장 큰 직사각형의 넓이로 가능한 최댓값을 구하라.

class Rectangle {
    int height, width;
    Rectangle(int height, int width) {
        this.height = height;
        this.width = width;
    }
}

public class midasit_inter1 {
    static int[] a = {3};
    static int[] b = {14};
    static int maxArea;

    public static void main(String[] args) throws IOException {
        int n = 1;
        Rectangle[] rectangles = new Rectangle[n];
        for (int i = 0; i < n; i++) {
            int h = a[i];
            int w = b[i];
            rectangles[i] = new Rectangle(h, w);
        }
        int maxArea = makeMaxArea(rectangles);
        System.out.println(maxArea);
    }
    static int makeMaxArea(Rectangle[] rectangles){
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < rectangles.length; i++) {
            while (!stack.empty() && rectangles[stack.peek()].height >= rectangles[i].height) {
                int j = stack.pop();
                int k = stack.empty() ? -1 : stack.peek();
                int area = rectangles[j].height * (i - k - 1);
                maxArea = Math.max(maxArea, area);
            }
            stack.push(i);
        }
        int n = rectangles.length;
        while (!stack.empty()) {
            int j = stack.pop();
            int k = stack.empty() ? -1 : stack.peek();
            int area = rectangles[j].height * (n - k - 1);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
}



