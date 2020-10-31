package common_child;

import com.sun.tools.javac.util.StringUtils;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the commonChild function below.
    static int commonChild(String s1, String s2) {
     char[] a = s1.toCharArray();
     char[] b = s2.toCharArray();

//     Integer[][] c = new Integer[s1.length()+1][s2.length()+1];


        int[][] c = new int[s1.length()+1][s2.length()+1];


        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j]) {
                    c[i+1][j+1] = c[i][j] + 1;
                } else {
                    c[i+1][j+1] = Math.max(c[i+1][j], c[i][j+1]);
                }
            }
        }


        return c[a.length][b.length];

   //  return lcc(a,b,c,0,0);

    }

    /* dynamic programing to get lagest commen substring
       I used recursion with storing results in a matrix c to save time complexity

     */
    static private int lcc(char[] a, char[]b, Integer[][] c, int i, int j){
        c[0][j]=0;
        c[i][0]=0;
        if(i> a.length-1 || j>b.length -1 ){
            return 0;
        }
        if(c[i+1][j+1]!= null){
            System.out.println(c[i+1][j+1]);
            return c[i+1][j+1];
        } else if(a[i]==b[j]){
            c[i+1][j+1] = 1 + lcc(a,b,c,i-1,j-1);
            return c[i+1][j+1];
        }else{
            c[i+1][j+1] = Math.max(lcc(a,b,c,i-1,j),lcc(a,b,c,i,j-1));
            return c[i+1][j+1];
        }

    }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
