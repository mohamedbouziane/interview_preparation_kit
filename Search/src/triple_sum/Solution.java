package triple_sum;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the triplets function below.
    static SortedSet<Integer> getSet(int[] array){
        SortedSet<Integer> result = new TreeSet<Integer>();
        for (int i=0; i<array.length; i++){
            result.add(array[i]);
        }
        return result;
    }

    static NavigableMap<Integer, Integer> cumulated (Set<Integer> set){
        NavigableMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        int counter = 0;
        for (Integer element : set){
            map.put(element, ++counter);
        }
        return map;

    }
    // Complete the triplets function below.
    static long triplets(int[] a, int[] b, int[] c) {
        SortedSet<Integer> setA = getSet(a);
        SortedSet<Integer> setB = getSet(b);
        SortedSet<Integer> setC = getSet(c);

        NavigableMap<Integer, Integer> aMap = cumulated(setA);
        NavigableMap<Integer, Integer> cMap = cumulated(setC);

        long counter = 0;

        for(Integer bElement : setB){
            Map.Entry<Integer, Integer> entryA = aMap.floorEntry(bElement);
            int itemsA = entryA == null ? 0 : entryA.getValue();

            Map.Entry<Integer, Integer> entryC = cMap.floorEntry(bElement);
            int itemsC = entryC == null ? 0 : entryC.getValue();

            counter += (long)itemsA*itemsC;
        }
        return counter;
    }



    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] lenaLenbLenc = scanner.nextLine().split(" ");

        int lena = Integer.parseInt(lenaLenbLenc[0]);

        int lenb = Integer.parseInt(lenaLenbLenc[1]);

        int lenc = Integer.parseInt(lenaLenbLenc[2]);

        int[] arra = new int[lena];

        String[] arraItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lena; i++) {
            int arraItem = Integer.parseInt(arraItems[i]);
            arra[i] = arraItem;
        }

        int[] arrb = new int[lenb];

        String[] arrbItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenb; i++) {
            int arrbItem = Integer.parseInt(arrbItems[i]);
            arrb[i] = arrbItem;
        }

        int[] arrc = new int[lenc];

        String[] arrcItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenc; i++) {
            int arrcItem = Integer.parseInt(arrcItems[i]);
            arrc[i] = arrcItem;
        }

        long ans = triplets(arra, arrb, arrc);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
