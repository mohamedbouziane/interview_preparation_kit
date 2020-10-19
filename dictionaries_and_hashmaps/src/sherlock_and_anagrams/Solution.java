package sherlock_and_anagrams;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {



        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j <= s.length(); j++) {
                char[] chars = s.substring(i,j).toCharArray();
                Arrays.sort(    chars);
                String str =  Arrays.toString(chars);

                    // occurrence sum for each sorted substring
                    map.merge(str,1,Integer::sum);
                
            }

        }
        /**
         * s = kkkk
         *
         *  k -> occurrence = 4 --> sum of possible pairs is 6 = {(0,1), (0,2), (0,3), (1,2), (1,3), (2,3)}
         *                      --> 6 = 4*(4-1)/2
         *  kk -> occurrence = 3  --> sum of possible pairs is 3
         *                        --> 3 = 3*(3-1)/2
         *  kkk -> occurrence = 2 --> sum of possible pairs is 1
         *                        --> 1 = 2*(2-1)/2
         */

        return map.values().stream().map(n->n*(n-1)/2).reduce(0,Integer::sum);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}