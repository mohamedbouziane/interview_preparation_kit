package sherlock_and_the_valid_string;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Solution {

    // Complete the isValid function below.
    static String isValid(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for(char c : s.toCharArray()){
            map.merge(c,1,Integer::sum);
        }

        List<Integer> list = map.values().stream().sorted(Integer::compareTo).collect(Collectors.toList());
        int size = list.size();

        System.out.println(list.toString());

        if(list.size()==1){
            return "YES";
        }else if(list.size()==2 && Math.abs(list.get(0)-list.get(1))==1){
            return "YES";
        }else if(size>2 && list.get(0)==1 && list.get(1)==list.get(size-1)){
            return "YES";
        }else if(size>2 && list.get(0)==list.get(size-2) && Math.abs(list.get(size-2)-list.get(size-1))==1){
            return "YES";
        }


        if(list.get(0)>1  && Math.abs(list.get(0)-list.get(size-1))>1){
            return "NO";
        }else if(size>2 && list.get(0)==1 && Math.abs(list.get(0)-list.get(1)) == Math.abs(list.get(0)-list.get(size-1))){
            return "YES";
        }
        else if(size>2 && Math.abs(list.get(0)-list.get(size-1))>0 &&Math.abs(list.get(0)-list.get(size-2))>0){
            return "NO";
        }else {
            return "YES";
        }
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
