package codetest;

import java.util.*;

public class pg_candidate_key {

    static boolean isCandidate;
    static String[][] rel;
    static ArrayList<Boolean[]> list; //선택된 열 조합 저장
    static int M, answer;
    static Boolean[] arr; //선택된 열

    public static void main(String args[]){

        String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
        System.out.println(solution(relation));
    }

    static int solution(String[][] relation) {
        rel = new String[relation.length][relation[0].length];
        for(int i=0;i<relation.length;i++){
            rel[i] = relation[i].clone();
        }
        arr = new Boolean[relation[0].length];
        for(int i=0;i<arr.length;i++){
            arr[i] = false;
        }
        list = new ArrayList<>();

        answer = 0;

        for(int i=1;i<=rel[0].length;i++){
            M = i;
            dfs(0, 0);
        }


        checkMin();

        return answer;
    }

    static void dfs(int idx, int cnt){  //열 선택
        if(idx>=arr.length&&cnt<M) return;
        if(cnt>=M){
            //선택된 열들 한 열로 만들기
            String[] strArr = new String[rel.length];
            for(int i=0;i<strArr.length;i++){
                StringBuilder sb = new StringBuilder();
                for(int j=0;j<arr.length;j++){
                    if(arr[j]==true){
                        sb.append(rel[i][j]);
                    }
                }
                strArr[i] = sb.toString();
            }
            checkCandidate(strArr);
            return;

        }else{
            arr[idx] = true;
            dfs(idx+1, cnt+1);
            arr[idx] = false;
            dfs(idx+1, cnt);
        }
    }

    static void checkCandidate(String[] strArr){
        HashMap<String, Integer> strMap = new HashMap<>();
        isCandidate = true;
        for(int i=0;i<strArr.length;i++){
            if(strMap.containsKey(strArr[i])){
                isCandidate = false;
                break;
            }else{
                strMap.put(strArr[i], 0);
            }
        }
        if(isCandidate == true){
            Boolean[] temp = new Boolean[rel[0].length];
            for(int i=0;i<arr.length;i++){
                temp[i] = arr[i];
            }
            list.add(temp);
        }
//        System.out.println("arr:"+Arrays.toString(arr)+"  strArr:"+Arrays.toString(strArr)+"  isCan:"+isCandidate);
    }

    static void checkMin(){ //최소성 검사
        boolean isMin = true;
        boolean[] delArr = new boolean[list.size()];

        for(int i=0;i<list.size();i++){
            Boolean[] arr1 = list.get(i);
            for(int j=i+1;j<list.size();j++){
                isMin = false;
                Boolean[] arr2 = list.get(j);
                Stack<Integer> stack = new Stack<>();
                for(int p = 0;p<arr2.length;p++){
                    if(arr1[p]^arr2[p]){//한쪽만 true
                        if(arr1[p]==true){
                            stack.push(i);
                        }else if(arr2[p]==true){
                            stack.push(j);
                        }
                    }
                }
                int pre = 0;
                if(!stack.isEmpty()) pre = stack.pop();
                while(!stack.isEmpty()){
                    int now = stack.pop();
                    if(pre!=now){
                        isMin = true;
                        break;
                    }
                }

                if(isMin==false){
                    delArr[pre] = true;
                }

//                System.out.print(Arrays.toString(arr1)+" "+Arrays.toString(arr2)+" "+isMin);
//                System.out.println();
            }
        }

        ArrayList<Boolean[]> temp = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            temp.add(list.get(i));
        }
        list = new ArrayList<>();
        for(int i=0;i<delArr.length;i++){
            if(delArr[i]==false){
                list.add(temp.get(i));
                answer+=1;
            }
        }

    }
}

