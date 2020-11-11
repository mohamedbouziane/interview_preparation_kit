package maximum_subarray_sum;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the maximumSum function below.
    static long maximumSum(long[] a, long m) {


        long[][] cal = new long[a.length][a.length];
        long max = 0;
        for (int i = 0; i < a.length; i++) {

            for (int j = i; j < a.length; j++) {
                if (i == j) {
                    cal[i][j] = a[i] % m;
                    if (cal[i][j] > max) {
                        max = cal[i][j];
                    }
                } else {
                    cal[i][j] = (cal[i][j - 1] + a[j]) % m;
                    if (cal[i][j] > max) {
                        max = cal[i][j];
                    }
                }

                if (max == m - 1) {
                    return max;
                }
            }
        }

        return max;


    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            long m = Long.parseLong(nm[1]);

            long[] a = new long[n];

            String[] aItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                long aItem = Long.parseLong(aItems[i]);
                a[i] = aItem;
            }

            long result = maximumSum(a, m);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
