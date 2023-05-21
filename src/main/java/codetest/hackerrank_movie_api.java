package codetest;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



import java.net.*;
import org.json.*;
import com.google.gson.*;

class Result {

    /*
     * Complete the 'bestInGenre' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING genre as parameter.
     *
     * Base URL: https://jsonmock.hackerrank.com/api/tvseries?page=
     */

    public static String bestInGenre(String genre){
        // Write your code here
        try{
            String baseUrl = "https://jsonmock.hackerrank.com/api/tvseries?";
            String queryParam = "genre="+genre;
            String encodedParam = URLEncoder.encode(queryParam, "UTF-8");

            System.out.println();

            URL url = new URL(baseUrl+"?"+encodedParam);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while((inputLine = in.readLine()) !=null){
                response.append(inputLine);
            }
            in.close();

            String r = response.toString();
            JsonObject jsonObj = new Gson.fromJson(r, JsonObject.class);
            JsonArray jsonArr = jsonObj.getJsonArray("data");

            // for(int i=0;i<jsonArr.length;i++){
            //     JSONObject item = jsonArr.getJSONObject(i);
            //     String name = item.getString("name");
            //     int rate = item.getString("imdb_rating");
            //     System.out.println("name:"+name+",rate:"+rate+" /");
            // }

            // System.out.println(response.toString());

            JsonObject best = new JsonObject();
            for(int i=0;i<jsonArr.size();i++){
                JsonObject item = jsonArr.getJsonObject(i);
                String name = item.getString("name");
                int rate = item.getString("imdb_rating");
                if(item.getString("genre").equals(genre)){
                    if(rate>best.getString("imdb_rating")){
                        best = item;
                    }else if(rate == best.getString("imdb_rating")&&name<best.getString("name")){
                        best = item;
                    }
                }
            }

            String result = best.getString("name");
            return result;
        }catch(IOException e){
            e.printStackTrace();
        }
        return "result";
    }

}

public class hackerrank_movie_api {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String genre = bufferedReader.readLine();

        String result = Result.bestInGenre(genre);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}