package fraudulent_activity_notifications;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Solution {

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        int n = expenditure.length;
        if(d >= n) {
            return 0;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        int ans = 0;

        for(int i = 0; i < n; i++) {
            if(i >= d) {
                // calculate median
                double median;
                if(minHeap.size() > maxHeap.size()){
                    median = minHeap.peek();
                } else {
                    median = (minHeap.peek()+ maxHeap.peek()) / 2.0;
                }

                if((2 * median) <= expenditure[i]) {
                    ans++;
                }

                // tail the heaps
                if(expenditure[i - d] < median) {
                    maxHeap.remove(expenditure[i - d]);
                    maxHeap.offer(minHeap.poll());
                } else {
                    minHeap.remove(expenditure[i - d]);
                    minHeap.offer(maxHeap.poll());
                }
            }

            // add new elements to heaps
            minHeap.offer(expenditure[i]);
            maxHeap.offer(minHeap.poll());
            if(minHeap.size()<maxHeap.size()) {
                minHeap.offer(maxHeap.poll());
            }
        }

        return ans;
    }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
