package codetest;


import java.util.*;

class pg_bracket_rotate {

    static String str;

    public int solution(String s) {
        int answer = 0;
        str = s;

        for(int i=0;i<str.length();i++){
            // System.out.println(str);
            if(check()==true){
                answer+=1;
            }
            move();
        }


        return answer;
    }

    static boolean check(){
        Stack<String> stack1 = new Stack<>();
        Stack<String> stack2 = new Stack<>();
        boolean isValid = true;
        String[] sArr = str.split("");
        for(int i=0;i<sArr.length;i++){
            stack1.push(sArr[i]);
        }

        String s = new String();
        while(!stack1.isEmpty()){
            s = stack1.pop();
            if(s.equals(")")||s.equals("}")||s.equals("]")){
                stack2.push(s);
            }else{
                if(stack2.isEmpty()){
                    isValid = false;
                    return isValid;
                }
                String o = stack2.peek();
                // System.out.println(s+" "+o+match(s,o));
                if(match(s,o)){
                    stack2.pop();
                }else{
                    isValid = false;
                    return isValid;
                }
            }

        }

        if(!stack2.isEmpty()){
            isValid = false;
        }
        return isValid;
    }

    static boolean match(String s, String o){
        return((s.equals("(")&&o.equals(")"))||(s.equals("{")&&o.equals("}"))||(s.equals("[")&&o.equals("]")));
    }

    static void move(){
        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(1));
        sb.append(str.substring(0,1));
        str = sb.toString();
    }
}
