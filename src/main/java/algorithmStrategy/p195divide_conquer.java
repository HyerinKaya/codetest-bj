package algorithmStrategy;
/*
너비가 같은 N개의 나무 판자를 붙여 세운 울타리가 있습니다 . 시간이 지남에
따라 판자들이 부러지거나 망가져 높이가 다 달라진 관계로 울타리를 통깨로
교쳬하기로 했습니다. 이때 버리는 울타리의 일부를 직사각형으로 잘라내 재
활용하고 싶습니다.
너비가 같은 N개의 나무 판자를 붙여 세운 울타리가 있습니다 . 시간이 지남에
따라 판자들이 부러지거나 망가져 높이가 다 달라진 관계로 울타리를 통깨로
교쳬하기로 했습니다. 이때 버리는 울타리의 일부를 직사각형으로 잘라내 재
활용하고 싶습니다.
->분할 정복 알고리즘
 */
public class p195divide_conquer {
    static int[] h;
    public static void main(String[] args) {

    }

    static int solve(int left, int right){
        //기저사례: 판자가 하나뿐인 경우 -> 판자 하나씩 찢어져 이 단계부터 정복 시작
        if(left==right) return h[left];

        int mid = (left+right)/2;
        int ret = Math.max(solve(left, mid), solve(mid+1, right));
        int lo = mid;
        int hi = mid+1;
        int height = Math.min(h[lo], h[hi]);

        //큰 높이 하나 포함 vs. 작은 높이 두개 포함 비교
        ret = Math.max(ret, height*2);

        while(left<lo || hi<right){
            //항상 높이가 더 높은 쪽으로 확장
            if(hi<right&&(lo==left||h[lo-1]<h[hi+1])){
                //오른쪽 확장(오른쪽 남았고, 왼쪽 없거나/오른쪽 더 높거나)
                hi+=1;
                height = Math.min(height, h[hi]);
            }else{
                lo-=1;
                height=Math.min(height, h[lo]);
            }
            //확장한 후 사각형의 넓이
            ret = Math.max(ret, height*(hi-lo+1));
        }
        return ret;
    }
}
