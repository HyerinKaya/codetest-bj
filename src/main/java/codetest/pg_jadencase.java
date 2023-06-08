import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        s += " .";
        String[] arr = s.split(" ");
        for(int i=0;i<arr.length;i++){
            if(arr[i].length()<1){
                continue;
            }
            StringBuilder sb = new StringBuilder();
            String str = arr[i];
            int ascii = (int)str.charAt(0);
            if(ascii>=97&&ascii<=122){
                int asciiVal = ascii-32;
                String change = new Character((char)asciiVal).toString();
                sb.append(change);
            }else{
                sb.append(str.charAt(0));
            }
            for(int j=1;j<str.length();j++){
                ascii = (int)str.charAt(j);
                if(ascii>=65&&ascii<=90){
                    int asciiVal = ascii+32;
                    String change = new Character((char)asciiVal).toString();
                    sb.append(change);
                }else{
                    sb.append(str.charAt(j));
                }
            }
            arr[i] = sb.toString();
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<arr.length-1;i++){
            sb.append(arr[i]);
            if(i<arr.length-2){
                sb.append(" ");
            }
        }


        answer = sb.toString();
        return answer;
    }
}