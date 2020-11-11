package minimum_time_required;
import java.io.*;
import java.lang.reflect.Array;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static long minTime(long[] machines, long goal) {
        Arrays.sort(machines);
        long minDays = 0;
        long maxDays = machines[machines.length - 1] * goal;

        while (minDays < maxDays) {
            long mid = (minDays + maxDays) / 2;
            long generatedProducts = countGeneratedProducts(machines, mid);
            if (generatedProducts < goal) {
                minDays = mid + 1;
            } else {
                maxDays = mid;
            }
        }

        return maxDays;
    }

    static long countGeneratedProducts(long[] machines, long days) {
        long count = 0;
        for (int i=0; i < machines.length; i++) {
            count = count + (days / machines[i]);
        }

        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nGoal = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nGoal[0]);

        long goal = Long.parseLong(nGoal[1]);

        long[] machines = new long[n];

        String[] machinesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long machinesItem = Long.parseLong(machinesItems[i]);
            machines[i] = machinesItem;
        }

        long ans = minTime(machines, goal);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
