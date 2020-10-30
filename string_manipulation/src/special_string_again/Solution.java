package special_string_again;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the substrCount function below.
    static long substrCount(int n, String s) {

        Pattern p1 = Pattern.compile("([a-z])\\1{1,}");
        Matcher m1 = p1.matcher(s);

        long sum =0;
        // count token like aa, aaa, aaaa ...
        while (m1.find()){
            String mtch = m1.group();
            long l = mtch.length();
            sum+= l*(l-1)/2;
        }
        // count token like aba, obo, nmn ...
        if(n>=3) {
            for (int i = 1; i <= n - 2; i++) {
                if(s.charAt(i-1)==s.charAt(i+1)&& s.charAt(i)!=s.charAt(i+1)){
                    sum++;
                    int j = 2;
                    //count token like aabaa, aaabaaa,  ...
                    while(i-j>=0 && i+j<=n-1 && s.charAt(i-j)==s.charAt(i-1) && s.charAt(i+j)==s.charAt(i-j)){
                        sum++;
                        j++;
                    }
                }
            }
        }

       return sum + n ;


    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
