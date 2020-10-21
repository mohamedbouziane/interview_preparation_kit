package frequency_queries;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> freq = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (List<Integer> query : queries) {
            Integer key = query.get(0);
            Integer value = query.get(1);
            if (key == 1) {
                if (map.get(value) != null && freq.get(map.get(value)) > 0) {
                    freq.merge(map.get(value), -1, Integer::sum);
                }
                map.merge(value, 1, Integer::sum);
                freq.merge(map.get(value), 1, Integer::sum);
            } else if (key == 2) {
                if (map.get(value) != null && map.get(value) > 0 && freq.get(map.get(value)) > 0) {
                    freq.merge(map.get(value), -1, Integer::sum);
                    map.merge(value, -1, Integer::sum);
                    freq.merge(map.get(value), 1, Integer::sum);
                }
            } else if (key == 3) {
                if (freq.get(value) != null && freq.get(value) > 0) {
                    result.add(1);
                } else {
                    result.add(0);
                }
            }
        }
        return result;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
                ans.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
