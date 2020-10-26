package making_anagrams;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {
       Map<Character, Integer> map1 = new HashMap<>();
       Map<Character, Integer> map2 = new HashMap<>();

       for(Character c : a.toCharArray()){
           map1.merge(c,1,Integer::sum);
       }
        for(Character c : b.toCharArray()){
            map2.merge(c,1,Integer::sum);
        }
        Set<Character> intersection = new HashSet<>(map1.keySet());
        intersection.retainAll(map2.keySet());

        Set<Character> diff = new HashSet<>(map1.keySet());
        diff.addAll(map2.keySet());
        diff.removeAll(intersection);

        int sum =0;

        for(Character c : intersection){
            sum+=Math.abs(map1.get(c)-map2.get(c));
        }

        for(Character c : diff){
            if(map1.get(c) != null){
                sum+=map1.get(c);
            }else{
                sum+=map2.get(c);
            }
        }

        return sum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String a = scanner.nextLine();

        String b = scanner.nextLine();

        int res = makeAnagram(a, b);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
